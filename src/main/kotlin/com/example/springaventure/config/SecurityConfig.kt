package com.example.springaventure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun getEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain? {
        return httpSecurity
            .csrf { csrf: CsrfConfigurer<HttpSecurity> -> csrf.disable() }
            .formLogin { form: FormLoginConfigurer<HttpSecurity?> ->
                form
                    .loginPage("/login").defaultSuccessUrl("/accueil").failureUrl("/login")
                    .permitAll()
            }
            .logout { logout: LogoutConfigurer<HttpSecurity?> ->
                logout
                    .logoutUrl("/logout")
                    .permitAll()
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(
                        "/accueil",
                        "/inscription",
                        "/webjars/**",
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/favicon.ico"
                    ).permitAll()
                    //.requestMatchers(HttpMethod.POST, "/inscription")
                    //.permitAll() //Interdit la page si l'utilisateur n'est pas admin
                    .requestMatchers("/admin/**").hasAuthority("admin")
                    .requestMatchers("/joueur/**").hasAuthority( "joueur")
                    .anyRequest().authenticated()
            }
            .build()
    }
}