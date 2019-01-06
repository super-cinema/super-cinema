import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleScreeningComponent } from './schedule-screening.component';

describe('ScheduleScreeningComponent', () => {
  let component: ScheduleScreeningComponent;
  let fixture: ComponentFixture<ScheduleScreeningComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScheduleScreeningComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScheduleScreeningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
