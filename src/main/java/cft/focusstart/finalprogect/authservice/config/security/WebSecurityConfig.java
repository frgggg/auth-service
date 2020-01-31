package cft.focusstart.finalprogect.authservice.config.security;

import cft.focusstart.finalprogect.authservice.model.SystemUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    protected WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user-registration").permitAll()
                .antMatchers("/user/**").hasRole(SystemUserRole.USER.toString())
                .antMatchers("/airline/**").hasRole(SystemUserRole.AIRLINE.toString())
                .anyRequest().hasRole(SystemUserRole.ADMIN.toString())
                .and()
                /*.formLogin()
                .loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/
                .formLogin().disable()
                .csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder())
                .usersByUsernameQuery("select " +
                        "login as username, " +
                        "password as password, " +
                        //"is_active as enabled " +
                        "1 as enabled " +
                        "from system_user " +
                        "where login=?")
                .authoritiesByUsernameQuery("select " +
                        "login as username, " +
                        "role as authority " +
                        "from system_user " +
                        "where login=?");
    }
}
