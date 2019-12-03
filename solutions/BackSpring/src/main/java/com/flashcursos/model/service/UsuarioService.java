package com.flashcursos.model.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flashcursos.model.entity.Usuario;
import com.flashcursos.model.repository.UsuarioRepository;
import com.flashcursos.model.repository.IAccountMailRepository;
import com.flashcursos.application.security.RequestContext;


@Service
@Transactional
public class UsuarioService {
	
	/*
	 *  Atributos
	 */
	
	// Password encoder
	@Autowired
	private PasswordEncoder passwordEncoder;	
	// Repositories
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private IAccountMailRepository accountMailRepository;
	
	/*
	 *  Services
	 */
	
	/**
	 * Serviço para inserir um novo usuario
	 * @param usuario
	 * @return
	 */
	public Usuario cadastrarUsuario(Usuario usuario)
	{
		// Setando o usuário como inátivo
		usuario.setDisponivel(false);
		
		// Gerando senha aleatoria
		usuario.generatePassword();
		usuario.setSenha(this.passwordEncoder.encode(usuario.getPassword()));
		
		// Gerando token de ativação da conta
		usuario.generateAccountActivateToken();
		
		usuario = this.usuarioRepository.save(usuario);
		try
		{
			this.accountMailRepository.sendNewUserAccount(usuario).get();
		}
		catch (MailSendException | InterruptedException | ExecutionException e)
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
	public void ativarUsuario(String senha, String confirmacaoSenha, String accountActivateToken)
	{
		// Validaçãoes do token e da senha
		OffsetDateTime dateTime = OffsetDateTime.now();
		Assert.notNull(accountActivateToken, "Token inválido.");
		
		Assert.isTrue(senha.equals(confirmacaoSenha),
				"As senhas não conferem.");
		
		Usuario usuarioByToken = this.usuarioRepository.findByAccountActivateToken(accountActivateToken).orElse(null);
		Assert.notNull(usuarioByToken, "Token inválido");
		Assert.isTrue(usuarioByToken.getAccountActivateTokenExpiration().isAfter(dateTime), "Token venceu.");
		
		
		// Setando o usuário como disponivel e criptografando a senha dele
		usuarioByToken.setDisponivel(true);
		usuarioByToken.setSenha(this.passwordEncoder.encode(senha));
		
		usuarioByToken = this.usuarioRepository.save(usuarioByToken);
		try
		{
			this.accountMailRepository.sendAccountActivated(usuarioByToken);
		}
		catch (Exception e)
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
	public void enviarEmailRecuperarSenhaUsuario(String email)
	{
		// Verificando o email indicado
		Usuario usuario = this.usuarioRepository.findByEmailIgnoreCase(email);

		Assert.notNull(usuario, "E-mail inválido.");
		
		// Gerando um token de reset de senha e colocando um limite de tempo de uso
		usuario.generatePasswordResetToken();
		usuario.setPasswordResetTokenExpiration(OffsetDateTime.now().plusDays(1));
		usuario = this.usuarioRepository.save(usuario);

		try
		{
			this.accountMailRepository.sendResetPassword(usuario);
		}
		catch (Exception e)
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
	public Usuario redefinirSenha(String senha, String confirmacaoSenha, String passwordResetToken)
	{
		OffsetDateTime dateTime = OffsetDateTime.now();
		Assert.notNull(passwordResetToken, "Token inválido.");
		Assert.isTrue(senha.equals(confirmacaoSenha), "Senhas não conferem.");
		Usuario usuario = this.usuarioRepository.findByPasswordResetToken(passwordResetToken).orElse(null);
		Assert.notNull(usuario, "Token inválido");
		Assert.isTrue(usuario.getPasswordResetTokenExpiration().isAfter(dateTime), "Token venceu.");
		usuario.setSenha(this.passwordEncoder.encode(senha));
		return this.usuarioRepository.save(usuario);
	}
	
	/**
	 * Serviço que trás o usuário logado
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
	public Usuario alterarMinhaConta(Usuario usuario, String password, String confirmationPassword)
	{
		if (password != null && confirmationPassword != null)
		{
			// Verificamos se não é ''
			Assert.hasText(password, "Informe sua senha.");
			Assert.hasText(confirmationPassword, "Informe a configuração de senha.");

			// Verificamos a senha se é igual a confirmação
			Assert.isTrue(password.equals(confirmationPassword),
					"As senhas não conferem.");
			usuario.setSenha(this.passwordEncoder.encode(password));
		}
		return this.usuarioRepository.save(usuario);
	}
	
	/**
	 * Serviço para detalhar um usuário
	 *
	 * @param id
	 * @return
	 */
	public Usuario detalharUsuario(long id)
	{
		Usuario usuario = this.usuarioRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Nenhum usuário encontrado."));
		
		return usuario;
	}
	
	/**
	 * Serviço para listar os usuarios cadastrados
	 * @return
	 */
	public List<Usuario> listarUsuarios(){
		return this.usuarioRepository.findAll();
	}
	
	/**
	 * Serviço para remover o cadastro de um usuario
	 * @param id
	 * @return
	 */
	public void removerUsuario(long id) {
		this.usuarioRepository.deleteById(id);
	}
}
