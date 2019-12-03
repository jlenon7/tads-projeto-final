import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { Router, ActivatedRoute } from '@angular/router';
import { ProfessorService } from 'src/app/service/professor.service';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';
import { MessagesService } from 'src/app/service/messages.service';

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
   * @param professorService 
   */
  constructor(private router: Router,
            private activatedRoute: ActivatedRoute,
            private professorService: ProfessorService,
            private messageService: MessagesService) { 
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
    this.professorService.remover(id).subscribe(dados => {
      this.messageService.toastSuccess('Professor excluído com sucesso.');
      this.listar();
    },
    (error: any) => {
      console.log(error.error.message);
      this.messageService.toastError(error.error.message);
      
    });
  }
}
