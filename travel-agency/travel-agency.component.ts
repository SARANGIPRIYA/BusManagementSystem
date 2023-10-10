import { Component, OnInit } from '@angular/core';
import { TravelAgency } from '../travel-agency';
import { TravelAgencyService } from '../travel-agency.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-travel-agency',
  templateUrl: './travel-agency.component.html',
  styleUrls: ['./travel-agency.component.css']
})
export class TravelAgencyComponent implements OnInit{
  travelAgency: TravelAgency[] = [];
  travelAgencyName : string = "";
  constructor(private travelAgencyService: TravelAgencyService,
    private router: Router,private route:ActivatedRoute) { }
  
    viewTravelAgency(agencyId:number){
      this.router.navigate(['view-travelAgency-details',agencyId]);
      
    }
    
      ngOnInit(): void {
        this.gettravelAgencyDetails();
      }
      private gettravelAgencyDetails(): any{
        this.travelAgencyService.getTravelAgencyList().subscribe(data => {
          this.travelAgency = data;
          console.log(data);
        });
      }
      deleteAgency(agencyId:number){
        var status =confirm("Are you sure? Do you want to delete?")
        if (status == true){
          this.travelAgencyService.deleteAgencyDetailsById(agencyId).subscribe(data =>{
            this.gettravelAgencyDetails();
          })
          
        }
        else{
          this.gettravelAgencyDetails();
        }
  }
  deleteAllTravelAgencyDetails() : void{
    var status = confirm("Are you sure to delete all the records?");
    if (status == true) {
      this.travelAgencyService.deleteAllAgencyDetails().subscribe(details => {
        console.log(details);
        this.gettravelAgencyDetails();
      },
        error => {
          console.log(error);
        })
    }
    else{
    this.gettravelAgencyDetails();
  }
  }
  private gettravelAgency(): any{
    this.travelAgencyService.getTravelAgencyList().subscribe(data => {
      this.travelAgency = data;
      console.log(data);
    });
  }
  travelAgencyDetails(agencyId: number){
    this.router.navigate(['travelAgency-details', agencyId]);
  }

  updateTravelAgency(agencyId: number){
    this.router.navigate(['update-travel-agency', agencyId]);
  }

  deleteTravelAgency(agencyId: number){
    var status = confirm("Are you sure to delete this record?");
    if (status == true) {
    this.travelAgencyService.deleteAgencyDetailsById(agencyId).subscribe( data => {
      console.log(data);
      this.gettravelAgencyDetails();
    })
  }
    else{
      this.gettravelAgencyDetails();
    }
}
}
