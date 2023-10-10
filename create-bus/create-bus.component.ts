import { Component, OnInit } from '@angular/core';
import { Bus } from '../bus';
import { BusService} from '../bus-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TravelAgency } from '../travel-agency';
import { TravelAgencyService } from '../travel-agency.service';
@Component({
  selector: 'app-create-bus',
  templateUrl: './create-bus.component.html',
  styleUrls: ['./create-bus.component.css']
})
export class CreateBusComponent {  
     // agencyService: TravelagencyService;
  bus : Bus = new Bus();
  selectedagencyIds:number[]=[];
    j:number ;
  travelAgency:TravelAgency[]=[];
  

  constructor(private busService: BusService,private route: ActivatedRoute,
    private router: Router,private agencyService: TravelAgencyService) { }

    
ngOnInit():void{
  this.agencyService.getTravelAgencyList().subscribe((data: any) => {
    this.travelAgency = data;
    console.log(data);
  })
}

    saveBusDetails(indexId:number,agencyId:number){
      this.busService.createBus(this.bus,agencyId).subscribe( data =>{
        console.log(data);
        this.goToBusData();
      },
      error => console.log(error));
    }

    goToBusData(){
      this.router.navigate(['/see-all-bus']);
    }
    
  }
