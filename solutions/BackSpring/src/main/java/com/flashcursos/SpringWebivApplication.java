package com.flashcursos;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class SpringWebivApplication {
	
	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebivApplication.class, args);
	}
	
	/**
	 * @return
	 */
	@Bean
	public MessageSource messageSource()
	{
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setAlwaysUseMessageFormat( true );
		messageSource.setDefaultEncoding( "UTF-8" );
		messageSource.setBasenames( "classpath:i18n/exceptions" );
		return messageSource;
	}
	
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}
	

}
