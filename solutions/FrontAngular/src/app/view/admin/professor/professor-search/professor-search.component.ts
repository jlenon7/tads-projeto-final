import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { Router, ActivatedRoute } from '@angular/router';
import { ProfessorService } from 'src/app/service/professor.service';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';
import { MessagesService } from 'src/app/service/messages.service';
import { TdDialogService } from '@covalent/core/dialogs';

@Component({
  selector: 'app-professor-search',
  templateUrl: './professor-search.component.html',
  styleUrls: ['./professor-search.component.css']
})
export class ProfessorSearchComponent implements OnInit {

   /**
   * Lista de professors a ser exibida
   */
  professores : Array<Professor>;

  /**
   * Construtor da classe
   * @param router
   * @param activatedRoute
   */
  constructor(private router: Router,
            private activatedRoute: ActivatedRoute,
            private professorService: ProfessorService,
<<<<<<< HEAD
            private messageService: MessagesService,
            private _dialogService: TdDialogService,
            private _viewContainerRef: ViewContainerRef) { 
=======
            private messageService: MessagesService) {
>>>>>>> 977a61a310f57068d58344577f2c8da9a25bd029
  }

  /**
   * Método que é executado ao carregar a classe
   */
  ngOnInit() {
    this.listar();
  }

  /**
   * Método que redireciona para cadastrar professor
   */
  navigateToNovo() {
    this.router.navigate(['cadastrar'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar, excluir ou visualizar professor
   * @param evento
   */
  navigateTo(evento) {
    console.log(evento.acaoRealizada);
    let id: number  = evento.professorSelecionadoId;
    if(evento.acaoRealizada == TipoAcaoValues[0]){
      this.router.navigate(['detalhes/'+id], { relativeTo: this.activatedRoute });
    }
    else if(evento.acaoRealizada == TipoAcaoValues[1]){
      this.router.navigate(['alterar/'+id], { relativeTo: this.activatedRoute });

    } else if(evento.acaoRealizada == TipoAcaoValues[2]){
      this.remover(id);
    }

  }

  /**
   * Método para listar os professors
   */
  listar(){
    this.professorService.listar().subscribe(dados => {
      this.professores = dados;
    },
    (error: any) => {
      console.log(error);
      console.log(error.error.message);
      this.messageService.toastError(error.error.message);
    });
  }

  remover(id: number){
<<<<<<< HEAD
    this.openRemoverConfirm(id);
  }

  openRemoverConfirm(id: number): void {
    this._dialogService.openConfirm({
      message: 'Tem certeza que deseja excluir esse professor?',
      disableClose: true, // defaults to false
      viewContainerRef: this._viewContainerRef, //OPTIONAL
      title: 'Excluir professor', //OPTIONAL, hides if not provided
      cancelButton: 'Não', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Sim', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.professorService.remover(id).subscribe(dados => {
          this.messageService.toastSuccess('Professor excluído com sucesso.');
          this.listar();
        },
        (error: any) => {
          console.log(error.error.message);
          this.messageService.toastError(error.error.message);

        });
      } else {
        // DO SOMETHING ELSE
      }
      
=======
    this.professorService.remover(id).subscribe(dados => {
      this.messageService.toastSuccess('Professor excluído com sucesso.');
      this.listar();
    },
    (error: any) => {
      console.log(error.error.message);
      this.messageService.toastError(error.error.message);

>>>>>>> 977a61a310f57068d58344577f2c8da9a25bd029
    });
  }
}
