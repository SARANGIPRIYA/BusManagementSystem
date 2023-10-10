import { Component, OnInit } from '@angular/core';
import { Bus } from '../bus';

import { Router } from '@angular/router';
import { BusService } from '../bus-service.service';

@Component({
  selector: 'app-bus-data',
  templateUrl: './bus-data.component.html',
  styleUrls: ['./bus-data.component.css']
})
export class BusDataComponent implements OnInit {

  buses: Bus[] = [];
  agencyName : string = "";
  constructor(private busService: BusService,
    private router: Router) { }

viewBus(busId:number){
  this.router.navigate(['view-bus-details',busId]);
  
}

  ngOnInit(): void {
    this.getbusDetails();
  }
  searchByagencyName() : any{
    this.busService.searchByagencyName(this.agencyName).subscribe(details => {
      this.buses= details;
      console.log(details);
    },
      error => {
        console.log(error);
      });
 }
 deleteBusById(busId:number){
  var status =confirm("Are you sure? Do you want to delete?")
  if (status == true){
    this.busService.deleteBus(busId).subscribe(data =>{
      this.getbusDetails();
    })
  }
 }
  deleteAllBusDetails() : void{
    var status = confirm("Are you sure to delete all the records?");
    if (status == true) {
      this.busService.deleteAll().subscribe(details => {
        console.log(details);
        this.getbusDetails();
      },
        error => {
          console.log(error);
        })
    }
    else{
    this.getbusDetails();
  }
  }
  getisAcBus() {
    this.busService.findByisAcBus().subscribe({
      next: (res) => {
      console.log(res);
      this.buses = res;
    },
    error: (e) => console.error(e)
  });
  }

  getnonAcBus(){
    this.busService.findByNonAcBus().subscribe({
      next: (res) => {
      console.log(res);
      this.buses = res;
    },
    error: (e) => console.error(e)
  });
  }
  private getbusDetails(): any{
    this.busService.getBusList().subscribe(data => {
      this.buses = data;
      console.log(data);
    });
  }

  busDetails(busId: number){
    this.router.navigate(['bus-details', busId]);
  }

  updateBus(busId: number){
    this.router.navigate(['update-bus', busId]);
  }

  deleteBus(busId: number){
    var status = confirm("Are you sure to delete this record?");
    if (status == true) {
    this.busService.deleteBus(busId).subscribe( data => {
      console.log(data);
      this.getbusDetails();
    })
  }
    else{
      this.getbusDetails();
    }
  }
    getBusBySorting(option:string){
  
      switch(option){
        case "costlow":
          this.busService.SortingCost("asc","ticketCost").subscribe(data=>{
            this.buses=data;
            console.log(data);
          },error=>{console.log(error)}
          )
          break;
          case "costHigh":
          this.busService.SortingCost("desc","ticketCost").subscribe(data=>{
            this.buses=data;
            console.log(data)
          },error=>{console.log(error)}
          )
          break;
          case "ratinglow":
            this.busService.SortRating("asc","busRating").subscribe(data=>{
              this.buses=data;
              console.log(data)
            },error=>{console.log(error)}
            )
            break;
            case "ratingHigh":
              this.busService.SortRating("desc","busRating").subscribe(data=>{
                this.buses=data;
                console.log(data)
              },error=>{console.log(error)}
              )
    
      }
    }
    
  }
