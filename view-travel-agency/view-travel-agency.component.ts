import { Component, OnInit } from '@angular/core';
import { TravelAgency } from '../travel-agency';
import { TravelAgencyService } from '../travel-agency.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-travel-agency',
  templateUrl: './view-travel-agency.component.html',
  styleUrls: ['./view-travel-agency.component.css']
})
export class ViewTravelAgencyComponent implements OnInit {
  agencyId: number = 0;
  particularTravelAgency: TravelAgency = new TravelAgency();
  
 
  constructor(private route: ActivatedRoute, private travelAgencyService: TravelAgencyService,private router:Router) { }

  ngOnInit(): void {
  
    this.agencyId = this.route.snapshot.params['agencyId'];  

    this.travelAgencyService.getTravelAgencyById(this.agencyId).subscribe( data => {
      this.particularTravelAgency = data;
      console.log( this.particularTravelAgency);
    });
  }
  goBack():any{
    this.router.navigate(['see-all-agencyDetails'])
  }
}

