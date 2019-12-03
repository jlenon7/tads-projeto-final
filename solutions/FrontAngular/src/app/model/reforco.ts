// import { Curso } from './curso'
import { DisciplinaEnum } from './disciplinaEnum';

export class Reforco {
    constructor(
       public id: number,
       public area: string,
       public ministrante: string,
       public vagas: number,
       public horaInicio: Date,
       public disponivel: Boolean,
       public disiciplinaEnum: DisciplinaEnum,
       // public funcionarios: Curso[]
      ) {  }

}
