import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlunoComponent } from './view/aluno/aluno.component';
import { AlunoFormComponent } from './view/administrador/aluno/aluno-form/aluno-form.component';
import { AlunoListComponent } from './view/administrador/aluno/aluno-list/aluno-list.component';
import { AlunoDetailComponent } from './view/administrador/aluno/aluno-detail/aluno-detail.component';
import { AlunoSearchComponent } from './view/administrador/aluno/aluno-search/aluno-search.component';
import { CursoDetailComponent } from './view/administrador/curso/curso-detail/curso-detail.component';
import { CursoFormComponent } from './view/administrador/curso/curso-form/curso-form.component';
import { ProfessorFormComponent } from './view/professor/professor-form/professor-form.component';
import { ProfessorListComponent } from './view/professor/professor-list/professor-list.component';
import { ProfessorDetailComponent } from './view/professor/professor-detail/professor-detail.component';
import { ProfessorSearchComponent } from './view/professor/professor-search/professor-search.component';
import { CursoListComponent } from './view/curso-list/curso-list.component';
import { CursoSearchComponent } from './view/curso-search/curso-search.component';
import { ReforcoFormComponent } from './view/reforco/reforco-form/reforco-form.component';
import { ReforcoListComponent } from './view/reforco/reforco-list/reforco-list.component';
import { ReforcoDetailComponent } from './view/reforco/reforco-detail/reforco-detail.component';
import { ReforcoSearchComponent } from './view/reforco/reforco-search/reforco-search.component';

@NgModule({
  declarations: [
    AppComponent,
    AlunoComponent,
    AlunoFormComponent,
    AlunoListComponent,
    AlunoDetailComponent,
    AlunoSearchComponent,
    CursoDetailComponent,
    CursoFormComponent,
    ProfessorFormComponent,
    ProfessorListComponent,
    ProfessorDetailComponent,
    ProfessorSearchComponent,
    CursoListComponent,
    CursoSearchComponent,
    ReforcoFormComponent,
    ReforcoListComponent,
    ReforcoDetailComponent,
    ReforcoSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
