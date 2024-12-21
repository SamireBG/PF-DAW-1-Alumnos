package pe.edu.cibertec.PF_DAW1_Alumnos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/alumnos/login").permitAll()
                        .requestMatchers("/alumnos/listar").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )

                .exceptionHandling( ex -> ex
                        .accessDeniedHandler((request,
                                              response,
                                              accessDeniedException) -> {
                            response.sendRedirect("/alumnos/restricted");
                        })
                )
                .formLogin(form -> form
                        .loginPage("/alumnos/login")
                        .defaultSuccessUrl("/alumnos/listar",false)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/alumnos/logout")
                        .logoutSuccessUrl("/alumnos/login?logout")
                        .permitAll()
                );
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService(){
        return username -> User.builder()
                .username("Samire")
                .password(passwordEncoder().encode("test123"))
                .roles("USER")
                .build();
    }

}
