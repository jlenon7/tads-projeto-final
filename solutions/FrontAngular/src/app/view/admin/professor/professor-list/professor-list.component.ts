import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';


@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})
export class ProfessorListComponent implements OnInit {

  @Input() professores : Professor[];

  @Output() selecionarProfessor = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  selecionar(id: any, acao: number) {
    this.selecionarProfessor.emit({professorSelecionadoId : id, acaoRealizada : TipoAcaoValues[acao]});
  }
}