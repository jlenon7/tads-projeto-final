package br.com.lenonsec.flashcursos.application.security;

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
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().
		authorizeRequests().
		anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.defaultSuccessUrl("/index")
		.and().httpBasic().disable();
		
		/*http
		.authorizeRequests()
			.antMatchers( "/api/*" ).permitAll();*/
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
