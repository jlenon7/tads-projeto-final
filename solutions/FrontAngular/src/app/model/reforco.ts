// import { Curso } from './curso'
import { Disciplina } from './disciplina';

export class Reforco {
    constructor(
       public id: number,
       public area: string,
       public ministrante: string,
       public vagas: number,
       public horaInicio: Date,
       public disponivel: Boolean,
       public disiciplinaEnum: Disciplina
       // public funcionarios: Curso[]
      ) {  }

}
