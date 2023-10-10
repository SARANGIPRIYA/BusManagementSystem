import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTravelAgencyComponent } from './view-travel-agency.component';

describe('ViewTravelAgencyComponent', () => {
  let component: ViewTravelAgencyComponent;
  let fixture: ComponentFixture<ViewTravelAgencyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewTravelAgencyComponent]
    });
    fixture = TestBed.createComponent(ViewTravelAgencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
