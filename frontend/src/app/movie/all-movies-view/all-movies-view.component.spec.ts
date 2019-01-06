import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMoviesViewComponent } from './all-movies-view.component';

describe('AllMoviesViewComponent', () => {
  let component: AllMoviesViewComponent;
  let fixture: ComponentFixture<AllMoviesViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllMoviesViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllMoviesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
