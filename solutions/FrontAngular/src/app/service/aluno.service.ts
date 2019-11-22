import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Aluno} from './../model/aluno'

@Injectable({
  providedIn: 'root'
})
export class AlunoService {

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Aluno[]>('http://localhost:4200/api/api/aluno/list/');
  }

  detalhar(alunoId: number){
    return this.http.get<Aluno>('http://localhost:4200/api/api/aluno/find?id='+alunoId);
  }

  cadastrar(aluno: Aluno){
    return this.http.post<Aluno>('http://localhost:4200/api/api/aluno/insert/', aluno);
  }

  editar(aluno: Aluno){
    return this.http.post<Aluno>('http://localhost:4200/api/api/aluno/update/', aluno);
  }

  remover(alunoId: number){
    return this.http.get('http://localhost:4200/api/api/aluno/remove?id='+alunoId);

  }
}
