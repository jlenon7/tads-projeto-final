import { Component, OnInit } from '@angular/core';
import { Curso } from 'src/app/model/curso';
import { Professor } from 'src/app/model/professor';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DificuldadeValues } from 'src/app/model/dificuldade';
import { CursoService } from 'src/app/service/curso.service';
import { ProfessorService } from 'src/app/service/professor.service';
import { MessagesService } from 'src/app/service/messages.service';
import { ParserToDateService } from 'src/app/service/parser-to-date.service';
import { DateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.css']
})
export class CursoFormComponent implements OnInit {
/**
   * Curso Form
   */
  public cursoForm: FormGroup;

  /**
   * Objeto curso
   */
  public curso: Curso;

  /**
  * Controla se é atualização
  */
  private isOnUpdate: boolean = false;

   /**
   * Lista de disciplinas
   */
  private dificuldadeValues: string[] = DificuldadeValues;


  /**
   * List de Ministrantes
  */
  public professoresList: Array<Professor> = [];

  /**
   * Construtor da classe
   * @param fb
   * @param activatedRoute
   * @param router
   * @param cursoService
   */
  constructor(private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private cursoService: CursoService,
    private messageService: MessagesService,
    private professorService: ProfessorService,
    private parserToDate: ParserToDateService,
    private _adapter: DateAdapter<any>) { }

  /**
   * Método chamado ao iniciar a classe
   */
  ngOnInit() {

    this.curso = new Curso(null, null, null, null, null, null);
    this.createForm();
    this.listarProfessores("");
    this.curso.id = this.activatedRoute.snapshot.params['id'];
    if (this.curso.id) {
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
    this.cursoForm = this.fb.group(
      {
        area: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        cargaHoraria: [null, {validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur'}],
        dificuldade: [null, { validators: [Validators.required] }],
        ministrante: [null, { validators: [Validators.required], updateOn: 'select' }],
      }
    );

    /**
     * Seta o locale da data para usar padrão brasileiro
     */
    this._adapter.setLocale('pt');

  }
  

  /**
   * Método para salvar o curso
   */
  onSave() {
    if (this.cursoForm.valid) {

      this.curso.disponivel = true;
      this.curso.area = this.cursoForm.get("area").value;
      this.curso.cargaHoraria = this.cursoForm.get("cargaHoraria").value;
      this.curso.dificuldade = this.cursoForm.get("dificuldade").value;
      var prof : Professor = this.cursoForm.get("ministrante").value;
      this.curso.ministrante = prof;

      console.log(this.curso);

      /**
       * Verifica se é cadastro ou edição
       */
      if(this.curso.id == null){
        this.cursoService.cadastrar(this.curso).subscribe(res => {
          this.curso = res;
          this.messageService.toastSuccess('Curso cadastrado com sucesso.');
          this.onBack();
        },
          (error: any) => {
            this.messageService.toastError(error.error.message);
          });
      }
      else{
        this.cursoService.editar(this.curso).subscribe(res => {
          this.curso = res;
          this.isOnUpdate = true;
          this.messageService.toastSuccess('Curso atualizado com sucesso.');
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
    this.cursoService.detalhar(this.curso.id).subscribe(res => {
      this.cursoForm.get("area").setValue(res.area);
      this.cursoForm.get("cargaHoraria").setValue(res.cargaHoraria);
      this.cursoForm.get("dificuldade").setValue(res.dificuldade);
      this.cursoForm.get("ministrante").setValue(res.ministrante);
      this.isOnUpdate = true;
    },
    (error: any) => {
      this.messageService.toastError(error.error.message);
    });

  }

  /**
   * Método para voltar a pagina de list de cursos
   */
  onBack() {
    if (!this.isOnUpdate) {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    }else{
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
    }
  }

  /**
   * Display de professor
   */

  displayProfessor(ministrante?: Professor): string | undefined {
    return ministrante ? ministrante.nome : undefined;
  }

  listarProfessores(filter: string) {
    this.professorService.listar().subscribe(dados => {
      this.professoresList = dados;
    },
      (error: any) => {
        this.messageService.toastError(error.error.message);
      });
  }

  selectProfessor(event: any) {
    this.cursoForm.get("ministrante").setValue(event.option.value);
  }

}



