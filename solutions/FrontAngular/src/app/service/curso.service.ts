import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Curso } from './../model/curso'

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Curso[]>('http://localhost:4200/api/api/admin/curso/list/');
  }

  detalhar(cursoId: number){
    return this.http.get<Curso>('http://localhost:4200/api/api/admin/curso/find?id='+cursoId);
  }

  cadastrar(curso: Curso){
    return this.http.post<Curso>('http://localhost:4200/api/api/admin/curso/insert/', curso);
  }

  editar(curso: Curso){
    return this.http.post<Curso>('http://localhost:4200/api/api/admin/curso/update/', curso);
  }

  remover(cursoId: number){
    return this.http.get('http://localhost:4200/api/api/curso/admin/remove?id='+cursoId);

  }
}
