// import { Curso } from './curso'
import { TipoUsuario } from './tipoUsuario';
import { AreaConhecimento } from './areaConhecimento';

export class Professor {
    constructor(
       public id: number,
       public nome: string,
       public cpf: string,
       public nascimento: Date,
       public email: string,
       public senha: string,
       public celular: string,
       public disponivel: Boolean,
       public tipoUsuario: TipoUsuario,
       public areaConhecimento: AreaConhecimento,
      ) {  }
}