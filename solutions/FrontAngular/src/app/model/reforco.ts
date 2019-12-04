import { Professor } from './professor'
import { Disciplina } from './disciplina';
import { Time } from '@angular/common';
import { Moment } from 'moment';

export class Reforco {
    constructor(
       public id: number,
       public area: string,
       public ministrante: Professor,
       public vagas: number,
       public horaInicio: Time,
       public disponivel: Boolean,
       public disciplina: Disciplina
      ) {  }

}
