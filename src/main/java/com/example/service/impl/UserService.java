package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserMapper;
import com.example.model.po.Book;
import com.example.model.po.Role;
import com.example.model.po.User;
import com.example.model.po.UserRoleLink;
import com.example.service.IBookService;
import com.example.service.IUserRoleLinkService;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IBookService bookService;
    @Autowired
    private IUserRoleLinkService userRoleLinkService;

    @Override
    @Cacheable(cacheNames = {"user:multiple"}, keyGenerator = "defaultPageKeyGenerator")
    public IPage<User> page(IPage<User> page, Wrapper<User> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    @Cacheable(cacheNames = {"user:single"}, key = "'user:'+#id", unless = "#result==null")
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"user:multiple"}, allEntries = true)
    public void customSave(User user) {
        baseMapper.insert(user);
        Integer userId = user.getId();
        List<Book> books = user.getBooks();
        for (Book book: books) {
            book.setUserId(userId);
        }
        bookService.saveBatch(books);
        List<Role> roles = user.getRoles();
        List<UserRoleLink> links = new ArrayList<>();
        for (Role role: roles) {
            links.add(new UserRoleLink(userId, role.getId()));
        }
        userRoleLinkService.saveBatch(links);
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"user:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"user:single"}, key = "'user:'+#user.id")
            }
    )
    public void customUpdate(User user) {
        baseMapper.updateById(user);
        Integer userId = user.getId();
        List<Book> books = user.getBooks();
        for (Book book: books) {
            book.setUserId(userId);
        }
        List<Book> oldBooks = bookService.list(new QueryWrapper<>(new Book().setUserId(userId)));
        List<Integer> ids = new ArrayList<>();
        Iterator<Book> oldBooksIterator = oldBooks.iterator();
        a: while (oldBooksIterator.hasNext()) {
            Book oldBook = oldBooksIterator.next();
            for (Book book: books) {
                if (oldBook.getId().equals(book.getId())) {
                    continue a;
                }
            }
            ids.add(oldBook.getId());
        }
        if (ids.size()>0) {
            bookService.removeByIds(ids);
        }
        if (books.size()>0) {
            bookService.saveOrUpdateBatch(books);
        }
        List<Role> roles = user.getRoles();
        List<UserRoleLink> links = new ArrayList<>();
        for (Role role: roles) {
            links.add(new UserRoleLink(userId, role.getId()));
        }
        List<UserRoleLink> oldLinks = userRoleLinkService.list(new QueryWrapper<>(new UserRoleLink().setUserId(userId)));
        QueryWrapper<UserRoleLink> queryWrapper = new QueryWrapper<>();
        Iterator<UserRoleLink> oldLinksIterator = oldLinks.iterator();
        boolean flag = false;
        b: while (oldLinksIterator.hasNext()) {
            UserRoleLink oldLink = oldLinksIterator.next();
            Iterator<UserRoleLink> linksIterator = links.iterator();
            while (linksIterator.hasNext()) {
                UserRoleLink link = linksIterator.next();
                if (oldLink.getRoleId().equals(link.getRoleId())) {
                    UpdateWrapper<UserRoleLink> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("user_id", link.getUserId());
                    updateWrapper.eq("role_id", link.getRoleId());
                    userRoleLinkService.update(link, updateWrapper);
                    linksIterator.remove();
                    continue b;
                }
            }
            queryWrapper.eq("user_id", oldLink.getUserId());
            queryWrapper.eq("role_id", oldLink.getRoleId());
            queryWrapper.or();
            flag = true;
        }
        System.out.println("---------queryWrapper: "+queryWrapper.getCustomSqlSegment());
        if (flag) {
            userRoleLinkService.remove(queryWrapper);
        }
        if (links.size()>0) {
            userRoleLinkService.saveBatch(links);
        }
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"user:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"user:single"}, key = "'user:'+#id")
            }
    )
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
