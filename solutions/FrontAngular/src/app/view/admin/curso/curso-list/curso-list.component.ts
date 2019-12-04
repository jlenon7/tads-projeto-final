import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Curso } from 'src/app/model/curso';


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

  selecionar(id: any, toEdit: boolean) {
    this.selecionarCurso.emit({cursoSelecionadoId : id, toEdit : toEdit})

  }
}
