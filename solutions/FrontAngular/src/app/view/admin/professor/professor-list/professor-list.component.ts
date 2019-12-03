import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Professor } from 'src/app/model/professor';


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

  selecionar(id: any, toEdit: boolean) {
    this.selecionarProfessor.emit({professorSelecionadoId : id, toEdit : toEdit})

  }
}