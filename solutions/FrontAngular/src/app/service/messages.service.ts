import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from "@angular/material";

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

    /**
   * 
   * @param snackBarService 
   * @param translate 
   */
  constructor( 
    public snackBarService: MatSnackBar,
  )
  {  }

  /**
   * Retorna uma mensagem de sucesso para o usuário.
   * @param message
   * @param duration
   */
  public toastSuccess( message: string, duration = 5000 )
  {
    let config = new MatSnackBarConfig();
    config.duration = duration;
    config.panelClass = ['toast-success', 'toast'];

    this.snackBarService.open( message, '', config );
  }

  /**
   * Retorna uma mensagem de erro para o usuário.
   * @param message
   * @param duration
   */
  public toastError( message: string, duration = 5000 )
  {
    let config = new MatSnackBarConfig();
    config.duration = duration;
    config.panelClass = ['toast-error', 'toast'];

    this.snackBarService.open( message, '', config );
  }

  /**
   * Retorna uma mensagem de alerta para o usuário.
   * @param message
   * @param duration
   */
  public toastWarnning( message: string, duration = 5000 )
  {
    let config = new MatSnackBarConfig();
    config.duration = duration;
    config.panelClass = ['toast-warnning', 'toast'];

    this.snackBarService.open( message, '', config );
  }


}
