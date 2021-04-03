package com.uxteam.config;

import com.uxteam.service.MyUserDetialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetialService myUserDetialService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/views/v2/**").hasAuthority("V2")
//                .antMatchers("/views/v1/**").hasAuthority("V1")
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/logsite")
//                .loginProcessingUrl("/login")
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler(){
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
                        RequestCache requestCache = new HttpSessionRequestCache();
                        String url = null;
                        SavedRequest savedRequest = requestCache.getRequest(request,response);
                        if(savedRequest != null){
                            url = savedRequest.getRedirectUrl();
                        }
                        System.out.println(url);
                        if(url == null){
                            getRedirectStrategy().sendRedirect(request,response,"/");
                        }
                        super.onAuthenticationSuccess(request, response, authentication);
                    }
                });
    }
}
