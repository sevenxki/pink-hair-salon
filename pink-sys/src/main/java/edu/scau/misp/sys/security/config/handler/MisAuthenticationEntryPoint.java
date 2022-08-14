package edu.scau.misp.sys.security.config.handler;


import com.alibaba.fastjson.JSON;

import edu.scau.misp.sys.util.HttpStatus;
import edu.scau.misp.hs.util.ServletUtils;
import edu.scau.misp.hs.util.StringUtils;
import edu.scau.misp.sys.entity.ResultEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 */
@Component("misAuthenticationEntryPoint")
public class MisAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String msg = StringUtils.format("请求{}失败，未认证用户拒绝访问！", httpServletRequest.getRequestURI());
        ServletUtils.renderString(httpServletResponse, JSON.toJSONString(ResultEntity.error(HttpStatus.UNAUTHORIZED, msg)));
    }
}

