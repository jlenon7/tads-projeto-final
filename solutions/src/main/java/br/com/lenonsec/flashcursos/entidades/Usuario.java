package br.com.lenonsec.flashcursos.entidades;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public abstract class Usuario extends AbstractEntity implements UserDetails{
	private static final long serialVersionUID = -6624327477248695198L;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Entidades😎👌😎👌😎👌😎👌😎👌😎👌😎
	 */
	
	@NotBlank
	@Column(nullable = false, length = 100)
	@Size(max = 100)
	private String nome;
	
	@Column(unique = true, nullable = false, length = 15)
	@NotBlank
	private String cpf;
	
	@NotBlank
	private LocalDate nascimento;
	
	@NotBlank
	@Column(nullable = false, length = 100, unique = true)
	@Size(max = 100)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Column(nullable = false, length = 100)
	@Size(max = 100)
	private String senha;
	
	@Column(unique = true, nullable = false, length = 20)
	@NotBlank
	private String celular;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private TipoUsuarioEnum tipoUsuario;
	
	@NotNull
	@Column(nullable = false)
	private Boolean disponivel;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Métodos😎👌😎👌😎👌😎👌😎👌😎👌😎👌
	 */
	
	/**
	 * Token para resetar o password
	 */
	private String passwordResetToken;

	/**
	 * Data que expira o token de resetar o password
	 */
	private OffsetDateTime passwordResetTokenExpiration;
	
	/**
	 * Token para ativar a conta
	 */
	private String accountActivateToken;

	/**
	 * Data que expira o token de ativar a conta
	 */
	private OffsetDateTime accountActivateTokenExpiration;
	
	/**
	 * 
	 */
	@Column(nullable = false)
	private Boolean ativo;

	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		if ( this.tipoUsuario == null )
		{
			return null;
		}
		
		authorities.addAll( this.tipoUsuario.getAuthorities() );

		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.ativo;
	}
	
	public void generatePasswordResetToken()
	{
		this.passwordResetToken = UUID.randomUUID().toString();
		this.passwordResetTokenExpiration = OffsetDateTime.now().plusDays( 1 );

	}
	
	public void generateAccountActivateToken()
	{
		this.accountActivateToken = UUID.randomUUID().toString();
		this.accountActivateTokenExpiration = OffsetDateTime.now().plusDays( 1 );

	}
	
	public void generatePassword()
	{
		this.senha = UUID.randomUUID().toString();

	}

}
