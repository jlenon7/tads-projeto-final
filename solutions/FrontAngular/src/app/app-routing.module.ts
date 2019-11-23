import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlunoFormComponent } from './view/admin/aluno/aluno-form/aluno-form.component';
import { AlunoSearchComponent } from './view/admin/aluno/aluno-search/aluno-search.component';
import { AlunoDetailComponent } from './view/admin/aluno/aluno-detail/aluno-detail.component';
import { ProfessorSearchComponent } from './view/admin/professor/professor-search/professor-search.component';
import { ProfessorFormComponent } from './view/admin/professor/professor-form/professor-form.component';
import { ProfessorDetailComponent } from './view/admin/professor/professor-detail/professor-detail.component';


const routes: Routes = [
  {
    component: AlunoSearchComponent,
    path: 'admin/aluno'
  },
  {
    component: AlunoFormComponent,
    path: 'admin/aluno/cadastrar'
  },
  {
    component: AlunoFormComponent,
    path: 'admin/aluno/alterar/:id'
  },
  {
    component: AlunoDetailComponent,
    path: 'admin/aluno/detalhes/:id'
  },
  {
    component: ProfessorSearchComponent,
    path: 'admin/professor'
  },
  {
    component: ProfessorFormComponent,
    path: 'admin/professor/cadastrar'
  },
  {
    component: ProfessorFormComponent,
    path: 'admin/professor/alterar/:id'
  },
  {
    component: ProfessorDetailComponent,
    path: 'admin/professor/detalhes/:id'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
