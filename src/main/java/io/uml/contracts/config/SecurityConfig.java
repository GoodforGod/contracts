package io.uml.contracts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static io.uml.contracts.util.WebControllerMapper.*;

/**
 * "default description"
 *
 * @author goodforgod
 * @since 10.09.2017
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";
    private static final String CLIENT = "CLIENT";

    private String uid = "admin";
    private String password = "1234";

    private String clientUid = "client";
    private String clientPassword = "1234";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(LOGIN, REGISTER, "/api/**",
                        "/css/**", "/static/css/**", "/webjars/bootstrap/**").permitAll()
                .antMatchers(SECURED + "/**", SWAGGER_DOC, SWAGGER_UI).hasAnyRole(ADMIN, CLIENT)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl(LOGIN + "?error=true")
                .defaultSuccessUrl(CONTRACTS, true)
                .loginPage(LOGIN)
                .usernameParameter("uid")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(uid).password("{noop}" + password).roles(ADMIN)
                .and()
                .withUser(clientUid).password("{noop}" + clientPassword).roles(CLIENT);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/css/**", "/webjars/bootstrap/**");
    }
}
