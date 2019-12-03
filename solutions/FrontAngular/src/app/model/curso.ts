// import { Curso } from './curso'
import { Dificuldade } from './dificuldade';

export class Curso {
    constructor(
       public id: number,
       public area: string,
       public ministrante: string,
       public cargaHoraria: number,
       public disponivel: Boolean,
       public dificuldadeEnum: Dificuldade,
       // public funcionarios: Curso[]
      ) {  }

}
