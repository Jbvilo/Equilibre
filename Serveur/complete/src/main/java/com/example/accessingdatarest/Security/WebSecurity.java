package com.example.accessingdatarest.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.example.accessingdatarest.Security.SecurityConstants.SIGN_UP_URL;

import java.util.Arrays;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
   private BCryptPasswordEncoder bCryptPasswordEncoder;


  public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
      // remove csrf and state in session because in jwt we do not need them
     .cors()
      .and()
      .csrf().disable()
     .authorizeRequests()
      // configure access rules
      .antMatchers(HttpMethod.POST, "/api/login").permitAll()
      .antMatchers(HttpMethod.OPTIONS, "/people").permitAll()
      .antMatchers(HttpMethod.POST, "/api/sign-up").permitAll()
      .antMatchers(HttpMethod.PUT, "/people/**").permitAll()
      .anyRequest().permitAll()
      .and()
   
      // add jwt filters (1. authentication, 2. authorization)
      .addFilter(new JWTAuthenticationFilter(authenticationManager()))
      //.addFilter(new JWTAuthorizationFilter(authenticationManager()))
      
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     // .antMatchers("/api/public/management/*").hasRole("MANAGER")
     // .antMatchers("/api/public/admin/*").hasRole("ADMIN")
      //.anyRequest().authenticated();
}

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.applyPermitDefaultValues();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PATCH"));



    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    
    
    
    source.registerCorsConfiguration("/**", configuration);   
    //source.registerCorsConfiguration("/**", new CorsConfiguration().addAllowedMethod(HttpMethod.PUT));
    return source;
  }


  
}



