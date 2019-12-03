import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AreaConhecimentoValues } from 'src/app/model/areaConhecimento';
import { ProfessorService } from 'src/app/service/professor.service';
import { MessagesService } from 'src/app/service/messages.service';
import { ParserToDateService } from 'src/app/service/parser-to-date.service';
import { DateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css']
})
export class ProfessorFormComponent implements OnInit {
/**
   * Professor Form
   */
  public professorForm: FormGroup;

  /**
   * Objeto professor
   */
  public professor: Professor;

  /**
  * Controla se é atualização
  */
  private isOnUpdate: boolean = false;

   /**
   * Lista de areas
   */
  private areaConhecimentoValues: string[] = AreaConhecimentoValues;


  /**
   * List de departamentos
   
  public departamentosList: Array<Departamento> = [];*/

  /**
   * Construtor da classe
   * @param fb
   * @param activatedRoute
   * @param router
   * @param departamentoService
   */
  constructor(private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private professorService: ProfessorService,
    private messageService: MessagesService,
    //private parserToDate: ParserToDateService,
    private _adapter: DateAdapter<any>) { }

  /**
   * Método chamado ao iniciar a classe
   */
  ngOnInit() {

    this.professor = new Professor(null, null, null, null, null, null, null, null, null, null);
    this.createForm();
    this.professor.id = this.activatedRoute.snapshot.params['id'];
    if (this.professor.id) {
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
    this.professorForm = this.fb.group(
      {
        nome: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        nascimento: [null, { validators: [Validators.required], updateOn: 'blur' }],
        cpf: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        email: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        senha: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        celular: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        tipousuario: [1],
        areaconhecimento: [null, { validators: [Validators.required] }],
      }
    );

    /**
     * Seta o locale da data para usar padrão brasileiro
     */
    this._adapter.setLocale('pt');

  }
  

  /**
   * Método para salvar o professor
   */
  onSave() {
    if (this.professorForm.valid) {

      this.professor.nome = this.professorForm.get("nome").value;
      this.professor.nascimento = this.professorForm.get("nascimento").value;
      this.professor.cpf = this.professorForm.get("cpf").value;
      this.professor.email = this.professorForm.get("email").value;
      this.professor.senha = this.professorForm.get("senha").value;
      this.professor.celular = this.professorForm.get("celular").value;
      this.professor.tipoUsuario = this.professorForm.value;
      this.professor.areaConhecimento = this.professorForm.get("areaconhecimento").value;

      console.log(this.professor);

      /**
       * Verifica se é cadastro ou edição
       */
      if(this.professor.id == null){
        this.professorService.cadastrar(this.professor).subscribe(res => {
          this.professor = res;
          this.messageService.toastSuccess('Professor cadastrado com sucesso.');
          this.onBack();
        },
          (error: any) => {
            this.messageService.toastError(error.error.message);
          });
      }
      else{
        this.professorService.editar(this.professor).subscribe(res => {
          this.professor = res;
          this.isOnUpdate = true;
          this.messageService.toastSuccess('Professor atualizado com sucesso.');
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
   * Método para popular o formulário com os dados do funcionário em edição
   */
  loadToEdit(){
    this.professorService.detalhar(this.professor.id).subscribe(res => {
      this.professorForm.get("nome").setValue(res.nome);
      this.professorForm.get("nascimento").setValue(res.nascimento);
      this.professorForm.get("cpf").setValue(res.cpf);
      this.professorForm.get("email").setValue(res.email);
      this.professorForm.get("senha").setValue(res.senha);
      this.professorForm.get("celular").setValue(res.celular);
      this.professorForm.get("tipousuario").setValue(res.tipoUsuario);
      this.professorForm.get("areaconhecimento").setValue(res.areaConhecimento);
      this.isOnUpdate = true;
    },
    (error: any) => {
      this.messageService.toastError(error.error.message);
    });

  }

  /**
   * Método para voltar a pagina de list de professors
   */
  onBack() {
    if (!this.isOnUpdate) {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    }else{
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
    }

  /**
   * Display de departamento
   
  displayDepartamento(departamento?: Departamento): string | undefined {
    return departamento ? departamento.descricao : undefined;
  }

  listarDepartamentos(filter: string) {
    this.departamentoService.listar().subscribe(dados => {
      this.departamentosList = dados;
    },
      (error: any) => {
        this.messageService.toastError(error.error.message);
      });
  }


  selectDepartamento(event: any) {
    this.funcionarioForm.get("departamento").setValue(event.option.value);*/

  }

}

