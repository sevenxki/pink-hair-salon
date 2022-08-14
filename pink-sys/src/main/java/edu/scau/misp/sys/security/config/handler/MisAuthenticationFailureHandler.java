package edu.scau.misp.sys.security.config.handler;



import com.alibaba.fastjson.JSON;

import edu.scau.misp.hs.util.ServletUtils;
import edu.scau.misp.sys.entity.ResultEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 * security默认返回网页资源，前后端分离返回json
 */
@Component("misAuthenticationFailureHandler")
public class MisAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // TODO 抛出不同异常
        ResultEntity result = ResultEntity.error("用户认证失败!");
        ServletUtils.renderString(response,JSON.toJSONString(result));
    }
}
