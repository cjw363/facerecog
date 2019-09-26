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
    UserSecurityServiceImpl mUserSecurityService;
    @Autowired
    UrlFilterMetadataSource mUrlFilterMetadataSource;
    @Autowired
    UrlAccessDecisionManager mUrlAccessDecisionManager;
    @Autowired
    SecAccessDeniedHandler mSecAccessDeniedHandler;
    @Autowired
    SecFailureHandler mSecFailureHandler;
    @Autowired
    SecSuccessHandler mSecSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mUserSecurityService).passwordEncoder(new NoPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**","/user/nologin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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