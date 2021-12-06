package com.facerecog.config;

import com.facerecog.security.NoPasswordEncoder;
import com.facerecog.security.SecAccessDeniedHandler;
import com.facerecog.security.SecFailureHandler;
import com.facerecog.security.SecSuccessHandler;
import com.facerecog.security.UrlAccessDecisionManager;
import com.facerecog.security.UrlFilterMetadataSource;
import com.facerecog.service.impl.UserSecurityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSecurityServiceImpl mUserSecurityService;//loadUserByUsername 返回UserDetails
    @Autowired
    UrlFilterMetadataSource mUrlFilterMetadataSource;//该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色
    @Autowired
    UrlAccessDecisionManager mUrlAccessDecisionManager;//根据用户角色和url需要角色匹配，decide，不符合抛出AccessDeniedException
    @Autowired
    SecAccessDeniedHandler mSecAccessDeniedHandler;//捕获AccessDeniedException权限不足，返回结果给用户
    @Autowired
    SecFailureHandler mSecFailureHandler;//登录失败处理，主要判断用户名密码错误，或被禁用，返回结果给用户
    @Autowired
    SecSuccessHandler mSecSuccessHandler;//登录成功处理

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mUserSecurityService).passwordEncoder(new NoPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**","/user/nologin","/ws");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests所有security全注解配置实现的开端，表示开始说明需要的权限。
        //需要的权限分量部分，第一部分，是拦截的路径，第二部分访问该路径需要的权限。
        //antMatchers表示什么路径，permitAll任何权限都可以访问，直接放行所有
        //anyRequest任何的请求，authenticated认证后才能访问
        //and().csrf().disable()固定写法，表示crsf拦截失效。crsf强大的防攻击

        http.cors().and().csrf().disable().authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(mUrlFilterMetadataSource);
                        o.setAccessDecisionManager(mUrlAccessDecisionManager);
                        return o;
                    }
                })
          .and().formLogin().loginProcessingUrl("/user/login").usernameParameter("name").passwordParameter("password").permitAll().failureHandler(mSecFailureHandler).successHandler(mSecSuccessHandler)
          .and().logout().permitAll()
          .and().sessionManagement().invalidSessionUrl("/user/nologin")
          .and().csrf().disable().exceptionHandling().authenticationEntryPoint((request, response, e) -> {
              //未登录或者找不到资源 默认处理
            request.getRequestDispatcher("/user/nologin").forward(request, response);
        }).accessDeniedHandler(mSecAccessDeniedHandler);
    }
}
