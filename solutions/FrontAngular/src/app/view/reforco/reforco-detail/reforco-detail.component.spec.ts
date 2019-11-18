import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReforcoDetailComponent } from './reforco-detail.component';

describe('ReforcoDetailComponent', () => {
  let component: ReforcoDetailComponent;
  let fixture: ComponentFixture<ReforcoDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReforcoDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReforcoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
