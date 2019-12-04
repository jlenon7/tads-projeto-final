import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Reforco } from 'src/app/model/reforco';
import { TipoAcaoValues } from 'src/app/model/tipo-acao';


@Component({
  selector: 'app-reforco-list',
  templateUrl: './reforco-list.component.html',
  styleUrls: ['./reforco-list.component.css']
})
export class ReforcoListComponent implements OnInit {

  @Input() reforcos : Reforco[];

  @Output() selecionarReforco = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  selecionar(id: any, acao: number) {
    this.selecionarReforco.emit({reforcoSelecionadoId : id, acaoRealizada : TipoAcaoValues[acao]});
  }
}