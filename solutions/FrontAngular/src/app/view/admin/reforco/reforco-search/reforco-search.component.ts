import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Reforco } from 'src/app/model/reforco';
import { Router, ActivatedRoute } from '@angular/router';
import { ReforcoService } from 'src/app/service/reforco.service';
import { MessagesService } from 'src/app/service/messages.service';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';
import { TdDialogService } from '@covalent/core/dialogs';

@Component({
  selector: 'app-reforco-search',
  templateUrl: './reforco-search.component.html',
  styleUrls: ['./reforco-search.component.css']
})
export class ReforcoSearchComponent implements OnInit {

   /**
   * Lista de reforcos a ser exibida
   */
  reforcos : Array<Reforco>;

  /**
   * Construtor da classe
   * @param router 
   * @param activatedRoute 
   */
  constructor(private router: Router,
            private activatedRoute: ActivatedRoute,
            private reforcoService: ReforcoService,
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
   * Método que redireciona para cadastrar reforco
   */
  navigateToNovo() {
    this.router.navigate(['cadastrar'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar, excluir ou visualizar reforco
   * @param evento 
   */
  navigateTo(evento) {
    console.log(evento.acaoRealizada);
    let id: number  = evento.reforcoSelecionadoId;
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
   * Método para listar os reforcos
   */
  listar(){
    this.reforcoService.listar().subscribe(dados => {
      this.reforcos = dados;
    },
    (error: any) => {
      console.log(error);
      console.log(error.error.message);
      this.messageService.toastError(error.error.message);
    });
  }
    
  /**
   * Método para remover um funcionario
   */
  remover(id: number){
    this.openRemoverConfirm(id);
  }

  openRemoverConfirm(id: number): void {
    this._dialogService.openConfirm({
      message: 'Tem certeza que deseja excluir esse reforço?',
      disableClose: true, // defaults to false
      viewContainerRef: this._viewContainerRef, //OPTIONAL
      title: 'Excluir reforço', //OPTIONAL, hides if not provided
      cancelButton: 'Não', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Sim', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.reforcoService.remover(id).subscribe(dados => {
          this.messageService.toastSuccess('Reforço excluído com sucesso.');
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
