import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorDetailComponent } from './professor-detail.component';

describe('ProfessorDetailComponent', () => {
  let component: ProfessorDetailComponent;
  let fixture: ComponentFixture<ProfessorDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessorDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
