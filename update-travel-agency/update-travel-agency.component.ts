import { Component, OnInit } from '@angular/core';
import { TravelAgency } from '../travel-agency';
import { TravelAgencyService } from '../travel-agency.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-travel-agency',
  templateUrl: './update-travel-agency.component.html',
  styleUrls: ['./update-travel-agency.component.css']
})
export class UpdateTravelAgencyComponent implements OnInit{

  agencyId: number ;
  travelAgency: TravelAgency = new TravelAgency();
  constructor(private travelAgencyService: TravelAgencyService,
    private route: ActivatedRoute,
    private router: Router) { }

  // ngOnInit(): void {
  //   this.agencyId = this.route.snapshot.params['agencyId'];  

  //   this.travelAgencyService.getTravelAgencyById(this.agencyId).subscribe(data => {
  //     this.travelAgency = data;
  //   }, error => console.log(error));
  // }
  // onSubmit(){
  //   this.travelAgencyService.updateAgencyDetailsById(this.agencyId, this.travelAgency).subscribe( data =>{
  //     this.getAlltravelAgencyDetails();
  //   }
  //   , error => console.log(error));
  // }

  // getAlltravelAgencyDetails(){
  //   this.router.navigate(['/see-all-travelAgency']);
  // }
  ngOnInit(): void {
    this.agencyId = this.route.snapshot.params['agencyId'];  //1

    this.travelAgencyService.getTravelAgencyById(this.agencyId).subscribe(data => {
      this.travelAgency= data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.travelAgencyService.updateAgencyDetailsById(this.agencyId, this.travelAgency).subscribe( data =>{
      this.getAllAgencyDetails();
    }
    ,error => console.log(error));
  }

  getAllAgencyDetails(){
    this.router.navigate(['/see-all-agencyDetails']);

  }
  goToTravelAgency():any{
    this.router.navigate(['see-all-agencyDetails'])
  }

}

