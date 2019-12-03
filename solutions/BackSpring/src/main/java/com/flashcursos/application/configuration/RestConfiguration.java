package com.flashcursos.application.configuration;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

/**
 *
 */
@Configuration
public class RestConfiguration implements Jackson2ObjectMapperBuilderCustomizer
{



	@Override
	public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {

	    Hibernate5Module hibernateModule = new Hibernate5Module();

	    hibernateModule.configure(Feature.FORCE_LAZY_LOADING, true);

	    jacksonObjectMapperBuilder.modules(hibernateModule);
	    
	    jacksonObjectMapperBuilder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    jacksonObjectMapperBuilder.featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		
	}
	
	
}
