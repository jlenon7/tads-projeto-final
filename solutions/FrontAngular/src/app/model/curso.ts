import { Professor } from './professor'
import { Dificuldade } from './dificuldade';

export class Curso {
    constructor(
       public id: number,
       public area: string,
       public cargaHoraria: number,
       public disponivel: Boolean,
       public dificuldadeEnum: Dificuldade,
       public ministrante: Professor[]
      ) {  }

}
