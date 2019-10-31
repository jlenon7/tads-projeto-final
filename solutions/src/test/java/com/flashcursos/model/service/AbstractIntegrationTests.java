package com.flashcursos.model.service;

<<<<<<< HEAD
import java.util.Locale;

=======
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flashcursos.SpringWebivApplication;
import com.flashcursos.TestApplication;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestApplication.class, SpringWebivApplication.class})
public abstract class AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
     *                           ATTRIBUTES
     *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
     *                           BEHAVIORS
     *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Before
	public void beforeTest()
	{
<<<<<<< HEAD
		Locale.setDefault( new Locale( "pt") );
=======
		
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
	}
}

