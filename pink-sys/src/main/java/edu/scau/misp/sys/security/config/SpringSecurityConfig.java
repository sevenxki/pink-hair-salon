package edu.scau.misp.sys.security.config;


import edu.scau.misp.sys.security.config.handler.*;
import edu.scau.misp.sys.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置认证管理bean
     * @return
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    // 不受控静态资源RUL
    private static final String[] SECURITY_IGNORE_RESOURCE_URL = {
            "/*.html", "/**/*.html", "/**/*.css", "/**/*.js",
            "/webjars/**", "/swagger-ui/**", "/v3/api-docs/**","/swagger-resources/**",
            "/druid/**"
    };
    // 不受控API_PATH
    private static final String[] SECURITY_IGNORE_API_PATH = {
            "/lab/**", "/sys/**", // 将模块controller加入白名单，方便开发测试。开发完成后注释,携带token请求
            "/auth/login","/login","/auth/logout","/auth/register","/auth/username/{username}","/hsUser/tk"
    };




//    @Autowired
//    MisAuthenticationSuccessHandler misAuthenticationSuccessHandler;
//
//    @Autowired
//    MisAuthenticationFailureHandler misAuthenticationFailureHandler;

    @Autowired
    MisLogoutSuccessHandler misLogoutSuccessHandler;

    @Autowired
    MisAuthenticationEntryPoint misAuthenticationEntryPoint;

    @Autowired
    MisAccessDeniedHandler misAccessDeniedHandler;

    @Autowired
    private CorsFilter corsFilter;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* 解决跨域 */
        http.cors();

        /* 在用户认证及退出过滤器前，配置跨域过滤器*/
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter, LogoutFilter.class);

        /* 禁用跨站请求伪造csrf */
        http.csrf().disable();

        /* 配置JWT token后禁用session */
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //禁用session

        /* 配置认证授权  注释*/
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, SECURITY_IGNORE_RESOURCE_URL).permitAll() // 资源白名单
//                .antMatchers(SECURITY_IGNORE_API_PATH).permitAll() // API白名单
//                .anyRequest() //任何其它请求
//                .authenticated(); //都需要身份认证

//        /* 配置登录 */
//        http.formLogin() //使用表单认证方式
////                .loginProcessingUrl("/auth/login")//配置默认登录入口
//                .successHandler(misAuthenticationSuccessHandler) // 设置登录成功处理器
//                .failureHandler(misAuthenticationFailureHandler); // 设置登录失败处理器

//        /* 配置退出 */
//        http.logout()
//                .logoutUrl("/logout") // 设定退出处理路径
//                .logoutSuccessHandler(misLogoutSuccessHandler); // 设置退出成功处理器

        /*
         * 配置异常处理
         * 配置authenticationEntryPoint后无法跳转Spring Security默认登录页测试,需使用postman测试
         * !!!!!!!!使用postman测试一定要使用post提交!!!!!!!!!!!!!!!!!!!   注释
         */
//        http.exceptionHandling().authenticationEntryPoint(misAuthenticationEntryPoint); //匿名无法访问资源，401
//        http.exceptionHandling().accessDeniedHandler(misAccessDeniedHandler); // 认证通过但未授权，403

    }

    /**
     * 自定义用户认证服务类
     */
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * 配置密码加密
     * @return
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // 配置数据库用户认证
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());

    }
}
