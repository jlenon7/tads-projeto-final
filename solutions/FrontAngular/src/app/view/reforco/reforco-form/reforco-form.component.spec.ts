import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReforcoFormComponent } from './reforco-form.component';

describe('ReforcoFormComponent', () => {
  let component: ReforcoFormComponent;
  let fixture: ComponentFixture<ReforcoFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReforcoFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReforcoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
