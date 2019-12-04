import { Component, OnInit } from '@angular/core';
import { Reforco } from 'src/app/model/reforco';
import { ReforcoService } from 'src/app/service/reforco.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagesService } from 'src/app/service/messages.service';
import * as moment from 'moment';
import { ParserToTimeService } from 'src/app/service/parser-to-time.service';

@Component({
  selector: 'app-reforco-detail',
  templateUrl: './reforco-detail.component.html',
  styleUrls: ['./reforco-detail.component.css']
})
export class ReforcoDetailComponent implements OnInit {

  /**
   * Objeto funcionário
   */
  public reforco: Reforco;

  constructor(private reforcoService: ReforcoService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private messageService: MessagesService,
    /*private parserToTime: ParserToTimeService*/) { }

  ngOnInit() {
    this.reforco = new Reforco(null, null, null, null, null, null, null);
    this.reforco.id = this.activatedRoute.snapshot.params['id'];
    if (this.reforco.id) {
      this.loadDados();
    }

  }

  /**
   * Método para popular os campos com os dados do funcionário em visualização
   */
  loadDados(){
    this.reforcoService.detalhar(this.reforco.id).subscribe(res => {
      this.reforco = new Reforco(res.id, res.area, res.ministrante, res.vagas, res.horaInicio, res.disponivel, res.disciplina);
      //this.reforco.horaInicio = this.parserToTime.parser(this.reforco.horaInicio);
    },
    (error: any) => {
      this.messageService.toastError(error.error.message);
    }
    );

  }

  /**
   * Método para voltar a pagina de list de reforcos
   */
  onBack() {

    this.router.navigate(['../../'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar reforco
   */
  navigateToEdit() {
    this.router.navigate(['../../alterar/'+this.reforco.id], { relativeTo: this.activatedRoute });
  }

}
