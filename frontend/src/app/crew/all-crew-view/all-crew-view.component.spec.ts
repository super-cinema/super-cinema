import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCrewViewComponent } from './all-crew-view.component';

describe('AllCrewViewComponent', () => {
  let component: AllCrewViewComponent;
  let fixture: ComponentFixture<AllCrewViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllCrewViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCrewViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
