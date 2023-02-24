package com.fdmgroup.helperdesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fdmgroup.helperdesk.security.HelperDetailsService;
import com.fdmgroup.helperdesk.security.TraineeDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Configuration
	@Order(1)
	public static class HelperConfig{
		@Autowired
		private HelperDetailsService helperDetailsService;
		
		@Bean
		public AuthenticationProvider helperAuthProvider() {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(helperDetailsService);
			provider.setPasswordEncoder(passwordEncoder());
			return provider;
		}
		
		
		@Bean
	    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
	        http.authenticationProvider(helperAuthProvider());
	 
	        http.securityMatcher("/helper/**")
	            .authorizeHttpRequests().anyRequest().authenticated()
	            .and()
	            .formLogin()
	                .loginPage("/helper/login")
	                .usernameParameter("username")
	                .loginProcessingUrl("/helper/login")
	                .defaultSuccessUrl("/helper/home")
	                .permitAll()
	            .and()
	                .logout()
                		.invalidateHttpSession(true)
                		.clearAuthentication(true)
	                    .logoutUrl("/helper/logout")
	                    .logoutSuccessUrl("/");
	 
	        return http.build();
	    }
	}
	
	@Configuration
	@Order(2)
	public static class TraineeConfig{
		@Autowired
		private TraineeDetailsService traineeDetailsService;
		
		@Bean
		public AuthenticationProvider traineeAuthProvider() {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(traineeDetailsService);
			provider.setPasswordEncoder(passwordEncoder());
			return provider;
		}
		
		
		@Bean
	    public SecurityFilterChain traineeFilterChain(HttpSecurity http) throws Exception {
	        http.authenticationProvider(traineeAuthProvider());
	 
	        http.securityMatcher("/trainee/**")
	            .authorizeHttpRequests().anyRequest().authenticated()
	            .and()
	            .formLogin()
	                .loginPage("/trainee/login")
	                .usernameParameter("username")
	                .loginProcessingUrl("/trainee/login")
	                .defaultSuccessUrl("/trainee/home")
	                .permitAll()
	            .and()
	                .logout()
	                	.invalidateHttpSession(true)
	                	.clearAuthentication(true)
	                    .logoutUrl("/trainee/logout")
	                    .logoutSuccessUrl("/");
	 
	        return http.build();
	    }
	}
}
