import { Time } from "@angular/common";
import { TravelAgency } from "./travel-agency";
import { Route } from "@angular/router";
import { Booking } from "./booking";

    
export class Bus {
    busId:number=0;
    busNumber:String="";
    agencyName:string="";
    destination:string="";
    source:string="";
    ticketCost:number=0;
    driverName:string="";
    totalSeats:number=0;
    noOfSeatsBooked:number=0;
    noOfSeatsAvailable:number=0;
    isAcBus:boolean=false;
    busRating:number=0;
    bookings:Booking[];
    travelAgency():TravelAgency{throw new Error('Method not implemented.');};
    route():Route{throw new Error('Method not implemented.');}

}



