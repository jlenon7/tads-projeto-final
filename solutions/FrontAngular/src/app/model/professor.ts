// import { Curso } from './curso'
import { TipoUsuarioEnum } from './tipoUsuario';
import { AreaConhecimentoEnum } from './areaConhecimento';

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
       public tipoUsuario: TipoUsuarioEnum,
       public areaConhecimento: AreaConhecimentoEnum
       // public funcionarios: Curso[]
      ) {  }
    
}