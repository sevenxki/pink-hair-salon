package edu.scau.misp.sys.security.config.handler;


import com.alibaba.fastjson.JSON;

import edu.scau.misp.sys.util.HttpStatus;
import edu.scau.misp.hs.util.ServletUtils;
import edu.scau.misp.hs.util.StringUtils;
import edu.scau.misp.sys.entity.ResultEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 未授权请求拒绝处理类 返回未授权
 */
@Component("misAccessDeniedHandler")
public class MisAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String msg = StringUtils.format("请求{}被拒绝，没有权限访问！", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(ResultEntity.error(HttpStatus.FORBIDDEN,msg)));
    }
}