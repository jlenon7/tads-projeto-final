import { Component, OnInit } from '@angular/core';
import { Reforco } from 'src/app/model/reforco';
import { DisciplinaValues } from 'src/app/model/disciplina';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReforcoService } from 'src/app/service/reforco.service';
import { MessagesService } from 'src/app/service/messages.service';
import { Professor } from 'src/app/model/professor';
import { ProfessorService } from 'src/app/service/professor.service';
import { ParserToDateService } from 'src/app/service/parser-to-date.service';
import { DateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-reforco-form',
  templateUrl: './reforco-form.component.html',
  styleUrls: ['./reforco-form.component.css']
})
export class ReforcoFormComponent implements OnInit {
  /**
     * Reforco Form
     */
  public reforcoForm: FormGroup;

  /**
   * Objeto reforco
   */
  public reforco: Reforco;

  /**
  * Controla se é atualização
  */
  private isOnUpdate: boolean = false;

  /**
   * Lista de cargos
   */
  private disciplinaValues: string[] = DisciplinaValues;


  /**
   * List de professores
   */
  public professoresList: Array<Professor> = [];

  /**
   * Construtor da classe
   * @param fb 
   * @param activatedRoute 
   * @param router 
   * @param professorService 
   */
  constructor(private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private reforcoService: ReforcoService,
    private messageService: MessagesService,
    private professorService: ProfessorService,
    private parserToDate: ParserToDateService,
    private _adapter: DateAdapter<any>) { }

  /**
   * Método chamado ao iniciar a classe
   */
  ngOnInit() {


    this.reforco = new Reforco(null, null, null, null, null, null, null);
    this.createForm();
    this.listarProfessores("");
    this.reforco.id = this.activatedRoute.snapshot.params['id'];
    if (this.reforco.id) {
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
    this.reforcoForm = this.fb.group(
      {
        area: [null, { validators: [Validators.required, Validators.maxLength(144)], updateOn: 'blur' }],
        vagas: [null, { validators: [Validators.required, Validators.maxLength(11)], updateOn: 'blur' }],
        horainicio: [null, { validators: [Validators.required, Validators.maxLength(11)], updateOn: 'blur' }],
        disciplina: [null, { validators: [Validators.required] }],
        ministrante: [null, { validators: [Validators.required], updateOn: 'select' }],
      }
      
      
    );

    /**
     * Seta o locale da data para usar padrão brasileiro
     */
    this._adapter.setLocale('pt');

  }


  /**
   * Método para salvar o reforco
   */
  onSave() {
    if (this.reforcoForm.valid) {

      this.reforco.area = this.reforcoForm.get("area").value;
      this.reforco.vagas = this.reforcoForm.get("vagas").value;
      this.reforco.horaInicio = this.reforcoForm.get("horainicio").value;
      var prof : Professor = this.reforcoForm.get("ministrante").value;
      this.reforco.ministrante = prof;
      this.reforco.disciplina = this.reforcoForm.get("disciplina").value;
      console.log(this.reforco);
      /**
       * Verifica se é cadastro ou edição
       */
      if (this.reforco.id == null) {
        this.reforcoService.cadastrar(this.reforco).subscribe(res => {
          this.reforco = res;
          this.messageService.toastSuccess('Reforço cadastrado com sucesso.');
          this.onBack();
        },
          (error: any) => {
            this.messageService.toastError(error.error.message);
          });
      }
      else {
        this.reforcoService.editar(this.reforco).subscribe(res => {
          this.reforco = res;
          this.isOnUpdate = true;
          this.messageService.toastSuccess('Reforço atualizado com sucesso.');
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
    this.reforcoService.detalhar(this.reforco.id).subscribe(res => {
      this.reforcoForm.get("area").setValue(res.area);
      this.reforcoForm.get("disciplina").setValue(res.disciplina);
      this.reforcoForm.get("vagas").setValue(res.vagas);
      this.reforcoForm.get("ministrante").setValue(res.ministrante);
      this.reforcoForm.get("horainicio").setValue(res.horaInicio);
      this.isOnUpdate = true;
    },
      (error: any) => {
        this.messageService.toastError(error.error.message);
      });

  }

  /**
   * Método para voltar a pagina de list de reforcos
   */
  onBack() {
    console.log(this.reforcoForm.get("professor").value);
    if (!this.isOnUpdate) {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    } else {
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
    this.reforcoForm.get("ministrante").setValue(event.option.value);
  }

}