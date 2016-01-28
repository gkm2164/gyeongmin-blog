package kr.pe.imarch.security;

import kr.pe.imarch.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by USER on 2016-01-28.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/admin/**").hasRole("USER")
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                .permitAll();
    }

    @Autowired private UserRepository ur;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        ur.findAll().forEach(u -> {
            try {
                auth.inMemoryAuthentication()
                        .withUser(u.getEmail()).password(u.getPassword()).roles("USER");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
