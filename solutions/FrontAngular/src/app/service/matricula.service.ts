import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Matricula } from './../model/matricula';

@Injectable({
  providedIn: 'root'
})
export class MatriculaService {

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Matricula[]>('http://localhost:4200/api/api/admin/matricula/list/');
  }

  detalhar(matriculaId: number){
    return this.http.get<Matricula>('http://localhost:4200/api/api/admin/matricula/find?id='+matriculaId);
  }

  cadastrar(matricula: Matricula){
    return this.http.post<Matricula>('http://localhost:4200/api/api/admin/matricula/insert/', matricula);
  }

  editar(matricula: Matricula){
    return this.http.post<Matricula>('http://localhost:4200/api/api/admin/matricula/update/', matricula);
  }

  remover(matriculaId: number){
    return this.http.get('http://localhost:4200/api/api/admin/matricula/remove?id='+matriculaId);

  }
}
