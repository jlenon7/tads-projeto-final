package br.com.lenonsec.flashcursos.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("app")
public class AppSettings
{

	/**
	 * URL externa da aplicação.
	 */
	private String externalUrl;

	/**
	 * Remetente padrão para envio de emails.
	 */
	private String mailFrom;

	/**
	 * Tempo de expiração do token de redefinição de senha.
	 */
	private int passwordTokenExpirationHours;
}
