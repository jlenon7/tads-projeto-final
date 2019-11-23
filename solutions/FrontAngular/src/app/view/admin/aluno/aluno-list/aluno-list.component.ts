import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Aluno } from 'src/app/model/aluno';


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

  selecionar(id: any, toEdit: boolean) {
    this.selecionarAluno.emit({alunoSelecionadoId : id, toEdit : toEdit})

  }
}
