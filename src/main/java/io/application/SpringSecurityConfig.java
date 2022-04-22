package io.application;

import io.application.voter.Voter;
import io.application.voter.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Order(1)
    @Configuration
    public class Config1 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			super.configure(auth);
            auth.inMemoryAuthentication()
                    .withUser("ADMIN")
                    .password("sarthakdandriyal3199@gmail.com")
                    .roles("ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.requestMatchers()
                    .antMatchers("/admin/**").and()
                    .authorizeRequests().anyRequest().hasRole("ADMIN")
                    .and().formLogin()
                    .and().httpBasic();
        }


    }

    @Order(2)
    @Configuration
    public class Config2 extends WebSecurityConfigurerAdapter {
        @Autowired
        private VoterService voterService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            super.configure(auth);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests()
                    .antMatchers("/").permitAll()  //dashboard  page will be permit to all user
                    .antMatchers("/user/**").hasRole("USER")//Only normal user can login
                    .and()
                    .formLogin()
                    .loginPage("/login")  //Loginform all can access ..
                    .defaultSuccessUrl("/generateOtp")
                    .failureUrl("/login?error")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();

        }

        @Bean
        public PasswordEncoder getPasswordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }
    }
}