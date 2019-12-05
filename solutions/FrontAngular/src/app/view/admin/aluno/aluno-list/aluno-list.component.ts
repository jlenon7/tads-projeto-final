import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Aluno } from 'src/app/model/aluno';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';


@Component({
  selector: 'app-aluno-list',
  templateUrl: './aluno-list.component.html',
  styleUrls: ['./aluno-list.component.css']
})
export class AlunoListComponent implements OnInit {

  @Input() alunos : Aluno[];

  @Output() selecionarAluno = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  selecionar(id: any, acao: number) {
    this.selecionarAluno.emit({alunoSelecionadoId : id, acaoRealizada : TipoAcaoValues[acao]});
  }
}