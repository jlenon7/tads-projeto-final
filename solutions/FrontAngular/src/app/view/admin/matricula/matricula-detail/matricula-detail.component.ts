import { Component, OnInit } from '@angular/core';
import { Matricula } from 'src/app/model/matricula';
import { Value} from 'src/app/model/value';
import { MatriculaService } from 'src/app/service/matricula.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagesService } from 'src/app/service/messages.service';
import * as moment from 'moment';

@Component({
  selector: 'app-matricula-detail',
  templateUrl: './matricula-detail.component.html',
  styleUrls: ['./matricula-detail.component.css']
})
export class MatriculaDetailComponent implements OnInit {

  /**
   * Objeto matricula
   */
  public matricula: Matricula;

  /**
   * Objeto value
   */
  public value: Value;

  constructor(private matriculaService: MatriculaService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private messageService: MessagesService) { }

  ngOnInit() {
    this.matricula = new Matricula(null, null, null, null, null, null);
    this.matricula.id = this.activatedRoute.snapshot.params['id'];
    if (this.matricula.id) {
      this.loadDados();
    }

  }

  /**
   * Método para popular os campos com os dados do matricula em visualização
   */
  loadDados(){
    this.matriculaService.detalhar(this.matricula.id).subscribe(res => {
      this.matricula = new Matricula(res.id, res.dataMatricula, res.dataVencimentoMatricula, res.curso, res.aluno, res.disponivel);
      if (this.matricula.disponivel === true){
        var myBool: Boolean = this.matricula.disponivel;
        var myString: string = String(myBool);
        this.value = new Value(myString = "Ativo");
        console.log(myString);

      } else {
        var myBool: Boolean = this.matricula.disponivel;
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
   * Método para voltar a pagina de list de matriculas
   */
  onBack() {

    this.router.navigate(['../../'], { relativeTo: this.activatedRoute });

  }

  /**
   * Método que redireciona para alterar matricula
   */
  navigateToEdit() {
    this.router.navigate(['../../alterar/'+this.matricula.id], { relativeTo: this.activatedRoute });
  }

}
