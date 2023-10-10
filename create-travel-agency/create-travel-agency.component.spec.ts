import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTravelAgencyComponent } from './create-travel-agency.component';

describe('CreateTravelAgencyComponent', () => {
  let component: CreateTravelAgencyComponent;
  let fixture: ComponentFixture<CreateTravelAgencyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateTravelAgencyComponent]
    });
    fixture = TestBed.createComponent(CreateTravelAgencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
