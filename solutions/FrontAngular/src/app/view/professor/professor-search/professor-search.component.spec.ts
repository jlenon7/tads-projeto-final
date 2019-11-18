import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorSearchComponent } from './professor-search.component';

describe('ProfessorSearchComponent', () => {
  let component: ProfessorSearchComponent;
  let fixture: ComponentFixture<ProfessorSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessorSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
