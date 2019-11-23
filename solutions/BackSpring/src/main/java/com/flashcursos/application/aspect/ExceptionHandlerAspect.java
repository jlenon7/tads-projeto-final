package com.flashcursos.application.aspect;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExceptionHandlerAspect
{
	/**
	 *
	 */
	private static final Logger LOG = Logger.getLogger( ExceptionHandlerAspect.class.getName() );

	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 *
	 */
	@Autowired
	private MessageSource messageSource;

	/*-------------------------------------------------------------------
	 * 		 					  ASPECTS
	 *-------------------------------------------------------------------*/
	//---------
	// Database
	//---------

	/**
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "within(@org.springframework.stereotype.Service *)", throwing = "exception")
	public void handleException( JoinPoint joinPoint, org.springframework.dao.DuplicateKeyException exception )
	{
		throw new DuplicateKeyException( "Chave primário duplicada." );
	}

	/**
	 * Trata exceções geradas pelo Hibernate antes de enviar para o banco
	 *
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "within(@org.springframework.stereotype.Service *)", throwing = "exception")
	public void handleException( JoinPoint joinPoint, javax.validation.ConstraintViolationException exception )
	{
		ArrayList<String> messages = new ArrayList<>();
		for ( ConstraintViolation<?> constraint : exception.getConstraintViolations() )
		{
			String annotationType = constraint.getConstraintDescriptor().getAnnotation().annotationType().getName();
			String attributeLabel = constraint.getPropertyPath().toString();
			switch ( annotationType )
			{
				case "javax.validation.constraints.NotNull":
					messages.add("O campo "+ attributeLabel +" não pode ser nullo.");
					break;
				case "org.hibernate.validator.constraints.NotEmpty":
					messages.add("O campo "+ attributeLabel +" não pode ser vazio.");
					break;
				case "org.hibernate.validator.constraints.Length":
					messages.add("O campo "+ attributeLabel +" deve ter no máximo " + constraint.getConstraintDescriptor().getAttributes().get( "max" ) + " caracteres.");
					break;
				case "javax.validation.constraints.Size":
					messages.add("O campo "+ attributeLabel +" deve ter no máximo " + constraint.getConstraintDescriptor().getAttributes().get( "max" ) + " dígitos.");
					break;
			}
		}

		throw new ValidationException( String.join( "\n", messages ), exception );
	}


	/**
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "within(@org.springframework.stereotype.Service *)", throwing = "exception")
	public void handleException( JoinPoint joinPoint, org.springframework.dao.EmptyResultDataAccessException exception )
	{
		throw new EmptyResultDataAccessException( "Nenhum resultado encontrado", 1 );
	}

	/**
	 * Trata exceções de Constraint geradas pelo PostgreSQL
	 *
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "within(@org.springframework.stereotype.Service *)", throwing = "exception")
	public void handleException( JoinPoint joinPoint, org.springframework.dao.DataIntegrityViolationException exception )
	{
		//Caso a exceção já tenha sido interceptada por outro Aspecto deve ser ignorada
		if ( exception.getStackTrace()[0].toString().contains( "ExceptionHandlerAspect" ) || exception.getCause() == null )
		{
			return;
		}

		if ( exception.getCause() instanceof ConstraintViolationException )
		{
			final ConstraintViolationException cause = (ConstraintViolationException) exception.getCause();
			final PSQLException sqlException = (PSQLException) cause.getSQLException();

			final String message = sqlException.getServerErrorMessage().getDetail();

			String key;
			//Verifica o código do erro gerado pelo PostgreSQL
			switch ( cause.getSQLState() )
			{
				case "23503": // foreign_key_violation
				{
					key = message.substring( message.indexOf( '"' ) + 1, message.indexOf( '.' ) - 1 );
					throw new DataIntegrityViolationException( "Não foi possível realizar a operação pois esse registro está referenciado em " + key );
				}
				case "23505": // unique_violation
				{
					key = message.substring( message.indexOf( '(' ) + 1, message.indexOf( ')' ) );
					if ( key.startsWith( "lower(" ) )
					{
						key = key.replace( "lower(", "" );
						key = key.replace( "::text", "" );
					}
					throw new DataIntegrityViolationException( "O campo " + key + " informado já existe." );
				}
				case "23502": // not_null_violation
				{
					LOG.info( message );
					LOG.info( "Not null violation." );
					key = cause.getConstraintName();
					throw new DataIntegrityViolationException( "Por favor preencha o campo " + key );
				}
				default:
				{
					throw new DataIntegrityViolationException( "O campo " + cause.getSQLState() + " já existe." );
				}
			}
		}

		throw new DataIntegrityViolationException( "Não foi possível realizar a operação pois ocorreu um problema de integridade nos dados." );
	}

	//---------
	// Segurança
	//---------

	/**
	 * Trata exceções de acesso negado
	 *
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "within(@org.springframework.stereotype.Service *)", throwing = "exception")
	public void handleException( JoinPoint joinPoint, org.springframework.security.access.AccessDeniedException exception )
	{
		throw new AccessDeniedException( "Acesso negado." );
	}
}
