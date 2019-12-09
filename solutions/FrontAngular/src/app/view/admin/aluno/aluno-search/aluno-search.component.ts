import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Aluno } from 'src/app/model/aluno';
import { Router, ActivatedRoute } from '@angular/router';
import { AlunoService } from 'src/app/service/aluno.service';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';
import { MessagesService } from 'src/app/service/messages.service';
import { TdDialogService } from '@covalent/core/dialogs';

@Component({
  selector: 'app-aluno-search',
  templateUrl: './aluno-search.component.html',
  styleUrls: ['./aluno-search.component.css']
})
export class AlunoSearchComponent implements OnInit {

   /**
   * Lista de alunos a ser exibida
   */
  alunos : Array<Aluno>;

  /**
   * Construtor da classe
   * @param router 
   * @param activatedRoute 
   */
  constructor(private router: Router,
            private activatedRoute: ActivatedRoute,
            private alunoService: AlunoService,
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
   * Método que redireciona para cadastrar aluno
   */
  navigateToNovo() {
    this.router.navigate(['cadastrar'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar, excluir ou visualizar aluno
   * @param evento 
   */
  navigateTo(evento) {
    console.log(evento.acaoRealizada);
    let id: number  = evento.alunoSelecionadoId;
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
   * Método para listar os alunos
   */
  listar(){
    this.alunoService.listar().subscribe(dados => {
      this.alunos = dados;
    },
    (error: any) => {
      console.log(error);
      console.log(error.error.message);
      this.messageService.toastError(error.error.message);
    });
  }
    
  /**
   * Método para remover um reforço
   */
  remover(id: number){
    this.openRemoverConfirm(id);
  }

  openRemoverConfirm(id: number): void {
    this._dialogService.openConfirm({
      message: 'Tem certeza que deseja excluir esse aluno?',
      disableClose: true, // defaults to false
      viewContainerRef: this._viewContainerRef, //OPTIONAL
      title: 'Excluir aluno', //OPTIONAL, hides if not provided
      cancelButton: 'Não', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Sim', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.alunoService.remover(id).subscribe(dados => {
          this.messageService.toastSuccess('Aluno excluído com sucesso.');
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
