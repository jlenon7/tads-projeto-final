import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Matricula } from 'src/app/model/matricula';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';


@Component({
  selector: 'app-matricula-list',
  templateUrl: './matricula-list.component.html',
  styleUrls: ['./matricula-list.component.css']
})
export class MatriculaListComponent implements OnInit {

  @Input() matriculas : Matricula[];

  @Output() selecionarMatricula = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  selecionar(id: any, acao: number) {
    this.selecionarMatricula.emit({matriculaSelecionadoId : id, acaoRealizada : TipoAcaoValues[acao]});
  }
}