package com.app.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {
	

	@Bean
	 SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**","/public/**").permitAll()
		.requestMatchers( "/admin/**").hasRole("ADMIN")
		.requestMatchers( "/customer/**").hasAnyRole("ADMIN","USER")		
		.anyRequest().authenticated().and()
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin()
		.and()
		.httpBasic();

		return http.build();

	}
	
	

	
	
	 @Bean
	  public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .components(new Components().addSecuritySchemes("bearer-jwt",
	                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
	                    .in(SecurityScheme.In.HEADER).name("Authorization")))
	            .info(new Info().title("Bus Ease API").version("snapshot").description(
						"This is Bus Reservation Service app REST API using spring boot. \n We have used sping data jpa data operations and spring-security using JWT for user validation / authentication.")
						.termsOfService("8080/swagger-ui/index.html"))
	            .addSecurityItem(
	                    new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
	  }

	
	
	@Bean
	 PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	

}
