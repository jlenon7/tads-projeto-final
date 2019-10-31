package com.flashcursos.infrastructure;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.flashcursos.application.configuration.settings.AppSettings;


/**
 *
 */
@Component
public class Mailer
{
	/**
	 *
	 */
	private static final String TEMPLATE = "mail/template";
	private static final String LOGO_RESOURCE = "logo";
	private static final String LOGO_PATH = "META-INF/resources/static/images/logo.png";// caminho a partir de src/main/resources

	/**
	 *
	 */
	@Autowired
	private final JavaMailSender mailSender;

	/**
	 *
	 */
	private final TemplateEngine templateEngine;

	/**
	 *
	 */
	private final AppSettings appSettings;

	/**
	 *
	 * @param mailSender
	 * @param templateEngine
	 */
	public Mailer( JavaMailSender mailSender, TemplateEngine templateEngine, AppSettings appSettings )
	{
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
		this.appSettings = appSettings;
	}

	/**
	 * Enviar e-mail
	 * @param template
	 * @param context
	 * @param preparer
	 * @return
	 */
	public Future<Void> sendMessage( String template, Context context, MessagePreparer preparer )
	{
		MimeMessagePreparator actualPreparator = mimeMessage ->
		{
			MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true, "UTF-8" );
			preparer.prepare( helper, mimeMessage );
			setContextVariables( context );
			context.setVariable( "template", template );
			String content = templateEngine.process( TEMPLATE, context );
			helper.setText( content, true );
			if ( LOGO_PATH != null )
			{
				helper.addInline( LOGO_RESOURCE, new ClassPathResource( LOGO_PATH ) );
			}
		};
		mailSender.send(actualPreparator);
		return new AsyncResult<>( null );
	}

	/**
	 * Setar variáveis do context, aqui setamos as variáveis utilizada no rodapé do template.html
	 * @param context
	 */
	private void setContextVariables( Context context )
	{
		/**
		 * url para todos os e-mail
		 */
		context.setVariable( "url", appSettings.getExternalUrl() );

		/**
		 * Rodapé padrão para todos os e-mail
		 */
		context.setVariable( "systemName", "Minha Aplicação" );
		context.setVariable( "currentYear", LocalDateTime.now().getYear() );
	}

	/**
	 * Interface preparadora do e-mail.
	 */
	@FunctionalInterface
	public interface MessagePreparer
	{
		void prepare( MimeMessageHelper messageHelper, MimeMessage mimeMessage ) throws Exception;
	}
}
