package com.project.customer.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//as per madhura ma'am
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
   @Autowired
   private JwtTokenHelper jwtTokenHelper;

   @Autowired
   private JWTRequestFilter jwtRequestFilter;

   @Bean
   public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
       http
               .exceptionHandling()
               .authenticationEntryPoint((request, response, authException) ->
                       response.sendError(HttpStatus.UNAUTHORIZED.value(), "Not yet Authenticated"))
               .and()
               .csrf()
               .disable()
               .authorizeRequests()
               .antMatchers(
                       "/api/auth/login",
                       "/api/auth/register",
                        "/swagger*/**",
                         "/v*/api-docs/**"
               ).permitAll()
               .antMatchers("/products/{productId}/bid").hasRole("CUSTOMER")
               .antMatchers("/product/delete/{id}").hasRole("ADMIN")
               .anyRequest()
               .authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
   }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
       return config.getAuthenticationManager();
    }
}
