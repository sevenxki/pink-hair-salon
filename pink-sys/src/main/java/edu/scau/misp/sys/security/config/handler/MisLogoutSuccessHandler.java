package edu.scau.misp.sys.security.config.handler;


import com.alibaba.fastjson.JSON;

import edu.scau.misp.hs.util.ServletUtils;
import edu.scau.misp.sys.entity.ResultEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 认证退出成功处理器
 * security默认返回网页资源，前后端分离返回json
 */
@Component("misLogoutSuccessHandler")
public class MisLogoutSuccessHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        ResultEntity result = ResultEntity.success("退出登录成功");
        ServletUtils.renderString(response, JSON.toJSONString(result));
    }
}
