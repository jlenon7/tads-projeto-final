import { BrowserModule} from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatListModule, MatCardModule, MatMenuModule, MatInputModule, MatButtonToggleModule, MatIconModule,
  MatProgressSpinnerModule, MatSelectModule, MatSlideToggleModule, MatSnackBarModule, MatToolbarModule,
  MatTabsModule, MatSidenavModule, MatTooltipModule, MatRippleModule, MatRadioModule, MatGridListModule,
  MatDatepickerModule, MatNativeDateModule, MatSliderModule, MatAutocompleteModule } from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';
import { CovalentCommonModule, CovalentLayoutModule, CovalentMediaModule, CovalentExpansionPanelModule,
  CovalentStepsModule, CovalentLoadingModule, CovalentSearchModule, CovalentPagingModule,
  CovalentNotificationsModule, CovalentMenuModule, CovalentDataTableModule, CovalentMessageModule } from '@covalent/core';
import { CovalentDialogsModule } from '@covalent/core/dialogs';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule} from "@angular/flex-layout";
import { AppRoutingModule } from './app-routing.module';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './view/admin/menu/menu.component';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';

import { AppComponent } from './app.component';
import { AlunoFormComponent } from './view/admin/aluno/aluno-form/aluno-form.component';
import { AlunoListComponent } from './view/admin/aluno/aluno-list/aluno-list.component';
import { AlunoDetailComponent } from './view/admin/aluno/aluno-detail/aluno-detail.component';
import { AlunoSearchComponent } from './view/admin/aluno/aluno-search/aluno-search.component';

import { ProfessorFormComponent } from './view/admin/professor/professor-form/professor-form.component';
import { ProfessorListComponent } from './view/admin/professor/professor-list/professor-list.component';
import { ProfessorDetailComponent } from './view/admin/professor/professor-detail/professor-detail.component';
import { ProfessorSearchComponent } from './view/admin/professor/professor-search/professor-search.component';

import { CursoListComponent } from './view/admin/curso/curso-list/curso-list.component';
import { CursoSearchComponent } from './view/admin/curso/curso-search/curso-search.component';
import { CursoDetailComponent } from './view/admin/curso/curso-detail/curso-detail.component';
import { CursoFormComponent } from './view/admin/curso/curso-form/curso-form.component';

import { ReforcoFormComponent } from './view/admin/reforco/reforco-form/reforco-form.component';
import { ReforcoListComponent } from './view/admin/reforco/reforco-list/reforco-list.component';
import { ReforcoDetailComponent } from './view/admin/reforco/reforco-detail/reforco-detail.component';
import { ReforcoSearchComponent } from './view/admin/reforco/reforco-search/reforco-search.component';

import { AlunoService } from './service/aluno.service';
import { CursoService } from './service/curso.service';
import { ReforcoService } from './service/reforco.service';
import { ProfessorService } from './service/professor.service';
import { MessagesService } from './service/messages.service';


@NgModule({
  declarations: [
    AppComponent,
    AlunoFormComponent,
    AlunoListComponent,
    AlunoDetailComponent,
    AlunoSearchComponent,

    MenuComponent,

    ProfessorFormComponent,
    ProfessorListComponent,
    ProfessorDetailComponent,
    ProfessorSearchComponent,

    CursoListComponent,
    CursoSearchComponent,
    CursoDetailComponent,
    CursoFormComponent,
    
    ReforcoFormComponent,
    ReforcoListComponent,
    ReforcoDetailComponent,
    ReforcoSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    /** Material Modules */

    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatInputModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatSlideToggleModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatTabsModule,
    MatSidenavModule,
    MatTooltipModule,
    MatRippleModule,
    MatRadioModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSliderModule,
    MatAutocompleteModule,
    /** Covalent Modules */
    CovalentCommonModule,
    CovalentLayoutModule,
    CovalentMediaModule,
    CovalentExpansionPanelModule,
    CovalentStepsModule,
    CovalentDialogsModule,
    CovalentLoadingModule,
    CovalentSearchModule,
    CovalentPagingModule,
    CovalentNotificationsModule,
    CovalentMenuModule,
    CovalentDataTableModule,
    CovalentMessageModule,
    NgxMaterialTimepickerModule,

  ],
  providers: [
    AlunoService,
    CursoService,
    ReforcoService,
    ProfessorService,
    MessagesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
