package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import web.config.handler.LoginSuccessHandler;
import web.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private LoginSuccessHandler loginSuccessHandler; // класс, в котором описана логика перенаправления пользователей по ролям
    @Autowired
    public SecurityConfig(LoginSuccessHandler loginSuccessHandler, CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.formLogin()
                .loginPage("/")// указываем страницу с формой логина
                .successHandler(loginSuccessHandler)//указываем логику обработки при логине
                .loginProcessingUrl("/login")// указываем action с формы логина
                .usernameParameter("j_username")// Указываем параметры логина и пароля с формы логина
                .passwordParameter("j_password")
                .permitAll();// даем доступ к форме логина всем

        http.logout()
                .permitAll()// разрешаем делать логаут всем
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// указываем URL логаута
                .logoutSuccessUrl("/login?logout")// указываем URL при удачном логауте
                .and().csrf().disable(); //- попробуйте выяснить сами, что это даёт//выключаем кросс-доменную секьюрность (на этапе обучения неважна)

        http.authorizeRequests()
                .antMatchers("/").permitAll() // доступность всем
                .antMatchers("/login").anonymous()//страница аутентификации доступна всем
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
