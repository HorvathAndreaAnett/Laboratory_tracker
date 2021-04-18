package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public WebSecurityConfig(@Autowired DataSource dataSource, AuthenticationSuccessHandler authenticationSuccessHandler) {
        super();
        this.dataSource = dataSource;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, 'true' as enabled from user where username=?")
                .authoritiesByUsernameQuery("select u.username, r.name " +
                        "from sd_lab1.user u, sd_lab1.role r " +
                        "where u.username = ? and r.id = (select rr.id from sd_lab1.role rr where rr.id=u.role_id) ");
    }

    @Override//for access to site config
    protected void configure(HttpSecurity http) throws Exception {
        //System.out.println(new BCryptPasswordEncoder().encode("blabla"));

        http.httpBasic().and().authorizeRequests()
                //.antMatchers("/teacher").hasAnyAuthority("TEACHER")
                //.antMatchers("/student").hasAnyAuthority("STUDENT")
                .antMatchers("/teacher/*").hasAnyAuthority("TEACHER")
                .antMatchers("/student/*").hasAnyAuthority("STUDENT")
                .antMatchers("/teacher/*").hasAnyRole("TEACHER")
                .antMatchers("/student/*").hasAnyRole("STUDENT")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                //.defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
        http.csrf().disable();
    }
}
