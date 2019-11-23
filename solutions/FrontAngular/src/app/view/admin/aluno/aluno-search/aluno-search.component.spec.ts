import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunoSearchComponent } from './aluno-search.component';

describe('AlunoSearchComponent', () => {
  let component: AlunoSearchComponent;
  let fixture: ComponentFixture<AlunoSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlunoSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlunoSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
