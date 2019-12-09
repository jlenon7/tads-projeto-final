import { Component, OnInit } from '@angular/core';
import { Aluno } from 'src/app/model/aluno';
import { Value } from 'src/app/model/value';
import { AlunoService } from 'src/app/service/aluno.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagesService } from 'src/app/service/messages.service';

@Component({
  selector: 'app-aluno-detail',
  templateUrl: './aluno-detail.component.html',
  styleUrls: ['./aluno-detail.component.css']
})
export class AlunoDetailComponent implements OnInit {

  /**
   * Objeto aluno
   */
  public aluno: Aluno;

  /**
   * Objeto value
   */
  public value: Value;

  constructor(private alunoService: AlunoService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private messageService: MessagesService) { }

  ngOnInit() {
    this.aluno = new Aluno(null, null, null, null, null, null, null, null, null);
    this.aluno.id = this.activatedRoute.snapshot.params['id'];
    if (this.aluno.id) {
      this.loadDados();
    }

  }

  /**
   * Método para popular os campos com os dados do funcionário em visualização
   */
  loadDados(){
    this.alunoService.detalhar(this.aluno.id).subscribe(res => {
      this.aluno = new Aluno(res.id, res.nome, res.cpf, res.nascimento, res.email, res.senha, res.celular, res.disponivel, res.tipoUsuario);
      if (this.aluno.disponivel === true){
        var myBool: Boolean = this.aluno.disponivel;
        var myString: string = String(myBool);
        this.value = new Value(myString = "Ativo");
        console.log(myString);

      } else {
        var myBool: Boolean = this.aluno.disponivel;
        var myString: string = String(myBool);
        this.value = new Value(myString = "Inativo");
        console.log(myString);
      }
    },
    (error: any) => {
      this.messageService.toastError(error.error.message);
    }
    );

  }

  /**
   * Método para voltar a pagina de list de alunos
   */
  onBack() {
    this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
  }

  /**
   * Método que redireciona para alterar aluno
   */
  navigateToEdit() {
    this.router.navigate(['../../alterar/'+this.aluno.id], { relativeTo: this.activatedRoute });
  }

}
