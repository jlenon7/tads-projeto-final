import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Reforco } from './../model/reforco'

@Injectable({
  providedIn: 'root'
})
export class ReforcoService {

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Reforco[]>('http://localhost:4200/api/api/admin/reforco/list/');
  }

  detalhar(reforcoId: number){
    return this.http.get<Reforco>('http://localhost:4200/api/api/admin/reforco/find?id='+reforcoId);
  }

  cadastrar(reforco: Reforco){
    return this.http.post<Reforco>('http://localhost:4200/api/api/admin/reforco/insert/', reforco);
  }

  editar(reforco: Reforco){
    return this.http.post<Reforco>('http://localhost:4200/api/api/admin/reforco/update/', reforco);
  }

  remover(reforcoId: number){
    return this.http.get('http://localhost:4200/api/api/reforco/admin/remove?id='+reforcoId);

  }
}
