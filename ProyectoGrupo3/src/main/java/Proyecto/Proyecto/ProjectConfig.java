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
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");  // Configura el parámetro 'lang' para cambiar el idioma
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((request) -> request
                .requestMatchers("/","/index","/inicio_Sesion","/errores/**",
                        "/registro/**","/disponibilidad/**","/contacto/**",
                        "/js/**","/css/**","/images/**","/webjars/**")
                    .permitAll()
                .requestMatchers(
                    "/reservas/nuevo","/reservas/guardar",
                    "/reservas/modificar/**","/reservas/eliminar/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                    "/reservas/listado"
                ).hasAnyRole("ADMIN", "USER")
            )
            .formLogin((form) -> form
                .loginPage("/inicio_Sesion").permitAll())
            .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
            .username("juan")
            .password("{noop}123")
            .roles("USER", "ADMIN")
            .build();
        UserDetails user = User.builder()
            .username("pedro")
            .password("{noop}789")
            .roles("USER")
            .build();

        if (dataSource != null) {
            JdbcUserDetailsManager jdbcUsers = new JdbcUserDetailsManager(dataSource);

            // Consulta corregida para obtener los usuarios
            jdbcUsers.setUsersByUsernameQuery(
                "SELECT username, password, activo FROM usuario WHERE username = ?");
            
            // Consulta corregida para obtener los roles
            jdbcUsers.setAuthoritiesByUsernameQuery(
                "SELECT r.nombre FROM rol r " +
                "JOIN usuario u ON u.id_usuario = r.id_usuario " +
                "WHERE u.username = ?");
            
            // Registrar los usuarios en memoria como respaldo si no existen en BD
            try {
                if (!jdbcUsers.userExists("juan")) {
                    jdbcUsers.createUser(admin);
                }
                if (!jdbcUsers.userExists("pedro")) {
                    jdbcUsers.createUser(user);
                }
            } catch (Exception e) {
                System.out.println("Error al configurar usuarios en BD: " + e.getMessage());
                return new InMemoryUserDetailsManager(admin, user);
            }
            
            return jdbcUsers;
        }
        
        return new InMemoryUserDetailsManager(admin, user);
    }
}