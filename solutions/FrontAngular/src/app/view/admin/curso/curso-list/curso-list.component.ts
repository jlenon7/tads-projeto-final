import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Curso } from 'src/app/model/curso';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';


@Component({
  selector: 'app-curso-list',
  templateUrl: './curso-list.component.html',
  styleUrls: ['./curso-list.component.css']
})
export class CursoListComponent implements OnInit {

  @Input() cursos : Curso[];

  @Output() selecionarCurso = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  selecionar(id: any, acao: number) {
    this.selecionarCurso.emit({cursoSelecionadoId : id, acaoRealizada : TipoAcaoValues[acao]});
  }
}