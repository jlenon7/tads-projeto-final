import { Component, OnInit } from '@angular/core';
import { Matricula } from 'src/app/model/matricula';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatriculaService } from 'src/app/service/matricula.service';
import { MessagesService } from 'src/app/service/messages.service';
<<<<<<< HEAD
=======
import { ParserToDateService } from 'src/app/service/parser-to-date.service';
>>>>>>> 93f4b2d4998741a5c56b47336865ea644251ba48
import { DateAdapter } from '@angular/material/core';
import { Aluno } from 'src/app/model/aluno';
import { Curso } from 'src/app/model/curso';
import { AlunoService } from 'src/app/service/aluno.service';
import { CursoService } from 'src/app/service/curso.service';

@Component({
  selector: 'app-matricula-form',
  templateUrl: './matricula-form.component.html',
  styleUrls: ['./matricula-form.component.css']
})
export class MatriculaFormComponent implements OnInit {
  /**
     * Matricula Form
     */
  public matriculaForm: FormGroup;

  /**
   * Objeto matricula
   */
  public matricula: Matricula;

  /**
  * Controla se é atualização
  */
  private isOnUpdate: boolean = false;

  /**
   * List de alunos
   */
  public alunosList: Array<Aluno> = [];

  /**
   * List de cursos
   */
  public cursosList: Array<Curso> = [];

  /**
<<<<<<< HEAD
  * Controla o Spinner
  */
 
  private showSpinner: boolean = false;

  /**
=======
>>>>>>> 93f4b2d4998741a5c56b47336865ea644251ba48
   * Construtor da classe
   * @param fb 
   * @param activatedRoute 
   * @param router 
   * @param professorService 
   */
  constructor(private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private matriculaService: MatriculaService,
    private messageService: MessagesService,
    private alunoService: AlunoService,
    private cursoService: CursoService,
    private _adapter: DateAdapter<any>) { }

  /**
   * Método chamado ao iniciar a classe
   */
  ngOnInit() {


    this.matricula = new Matricula(null, null, null, null, null, null);
    this.createForm();
    this.listarCursos("");
    this.listarAlunos("");
    this.matricula.id = this.activatedRoute.snapshot.params['id'];
    if (this.matricula.id) {
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
    this.matriculaForm = this.fb.group(
      {
        aluno: [null, { validators: [Validators.required], updateOn: 'select' }],
        curso: [null, { validators: [Validators.required], updateOn: 'select' }],
        
      }
      
      
    );

    /**
     * Seta o locale da data para usar padrão brasileiro
     */
    this._adapter.setLocale('pt');

  }

<<<<<<< HEAD
  /**
   * Método para chamar o loader
   */

  loadData() {
    this.showSpinner = true;
    setTimeout(() => {
      this.showSpinner = false;
    }, 30000);
  }
=======
>>>>>>> 93f4b2d4998741a5c56b47336865ea644251ba48

  /**
   * Método para salvar a matricula
   */
  onSave() {
    if (this.matriculaForm.valid) {

      //this.matricula.disponivel = true;
      var alun : Aluno = this.matriculaForm.get("aluno").value;
      this.matricula.aluno = alun;
      var curs : Curso = this.matriculaForm.get("curso").value;
      this.matricula.curso = curs;
      console.log(this.matricula);
      /**
       * Verifica se é cadastro ou edição
       */
      if (this.matricula.id == null) {
        this.matriculaService.cadastrar(this.matricula).subscribe(res => {
          this.matricula = res;
          this.messageService.toastSuccess('Matricula cadastrada com sucesso.');
          this.onBack();
        },
          (error: any) => {
            this.messageService.toastError(error.error.message);
          });
      }
      else {
        this.matriculaService.editar(this.matricula).subscribe(res => {
          this.matricula = res;
          this.isOnUpdate = true;
          this.messageService.toastSuccess('Matricula atualizada com sucesso.');
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
   * Método para popular o formulário com os dados do reforço em edição
   */
  loadToEdit() {
    this.matriculaService.detalhar(this.matricula.id).subscribe(res => {
      this.matriculaForm.get("curso").setValue(res.curso);
      this.matriculaForm.get("aluno").setValue(res.aluno);
      this.isOnUpdate = true;
    },
      (error: any) => {
        this.messageService.toastError(error.error.message);
      });

  }

  /**
   * Método para voltar a pagina de list de matriculas
   */
  onBack() {
    if (!this.isOnUpdate) {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    } else {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
    }

  }


  /**
   * Display de curso
   */
  displayCurso(curso?: Curso): string | undefined {
    return curso ? curso.area : undefined;
  }

  listarCursos(filter: string) {
    this.cursoService.listar().subscribe(dados => {
      this.cursosList = dados;
    },
      (error: any) => {
        this.messageService.toastError(error.error.message);
      });
  }


  selectCurso(event: any) {
    this.matriculaForm.get("curso").setValue(event.option.value);
  }

  /**
   * Display de aluno
   */
  displayAluno(aluno?: Aluno): string | undefined {
    return aluno ? aluno.nome : undefined;
  }

  listarAlunos(filter: string) {
    this.alunoService.listar().subscribe(dados => {
      this.alunosList = dados;
    },
      (error: any) => {
        this.messageService.toastError(error.error.message);
      });
  }


  selectAluno(event: any) {
    this.matriculaForm.get("aluno").setValue(event.option.value);
  }

}