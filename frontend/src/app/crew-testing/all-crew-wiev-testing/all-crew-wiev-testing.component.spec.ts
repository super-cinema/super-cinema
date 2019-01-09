import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCrewWievTestingComponent } from './all-crew-wiev-testing.component';

describe('AllCrewWievTestingComponent', () => {
  let component: AllCrewWievTestingComponent;
  let fixture: ComponentFixture<AllCrewWievTestingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllCrewWievTestingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCrewWievTestingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
