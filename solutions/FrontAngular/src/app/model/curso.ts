// import { Curso } from './curso'
import { DificuldadeEnum } from './dificuldadeEnum';

export class Curso {
    constructor(
       public id: number,
       public area: string,
       public ministrante: string,
       public cargaHoraria: number,
       public disponivel: Boolean,
       public dificuldadeEnum: DificuldadeEnum,
       // public funcionarios: Curso[]
      ) {  }

}
