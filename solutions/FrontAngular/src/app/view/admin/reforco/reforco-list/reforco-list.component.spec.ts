import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReforcoListComponent } from './reforco-list.component';

describe('ReforcoListComponent', () => {
  let component: ReforcoListComponent;
  let fixture: ComponentFixture<ReforcoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReforcoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReforcoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
