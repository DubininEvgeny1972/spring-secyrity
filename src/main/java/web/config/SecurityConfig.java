package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import web.config.handler.LoginSuccessHandler;
import web.model.Role;
import web.model.User;
import web.service.CustomUserDetailsService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private String role;
    @Autowired
    UserService userService;

//    @Autowired
    private CustomUserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private LoginSuccessHandler loginSuccessHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(LoginSuccessHandler loginSuccessHandler, CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @PostConstruct
    public void startProject() {
        System.out.println("Hello!");
//        Role role1 = new Role("ROLE_VIP");
//        saveRole(role1);
//        Role role2 = new Role("ROLE_USER");
//        saveRole(role2);
//        User user1 = new User();
//        user1.setName("Bob");
//        user1.setLastName("Dillan");
//        user1.setAge((byte) 48);
//        user1.setLogin("444");
//        user1.setPassword("444");
//        role = "ROLE_USER";
//        userService.saveUser(user1, role);
//
//        Set<Role> roleFromUser1 = new HashSet<>();
//        roleFromUser1.add(role1);
//        user1.setRoles(roleFromUser1);
//        System.out.println(user1);
//
//        User user2 = new User();
//        user2.setName("Kirk");
//        user2.setLastName("Duglas");
//        user2.setAge((byte) 45);
//        user2.setLogin("222");
//        user2.setPassword("222");
//        Set<Role> roleFromUser2 = new HashSet<>();
//        roleFromUser1.add(role2);
//        roleFromUser2.add(role1);
//        user2.setRoles(roleFromUser1);
//        System.out.println(user2);
//        userDaoHiber.saveUser(user2);
//        userDaoHiber.saveUser(user1);

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
                .loginPage("/login")// указываем страницу с формой логина
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
        return NoOpPasswordEncoder.getInstance();
    }
}
