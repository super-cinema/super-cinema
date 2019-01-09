import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrewDetailTestComponent } from './crew-detail-test.component';

describe('CrewDetailTestComponent', () => {
  let component: CrewDetailTestComponent;
  let fixture: ComponentFixture<CrewDetailTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrewDetailTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrewDetailTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
