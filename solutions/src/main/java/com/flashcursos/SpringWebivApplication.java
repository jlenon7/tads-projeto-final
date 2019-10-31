package com.flashcursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
<<<<<<< HEAD
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
=======
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class SpringWebivApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebivApplication.class, args);
	}
	
<<<<<<< HEAD
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
	
	/**
	 * @return
	 */
	@Bean
	public Validator validator()
	{
		return new LocalValidatorFactoryBean();
	}
	
=======
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

}
