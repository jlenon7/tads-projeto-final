import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AlunoFormComponent } from './view/admin/aluno/aluno-form/aluno-form.component';
import { AlunoSearchComponent } from './view/admin/aluno/aluno-search/aluno-search.component';
import { AlunoDetailComponent } from './view/admin/aluno/aluno-detail/aluno-detail.component';

import { ProfessorSearchComponent } from './view/admin/professor/professor-search/professor-search.component';
import { ProfessorFormComponent } from './view/admin/professor/professor-form/professor-form.component';
import { ProfessorDetailComponent } from './view/admin/professor/professor-detail/professor-detail.component';

import { CursoSearchComponent } from './view/admin/curso/curso-search/curso-search.component';
import { CursoFormComponent } from './view/admin/curso/curso-form/curso-form.component';
import { CursoDetailComponent } from './view/admin/curso/curso-detail/curso-detail.component';

import { ReforcoSearchComponent } from './view/admin/reforco/reforco-search/reforco-search.component';
import { ReforcoFormComponent } from './view/admin/reforco/reforco-form/reforco-form.component';
import { ReforcoDetailComponent } from './view/admin/reforco/reforco-detail/reforco-detail.component';

import { MatriculaSearchComponent } from './view/admin/matricula/matricula-search/matricula-search.component';
import { MatriculaFormComponent } from './view/admin/matricula/matricula-form/matricula-form.component';
import { MatriculaDetailComponent } from './view/admin/matricula/matricula-detail/matricula-detail.component';


const routes: Routes = [
  {
    component: MatriculaSearchComponent,
    path: 'admin/matricula'
  },
  {
    component: MatriculaFormComponent,
    path: 'admin/matricula/cadastrar'
  },
  {
    component: MatriculaFormComponent,
    path: 'admin/matricula/alterar/:id'
  },
  {
    component: MatriculaDetailComponent,
    path: 'admin/matricula/detalhes/:id'
  },
  
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
  },


  {
    component: CursoSearchComponent,
    path: 'admin/curso'
  },
  {
    component: CursoFormComponent,
    path: 'admin/curso/cadastrar'
  },
  {
    component: CursoFormComponent,
    path: 'admin/curso/alterar/:id'
  },
  {
    component: CursoDetailComponent,
    path: 'admin/curso/detalhes/:id'
  },

  
  {
    component: ReforcoSearchComponent,
    path: 'admin/reforco'
  },
  {
    component: ReforcoFormComponent,
    path: 'admin/reforco/cadastrar'
  },
  {
    component: ReforcoFormComponent,
    path: 'admin/reforco/alterar/:id'
  },
  {
    component: ReforcoDetailComponent,
    path: 'admin/reforco/detalhes/:id'
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
