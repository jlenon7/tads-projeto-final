package com.flashcursos.model.entity;


import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.Transient;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(unique = true, nullable = false, length = 15)
	@NotBlank
	private String cpf;
	
	private LocalDate nascimento;
	
	@NotBlank
	@Column(nullable = false, length = 100, unique = true)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Column(nullable = false, length = 100)
	private String senha;
	
	@Column(unique = true, nullable = false, length = 20)
	@NotBlank
	private String celular;
	
	@Enumerated( EnumType.ORDINAL )
	private TipoUsuarioEnum tipousuario;
	
	@NotNull
	@Column(nullable = false)
	private Boolean disponivel;
	
	public Usuario(Long id) {
		super.setId(id);
	}
	
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
	
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		if ( this.tipousuario == null )
		{
			return null;
		}
		
		authorities.addAll( this.tipousuario.getAuthorities() );

		return authorities;
	}
	
	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return senha;
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
		return this.disponivel;
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

