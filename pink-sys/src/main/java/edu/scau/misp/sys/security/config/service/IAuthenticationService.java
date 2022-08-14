package edu.scau.misp.sys.security.config.service;


import org.springframework.security.core.AuthenticationException;

import java.util.Map;

public interface IAuthenticationService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     */
    String login(String username, String password) throws AuthenticationException;

    /**
     * 获得登录用户信息
     * @return
     * @throws AuthenticationException
     */
    Map<String, Object> getUserInfo() throws AuthenticationException ;
}
