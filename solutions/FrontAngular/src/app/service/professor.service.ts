import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Professor } from './../model/professor';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Professor[]>('http://localhost:4200/api/api/admin/professor/list/');
  }

  detalhar(professorId: number){
    return this.http.get<Professor>('http://localhost:4200/api/api/admin/professor/find?id='+professorId);
  }

  cadastrar(professor: Professor){
    return this.http.post<Professor>('http://localhost:4200/api/api/admin/professor/insert/', professor);
  }

  editar(professor: Professor){
    return this.http.post<Professor>('http://localhost:4200/api/api/admin/professor/update/', professor);
  }

  remover(professorId: number){
    return this.http.get('http://localhost:4200/api/api/professor/admin/remove?id='+professorId);
    
  }
}
