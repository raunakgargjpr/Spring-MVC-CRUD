package com.raunak.springboot.thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailsManager
                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return theUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/employees/list").hasRole("EMPLOYEE")
                                .requestMatchers("/employees/showFormForAdd/**").hasRole("MANAGER")
                                .requestMatchers("/employees/showFormForUpdate/**").hasRole("MANAGER")
                                .requestMatchers("/employees/save/**").hasRole("MANAGER")
                                .requestMatchers("/employees/delete**").hasRole("ADMIN")
                                .anyRequest().authenticated()

                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage") // we have to create it
                                .loginProcessingUrl("/authenticateTheUser") // we get this for free!
                                .permitAll()
                )
                .logout(logout -> logout.permitAll().logoutSuccessUrl("/"))
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }
}
