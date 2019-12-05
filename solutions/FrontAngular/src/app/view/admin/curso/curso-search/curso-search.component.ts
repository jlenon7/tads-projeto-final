import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Curso } from 'src/app/model/curso';
import { Router, ActivatedRoute } from '@angular/router';
import { CursoService } from 'src/app/service/curso.service';
import { MessagesService } from 'src/app/service/messages.service';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';
import { TdDialogService } from '@covalent/core/dialogs';

@Component({
  selector: 'app-curso-search',
  templateUrl: './curso-search.component.html',
  styleUrls: ['./curso-search.component.css']
})
export class CursoSearchComponent implements OnInit {

   /**
   * Lista de cursos a ser exibida
   */
  cursos : Array<Curso>;

  /**
   * Construtor da classe
   * @param router 
   * @param activatedRoute 
   */
  constructor(private router: Router,
            private activatedRoute: ActivatedRoute,
            private cursoService: CursoService,
            private messageService: MessagesService,
            private _dialogService: TdDialogService,
            private _viewContainerRef: ViewContainerRef) { 
  }

  /**
   * Método que é executado ao carregar a classe
   */
  ngOnInit() {
    this.listar();
  }

  /**
   * Método que redireciona para cadastrar curso
   */
  navigateToNovo() {
    this.router.navigate(['cadastrar'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar, excluir ou visualizar curso
   * @param evento 
   */
  navigateTo(evento) {
    console.log(evento.acaoRealizada);
    let id: number  = evento.cursoSelecionadoId;
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
   * Método para listar os cursos
   */
  listar(){
    this.cursoService.listar().subscribe(dados => {
      this.cursos = dados;
    },
    (error: any) => {
      console.log(error);
      console.log(error.error.message);
      this.messageService.toastError(error.error.message);
    });
  }
    
  /**
   * Método para remover um curso
   */
  remover(id: number){
    this.openRemoverConfirm(id);
  }

  openRemoverConfirm(id: number): void {
    this._dialogService.openConfirm({
      message: 'Tem certeza que deseja excluir esse curso?',
      disableClose: true, // defaults to false
      viewContainerRef: this._viewContainerRef, //OPTIONAL
      title: 'Excluir curso', //OPTIONAL, hides if not provided
      cancelButton: 'Não', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Sim', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.cursoService.remover(id).subscribe(dados => {
          this.messageService.toastSuccess('Curso excluído com sucesso.');
          this.listar();
        },
        (error: any) => {
          console.log(error.error.message);
          this.messageService.toastError(error.error.message);

        });
      } else {
        // DO SOMETHING ELSE
      }
    });
  }
}
