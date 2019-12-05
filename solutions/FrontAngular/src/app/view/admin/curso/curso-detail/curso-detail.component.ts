import { Component, OnInit } from '@angular/core';
import { Curso } from 'src/app/model/curso';
import { CursoService } from 'src/app/service/curso.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagesService } from 'src/app/service/messages.service';
import * as moment from 'moment';
import { ParserToTimeService } from 'src/app/service/parser-to-time.service';

@Component({
  selector: 'app-curso-detail',
  templateUrl: './curso-detail.component.html',
  styleUrls: ['./curso-detail.component.css']
})
export class CursoDetailComponent implements OnInit {

  /**
   * Objeto curso
   */
  public curso: Curso;

  constructor(private cursoService: CursoService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private messageService: MessagesService,
    /*private parserToTime: ParserToTimeService*/) { }

  ngOnInit() {
    this.curso = new Curso(null, null, null, null, null, null);
    this.curso.id = this.activatedRoute.snapshot.params['id'];
    if (this.curso.id) {
      this.loadDados();
    }

  }

  /**
   * Método para popular os campos com os dados do curso em visualização
   */
  loadDados(){
    this.cursoService.detalhar(this.curso.id).subscribe(res => {
      this.curso = new Curso(res.id, res.area, res.cargaHoraria, res.disponivel, res.dificuldade, res.ministrante);
      //this.curso.horaInicio = this.parserToTime.parser(this.curso.horaInicio);
    },
    (error: any) => {
      this.messageService.toastError(error.error.message);
    }
    );

  }

  /**
   * Método para voltar a pagina de list de cursos
   */
  onBack() {

    this.router.navigate(['../../'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar curso
   */
  navigateToEdit() {
    this.router.navigate(['../../alterar/'+this.curso.id], { relativeTo: this.activatedRoute });
  }

}
