package com.example.service;

import com.example.model.vo.TokenVO;
import com.example.model.vo.UserDetailsImpl;

public interface ITokenService {

    // 获取token并缓存userDetails
    TokenVO saveToken(UserDetailsImpl userDetails);

    // 刷新缓存中的userDetails
    void refresh(UserDetailsImpl userDetails);

    // 根据token获取userDetails
    UserDetailsImpl getUserDetalis(String token);

    // 删除token对应的缓存中的userDetails
    void deleteToken(String token);

}
