package br.com.lenonsec.flashcursos.repository;

import java.util.concurrent.Future;

import br.com.lenonsec.flashcursos.entidades.Usuario;


/**
 *
 */
public interface IAccountMailRepository
{
	/*-------------------------------------------------------------------
     *                          BEHAVIORS
     *-------------------------------------------------------------------*/

	/**
	 * Envia email notificando criação de conta do usuário
	 * @param usuario
	 * @return
	 */
	Future<Void> sendNewUserAccount( Usuario usuario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendPasswordReseted( Usuario usuario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendPasswordResetNotice( Usuario usuario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendAccountActivated( Usuario usuario );

	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendAccountInactivated( Usuario usuario );
	
	/**
	 *
	 * @param user
	 * @return
	 */
	Future<Void> sendResetPassword( Usuario usuario );

}