package br.com.lenonsec.flashcursos.entidades;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public enum TipoUsuarioEnum implements GrantedAuthority{

	ADMIN,
	PROFESSOR,
	ALUNO;
	
	public static final Integer ADMIN_VALUE 	= 0;
	public static final Integer ALUNO_VALUE 	= 1;
	public static final Integer PROFESSOR_VALUE = 2;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority()
	{
		return this.name();
	}

	/**
	 * @return
	 */
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		authorities.add( this );


		return authorities;
	}

}


