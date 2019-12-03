import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { ProfessorService } from 'src/app/service/professor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagesService } from 'src/app/service/messages.service';

@Component({
  selector: 'app-professor-detail',
  templateUrl: './professor-detail.component.html',
  styleUrls: ['./professor-detail.component.css']
})
export class ProfessorDetailComponent implements OnInit {

  /**
   * Objeto funcionário
   */
  public professor: Professor;

  constructor(private professorService: ProfessorService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private messageService: MessagesService) { }

  ngOnInit() {
    this.professor = new Professor(null, null, null, null, null, null, null, null, null, null);
    this.professor.id = this.activatedRoute.snapshot.params['id'];
    if (this.professor.id) {
      this.loadDados();
    }

  }

  /**
   * Método para popular os campos com os dados do funcionário em visualização
   */
  loadDados(){
    this.professorService.detalhar(this.professor.id).subscribe(res => {
      this.professor = new Professor(res.id, res.nome, res.cpf, res.nascimento, res.email, res.senha, res.celular, res.disponivel, res.tipoUsuario, res.areaConhecimento);
    },
    (error: any) => {
      this.messageService.toastError(error.error.message);
    }
    );

  }

  /**
   * Método para voltar a pagina de list de professors
   */
  onBack() {

    this.router.navigate(['../../'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar professor
   */
  navigateToEdit() {
    this.router.navigate(['../../alterar/'+this.professor.id], { relativeTo: this.activatedRoute });
  }

}
