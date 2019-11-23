import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReforcoSearchComponent } from './reforco-search.component';

describe('ReforcoSearchComponent', () => {
  let component: ReforcoSearchComponent;
  let fixture: ComponentFixture<ReforcoSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReforcoSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReforcoSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
