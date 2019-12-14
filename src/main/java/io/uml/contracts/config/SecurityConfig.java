package io.uml.contracts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static io.uml.contracts.config.WebMapper.*;

/**
 * "default description"
 *
 * @author goodforgod
 * @since 10.09.2017
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_CLIENT = "CLIENT";

    public static final String ADMIN_ID = "admin";
    public static final String ADMIN_PASSWORD = "1234";

    public static final String CLIENT_UID = "client";
    public static final String CLIENT_PASSWORD = "1234";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(LOGIN, REGISTER,
                        "/static/css/**").permitAll()
                .antMatchers(SECURED + "/**", SWAGGER_DOC, SWAGGER_UI).hasAnyRole(ROLE_ADMIN, ROLE_CLIENT)
                .anyRequest().authenticated()
                .and()
                .csrf()
                .ignoringAntMatchers("/logout")
                .and()
                .formLogin()
                .failureUrl(LOGIN + "?error=true")
                .defaultSuccessUrl(CONTRACT_TABLE)
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
                .withUser(ADMIN_ID).password("{noop}" + ADMIN_PASSWORD).roles(ROLE_ADMIN)
                .and()
                .withUser(CLIENT_UID).password("{noop}" + CLIENT_PASSWORD).roles(ROLE_CLIENT);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/css/**", "/webjars/bootstrap/**");
    }
}
