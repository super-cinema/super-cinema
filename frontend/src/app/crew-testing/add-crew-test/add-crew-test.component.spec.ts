import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCrewTestComponent } from './add-crew-test.component';

describe('AddCrewTestComponent', () => {
  let component: AddCrewTestComponent;
  let fixture: ComponentFixture<AddCrewTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCrewTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCrewTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
