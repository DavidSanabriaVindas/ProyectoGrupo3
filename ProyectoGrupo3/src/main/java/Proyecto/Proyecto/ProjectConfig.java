package Proyecto.Proyecto;

import java.util.Locale;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/inicio_Sesion").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((request) -> request
                .requestMatchers("/","/index","/inicio_Sesion","/errores/**",
                        "/registro/**","/disponibilidad/**","/contacto/**",
                        "/js/**","/css/**","/images/**","/webjars/**")
                    .permitAll()
                .requestMatchers(
                    "/reservas/**"
                ).hasAnyRole("ADMIN", "USER")
            )
            .formLogin((form) -> form
                .loginPage("/inicio_Sesion")
                .defaultSuccessUrl("/reservas/listado", true)
                .permitAll())
            .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        JdbcUserDetailsManager jdbcUsers = new JdbcUserDetailsManager(dataSource);
        
        // Consulta para obtener usuarios (username, password, enabled)
        jdbcUsers.setUsersByUsernameQuery(
            "SELECT username, password, activo FROM usuario WHERE username = ?");
        
        // Consulta para obtener roles (username, authority)
        jdbcUsers.setAuthoritiesByUsernameQuery(
            "SELECT u.username, r.nombre FROM rol r " +
            "JOIN usuario u ON u.id_usuario = r.id_usuario " +
            "WHERE u.username = ?");
        
        return jdbcUsers;
    }
}