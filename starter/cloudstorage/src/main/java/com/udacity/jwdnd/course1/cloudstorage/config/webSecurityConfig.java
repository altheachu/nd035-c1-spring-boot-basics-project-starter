package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/loginDo","/signup/**","/css/**", "/js/**", "/h2/**")
                .permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login").permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);

        http.logout(logout -> logout
                .logoutUrl("home/logout")
                .logoutSuccessUrl("/login"));

        http.csrf()
                .ignoringAntMatchers("/h2/**");

        http.headers().frameOptions().sameOrigin();
    }

}
