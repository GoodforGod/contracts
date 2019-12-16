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

    public static final String CLIENT_ID = "client";
    public static final String CLIENT_PASSWORD = "1234";

    public static final String LORD_UID = "lord";
    public static final String LORD_PASSWORD = "1234";

    public static final String GAMORA_UID = "gamora";
    public static final String GAMORA_PASSWORD = "1234";

    public static final String DRAKS_UID = "draks";
    public static final String DRAKS_PASSWORD = "1234";

    public static final String ROCKET_UID = "rocket";
    public static final String ROCKET_PASSWORD = "1234";

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
                .withUser(CLIENT_ID).password("{noop}" + CLIENT_PASSWORD).roles(ROLE_CLIENT)
                .and()
                .withUser(LORD_UID).password("{noop}" + LORD_PASSWORD).roles(ROLE_ADMIN)
                .and()
                .withUser(GAMORA_UID).password("{noop}" + GAMORA_PASSWORD).roles(ROLE_ADMIN)
                .and()
                .withUser(DRAKS_UID).password("{noop}" + DRAKS_PASSWORD).roles(ROLE_ADMIN)
                .and()
                .withUser(ROCKET_UID).password("{noop}" + ROCKET_PASSWORD).roles(ROLE_ADMIN);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/css/**", "/webjars/bootstrap/**");
    }
}
