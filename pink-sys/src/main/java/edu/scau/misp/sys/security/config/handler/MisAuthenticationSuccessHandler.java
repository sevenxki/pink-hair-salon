package edu.scau.misp.sys.security.config.handler;

import com.alibaba.fastjson.JSON;

import edu.scau.misp.hs.util.ServletUtils;
import edu.scau.misp.sys.entity.ResultEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 认证成功处理器
 * security默认返回网页资源，前后端分离返回json
 */

@Component("misAuthenticationSuccessHandler")
public class MisAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HashMap<String,Object>  data = new HashMap<>();
        ResultEntity result = ResultEntity.success("登录成功!",data);
        ServletUtils.renderString(response,JSON.toJSONString(result));
    }
}
