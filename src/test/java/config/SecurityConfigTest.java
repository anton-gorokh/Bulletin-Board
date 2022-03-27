package config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/login*").permitAll()
 .mvcMatchers("/security/action/user").hasRole("USER")
                 .mvcMatchers("/security/action/admin").hasRole("ADMIN")

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/user/hello")
                .and()
                .logout();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("John")
                .password("$2a$10$1eynDv3xVtnUXhnfPota7e8wWfo7ReiL6.dNR80TLTB5yDT0SpFsq")//123
                .roles("USER")
                .and()
                .withUser("Ben")
                .password("$2a$10$VB0VVEzgiqyxfRtPFFh7h.J/i/wZuFNN5IulsnOUfkB.yHlBACKti")//123456
                .roles("ADMIN");
    }*/
}
