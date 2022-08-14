package edu.scau.misp.sys.security.filter;

import edu.scau.misp.sys.domain.HsLoginUser;
import edu.scau.misp.sys.service.HsTokenService;
import edu.scau.misp.sys.util.SecurityUtils;
import edu.scau.misp.sys.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component("authenticationTokenFilter")
public class MisAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private HsTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 1. 从请求头中获取token
        String token = request.getHeader("Authorization");
        System.out.println(token);

        // 2. 解析token获得claims
        Claims claims = tokenService.parseToken(token);

        String m = null;
        System.out.println(token==m);
        if(token==m) {
            System.out.println(1);
        }else{
            System.out.println(2);
            // 3. 判断token是否过期
            if (tokenService.isTokenExpired(claims)) {
                throw new JwtException("token已过期");
            }
            // 4. 得到subject
            String userName = claims.getSubject();  // 获取token是设置的subject是用户名。注意subject可以是LoginUser的任意唯一字段
            System.out.println(userName);
        }

        // 5. subject与LoginUser关联
        //这部分不打算使用了
        HsLoginUser loginUser = null;


        // 5. 如果得到loginUser，使用改loginUser进行用户认证
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // 6. 进入下一个过滤器(即UsernamePasswordAuthenticationFilter)进行用户认证
        chain.doFilter(request, response);
    }
}
