import { Component, OnInit } from '@angular/core';
import { Aluno } from 'src/app/model/aluno';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlunoService } from 'src/app/service/aluno.service';
import { MessagesService } from 'src/app/service/messages.service';
import { DateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-aluno-form',
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.css']
})
export class AlunoFormComponent implements OnInit {
/**
   * Aluno Form
   */
  public alunoForm: FormGroup;

  /**
   * Objeto aluno
   */
  public aluno: Aluno;

  /**
  * Controla se é atualização
  */
  private isOnUpdate: boolean = false;

  /**
  * Controla o Spinner
  */

  private showSpinner: boolean = false;

  /**
   * Construtor da classe
   * @param fb
   * @param activatedRoute
   * @param router
   */
  constructor(private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private alunoService: AlunoService,
    private messageService: MessagesService,
    private _adapter: DateAdapter<any>) { }

  /**
   * Método chamado ao iniciar a classe
   */
  ngOnInit() {
    this.aluno = new Aluno(null, null, null, null, null, null, null, null, null);
    this.aluno.id = this.activatedRoute.snapshot.params['id'];
    if(this.aluno.id){
      this.isOnUpdate = true;
    }
    this.createForm();
    if (this.aluno.id) {
      this.loadToEdit();
    }

  }

  /**
   * Método para criar o formulário
   */
  public createForm() {
    /**
     * Instancia uma classe FormGroup
     */
    this.alunoForm = this.fb.group(
      {
        nome: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        nascimento: [null, { validators: [Validators.required], updateOn: 'blur' }],
        cpf: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        email: [null, {validators: [Validators.required, Validators.email] }],
        senha: [null, {validators: (this.isOnUpdate ? null : [Validators.required, Validators.maxLength(144)]), updateOn: 'blur'}],
        celular: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        tipousuario: [null],
      }
    );

    /**
     * Seta o locale da data para usar padrão brasileiro
     */
    this._adapter.setLocale('pt');

  }

  /**
   * Método para chamar o loader
   */

  loadData() {
    this.showSpinner = true;
    setTimeout(() => {
      this.showSpinner = false;
    }, 30000);
  }

  /**
   * Método para salvar o aluno
   */
  onSave() {
    if (this.alunoForm.valid) {

      this.aluno.nome = this.alunoForm.get("nome").value;
      this.aluno.nascimento = this.alunoForm.get("nascimento").value;
      this.aluno.cpf = this.alunoForm.get("cpf").value;
      this.aluno.email = this.alunoForm.get("email").value;
      this.aluno.celular = this.alunoForm.get("celular").value;
      this.aluno.tipoUsuario = this.alunoForm.get("tipousuario").value;
      console.log(this.aluno);

      /**
       * Verifica se é cadastro ou edição
       */
      if(this.aluno.id == null){
        this.aluno.senha = this.alunoForm.get("senha").value;
        this.alunoService.cadastrar(this.aluno).subscribe(res => {
          this.aluno = res;
          this.messageService.toastSuccess('Aluno cadastrado com sucesso.');
          this.onBack();
        },
          (error: any) => {
            this.messageService.toastError(error.error.message);
          });
      }
      else{
        this.alunoService.editar(this.aluno).subscribe(res => {
          this.aluno = res;
          this.isOnUpdate = true;
          this.messageService.toastSuccess('Aluno atualizado com sucesso.');
          this.onBack();
        },
        (error: any) => {
          this.messageService.toastError(error.error.message);
        });
      }

    } else {
      this.messageService.toastWarnning('Preencha todos os campos obrigatórios antes de salvar.');

    }
  }



  /**
   * Método para popular o formulário com os dados do aluno em edição
   */
  loadToEdit(){
    this.alunoService.detalhar(this.aluno.id).subscribe(res => {
      this.alunoForm.get("nome").setValue(res.nome);
      this.alunoForm.get("nascimento").setValue(res.nascimento);
      this.alunoForm.get("cpf").setValue(res.cpf);
      this.alunoForm.get("email").setValue(res.email);
      this.alunoForm.get("celular").setValue(res.celular);
      this.alunoForm.get("tipousuario").setValue(res.tipoUsuario);
    },
    (error: any) => {
      this.messageService.toastError(error.error.message);
    });

  }

  /**
   * Método para voltar a pagina de list de alunos
   */
  onBack() {
    if(!this.isOnUpdate){
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    }else{
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
    }

  }

}

