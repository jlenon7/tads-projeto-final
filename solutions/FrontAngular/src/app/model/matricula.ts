import { Curso } from './curso'
import { Aluno } from './aluno'
import { Time } from '@angular/common';
import { Moment } from 'moment';

export class Matricula {
    constructor(
       public id: number,
       public dataMatricula: Time,
       public dataVencimentoMatricula: Time,
       public curso: Curso,
       public aluno: Aluno,
       public disponivel: Boolean
      ) {  }

}
