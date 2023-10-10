import { Component, OnInit } from '@angular/core';
import { BusService } from '../bus-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus } from '../bus';

@Component({
  selector: 'app-view-bus',
  templateUrl: './view-bus.component.html',
  styleUrls: ['./view-bus.component.css']
})
export class ViewBusComponent implements OnInit{
  busId: number = 0;
  particularBus: Bus = new Bus();
  particularTravelagency:any;
  particularRoute:any;
  
  constructor(private route: ActivatedRoute, private busService: BusService,private router:Router) { }

  ngOnInit(): void {
    
    this.busId = this.route.snapshot.params['busId'];  //1


    this.busService.getBusById(this.busId).subscribe( data => {
      this.particularBus = data;
      this.particularTravelagency=data.travelAgency;
      this.particularRoute=data.route;
      console.log( this.particularBus);
    });
  }
  // goToBooking():any{
  //   this.router.navigate(['create-booking',this.particularBus.busId])
  // }
  // goToRoute():any{
  //   this.router.navigate(['view-route-details',this.particularRoute.routeId])
  // }
  goToBusData():any{
    this.router.navigate(['see-all-bus'])
  }
  goToTravelAgency():any{
    this.router.navigate(['view-travelAgency-details',this.particularTravelagency.agencyId])
  }
}
