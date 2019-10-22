package br.com.lenonsec.flashcursos.services;

import java.time.OffsetDateTime;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.lenonsec.flashcursos.application.security.RequestContext;
import br.com.lenonsec.flashcursos.entidades.Usuario;
import br.com.lenonsec.flashcursos.repository.IAccountMailRepository;
import br.com.lenonsec.flashcursos.repository.UsuarioRepository;
import br.com.lenonsec.flashcursos.settings.AppSettings;


@Service
@Transactional
public class UsuarioService
{

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * Password encoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	//Repositories
	/**
	 *
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 */
	@Autowired
	private IAccountMailRepository accountMailRepository;

	/**
	 *
	 */
	@Autowired
	private AppSettings appSettings;

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/

	/**
	 * Serviço para inserir um usuário
	 *
	 * @param usuario
	 * @return
	 */
	public Usuario cadastrarUsuario( Usuario usuario )
	{
		//seta o usuário como inativo
		usuario.setAtivo( false );
		
		//gera senha aleatória
		usuario.generatePassword();
		usuario.setSenha( this.passwordEncoder.encode( usuario.getPassword() ) );
		
		//gera um token para ativação da conta
		usuario.generateAccountActivateToken();
		
		usuario = this.usuarioRepository.save( usuario );
		try
		{
			this.accountMailRepository.sendNewUserAccount( usuario ).get();
		}
		catch ( MailSendException | InterruptedException | ExecutionException e )
		{
			e.printStackTrace();
		}
		return usuario;
	}
	
	

	
	/**
	 * Serviço para ativar um usuário para acesso a plataforma
	 * É chamado ao acessar o token accountActivateToken
	 * 
	 * @param senha
	 * @param confirmacaoSenha
	 * @param accountActivateToken
	 */
	public void ativarUsuario( String senha, String confirmacaoSenha, String accountActivateToken )
	{
		OffsetDateTime dateTime = OffsetDateTime.now();
		Assert.notNull( accountActivateToken, "Token inválido." );
		
		Assert.isTrue( senha.equals( confirmacaoSenha ),
				"As senhas não conferem." );
		
		Usuario usuarioByToken = this.usuarioRepository.findByAccountActivateToken( accountActivateToken ).orElse( null );
		Assert.notNull(usuarioByToken, "Token inválido");
		Assert.isTrue( usuarioByToken.getAccountActivateTokenExpiration().isAfter( dateTime ), "Token venceu." );
		
		
		usuarioByToken.setAtivo( true );
		usuarioByToken.setSenha( this.passwordEncoder.encode( senha ) );
		
		usuarioByToken = this.usuarioRepository.save( usuarioByToken );
		try
		{
			this.accountMailRepository.sendAccountActivated( usuarioByToken );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

	}
	

	/**
	 * Serviço que envia um e-mail para recuperar a senha do usuário
	 * Gera um token com um link para acesso a redefinição de senha
	 *
	 * @param email
	 */
	public void enviarEmailRecuperarSenhaUsuario( String email )
	{
		Usuario usuario = this.usuarioRepository.findByEmailIgnoreCase( email );

		Assert.notNull( usuario, "E-mail inválido." );

		usuario.generatePasswordResetToken();
		usuario.setPasswordResetTokenExpiration( OffsetDateTime.now().plusDays( 1 ) );
		usuario = this.usuarioRepository.save( usuario );

		try
		{
			this.accountMailRepository.sendResetPassword( usuario );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

	}


	/**
	 * Serviço para redifinir a senha do usuário
	 *
	 * @param senha
	 * @param confirmacaoSenha
	 * @param passwordResetToken
	 * @return
	 */
	public Usuario redefinirSenha( String senha, String confirmacaoSenha, String passwordResetToken )
	{
		OffsetDateTime dateTime = OffsetDateTime.now();
		Assert.notNull( passwordResetToken, "Token inválido." );
		Assert.isTrue( senha.equals( confirmacaoSenha ), "Senhas não conferem." );
		Usuario usuario = this.usuarioRepository.findByPasswordResetToken( passwordResetToken ).orElse( null );
		Assert.notNull(usuario, "Token inválido");
		Assert.isTrue( usuario.getPasswordResetTokenExpiration().isAfter( dateTime ), "Token venceu." );
		usuario.setSenha( this.passwordEncoder.encode( senha ) );
		return this.usuarioRepository.save( usuario );
	}

	

	/**
	 * Serviço para detalhar um usuário
	 *
	 * @param id
	 * @return
	 */
	public Usuario detalharUsuario( long id )
	{
		Usuario usuario = this.usuarioRepository.findById( id )
				.orElseThrow( () -> new IllegalArgumentException( "Nenhum usuário encontrado." ));
		
		return usuario;
	}

	
	
	/**
	 * Serviço que trás o usuário logado
	 *
	 * @return
	 */
	public Usuario getAuthenticatedUser()
	{
		return RequestContext.currentUser().orElse( null );
	}

	/**
	 * Serviço para "alterar minha conta"
	 *
	 * @param user
	 */
	public Usuario alterarMinhaConta( Usuario usuario, String password, String confirmationPassword )
	{
		if ( password != null && confirmationPassword != null )
		{
			//verificamos se não é ''
			Assert.hasText( password, "Informe sua senha." );
			Assert.hasText( confirmationPassword, "Informe a configuração de senha." );

			//verificamos a senha se é igual a confirmação
			Assert.isTrue( password.equals( confirmationPassword ),
					"As senhas não conferem." );
			usuario.setSenha( this.passwordEncoder.encode( password ) );
		}
		return this.usuarioRepository.save( usuario );
	}
}