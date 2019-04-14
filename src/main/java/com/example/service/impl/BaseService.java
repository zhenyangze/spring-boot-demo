package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exception.LogicException;
import com.example.filter.TokenFilter;
import com.example.model.BaseModel;
import com.example.model.po.User;
import com.example.model.vo.UserDetailsImpl;
import com.example.service.IBaseService;
import com.example.service.ITokenService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseService<M extends BaseMapper<T>, T extends BaseModel> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ITokenService tokenService;

    @Override
    public User currentUser() {
        String token = TokenFilter.getToken(request);
        UserDetailsImpl userDetails = tokenService.getUserDetalis(token);
        return userDetails==null? null: userDetails.getUser();
    }

    @Override
    @Transactional
    public void merge(Collection<T> collection, QueryWrapper<T> queryWrapper) {
        merge(collection, queryWrapper, 1000);
    }

    @Override
    @Transactional
    public void merge(Collection<T> collection, QueryWrapper<T> queryWrapper, int batchSize) {
        if (queryWrapper==null || StringUtils.isEmpty(queryWrapper.getCustomSqlSegment())) {
            throw new LogicException("queryWrapper查询条件不能为空！");
        }
        if (Collections.isEmpty(collection)) {
            remove(queryWrapper);
            return;
        }
        List<T> olds = list(queryWrapper);
        if (olds.isEmpty()) {
            saveBatch(collection, batchSize);
            return;
        }
        QueryWrapper<T> deleteWrapper = new QueryWrapper<>();
        Map<String, String> pkMap = null;
        Iterator<T> oldIterator = olds.iterator();
        boolean flag = false;
        a: while (oldIterator.hasNext()) {
            T old = oldIterator.next();
            if (pkMap==null) {
                pkMap = old.pkMap();
            }
            Iterator<T> iterator = collection.iterator();
            b: while (iterator.hasNext()) {
                T t = iterator.next();
                // 比较id是否相等
                for (String id: pkMap.keySet()) {
                    if (!old.value(id).equals(t.value(id))) {
                        continue b;
                    }
                }
                UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
                for (Map.Entry<String, String> entry: pkMap.entrySet()) {
                    updateWrapper.eq(entry.getValue(), t.value(entry.getKey()));
                }
                update(t, updateWrapper);
                iterator.remove();
                continue a;
            }
            for (Map.Entry<String, String> entry: pkMap.entrySet()) {
                deleteWrapper.eq(entry.getValue(), old.value(entry.getKey()));
            }
            deleteWrapper.or();
            flag = true;
        }
        if (flag) {
            remove(deleteWrapper);
        }
        if (!Collections.isEmpty(collection)) {
            saveBatch(collection, batchSize);
        }
    }

}
