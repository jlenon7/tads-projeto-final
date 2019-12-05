import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Matricula } from 'src/app/model/matricula';
import { Router, ActivatedRoute } from '@angular/router';
import { MatriculaService } from 'src/app/service/matricula.service';
import { MessagesService } from 'src/app/service/messages.service';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';
import { TdDialogService } from '@covalent/core/dialogs';

@Component({
  selector: 'app-matricula-search',
  templateUrl: './matricula-search.component.html',
  styleUrls: ['./matricula-search.component.css']
})
export class MatriculaSearchComponent implements OnInit {

   /**
   * Lista de matriculas a ser exibida
   */
  matriculas : Array<Matricula>;

  /**
   * Construtor da classe
   * @param router 
   * @param activatedRoute 
   */
  constructor(private router: Router,
            private activatedRoute: ActivatedRoute,
            private matriculaService: MatriculaService,
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
   * Método que redireciona para cadastrar matricula
   */
  navigateToNovo() {
    this.router.navigate(['cadastrar'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar, excluir ou visualizar matricula
   * @param evento 
   */
  navigateTo(evento) {
    console.log(evento.acaoRealizada);
    let id: number  = evento.matriculaSelecionadoId;
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
   * Método para listar os matriculas
   */
  listar(){
    this.matriculaService.listar().subscribe(dados => {
      this.matriculas = dados;
    },
    (error: any) => {
      console.log(error);
      console.log(error.error.message);
      this.messageService.toastError(error.error.message);
    });
  }
    
  /**
   * Método para remover um matricula
   */
  remover(id: number){
    this.openRemoverConfirm(id);
  }

  openRemoverConfirm(id: number): void {
    this._dialogService.openConfirm({
      message: 'Tem certeza que deseja excluir esta matricula?',
      disableClose: true, // defaults to false
      viewContainerRef: this._viewContainerRef, //OPTIONAL
      title: 'Excluir matricula', //OPTIONAL, hides if not provided
      cancelButton: 'Não', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Sim', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.matriculaService.remover(id).subscribe(dados => {
          this.messageService.toastSuccess('Matricula excluída com sucesso.');
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
