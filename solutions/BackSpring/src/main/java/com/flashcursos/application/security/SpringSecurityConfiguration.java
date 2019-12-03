package com.flashcursos.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplUserDetailService detail;
	
	private final AuthenticationFailureHandler authenticationFailureHandler;
	/**
	 *
	 */
	private final AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public SpringSecurityConfiguration( AuthenticationFailureHandler authenticationFailureHandler, AuthenticationSuccessHandler authenticationSuccessHandler )
	{
		this.authenticationFailureHandler = authenticationFailureHandler;
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeRequests()
			.antMatchers("/" ).fullyAuthenticated()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.usernameParameter( "email" )
			.passwordParameter( "password" )
			.loginPage( "/authentication" )
			.loginProcessingUrl( "/authenticate" )
			.failureHandler( this.authenticationFailureHandler )
			.successHandler( this.authenticationSuccessHandler )
			.permitAll()
			.and()
			.logout()
			.logoutUrl( "/logout" );
		http.csrf().disable().
		authorizeRequests().
		anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.defaultSuccessUrl("/index")
		.and().httpBasic().disable();*/
		
		http.cors().and().csrf().disable()
		.authorizeRequests()
			.antMatchers( "/api/*" ).permitAll();
	}
	
	protected void authenticationConfigure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detail)
		.passwordEncoder(this.encoder());
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}
}

