import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Bus} from './bus';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  private baseURL = "http://localhost:8080/BusBooking.com";

  constructor(private httpClient: HttpClient) { }
  
 
  getBusById(busId : number) : Observable<Bus> {
    return this.httpClient.get<Bus>(`${this.baseURL}/getBusDetailsBybusId/${busId}`);
  }

  deleteAll(): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/deleteAllBusDetails`);
  }

  getBusList(): Observable<Bus[]>{
    return this.httpClient.get<Bus[]>(`${this.baseURL}/getAllBusDetails`);
  }

  deleteBus(busId : number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteBusBybusId/${busId}`);

  }

  findByisAcBus(): Observable<Bus[]> {
    return this.httpClient.get<Bus[]>(`${this.baseURL}/BusManagement/isAcBus`);
  }

  findByNonAcBus(): Observable<Bus[]> {
    return this.httpClient.get<Bus[]>(`${this.baseURL}/BusManagement/nonAcBus`);
}
searchByagencyName(agencyName:string):Observable<Bus[]>{
  return this.httpClient.get<Bus[]>(`${this.baseURL}/getAllBusDetails?agencyName=${agencyName}`);
}

  createBus(bus : Bus,agencyId:number): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/saveBusDetails/${agencyId}`, bus);
  }
  updateBus(busId : number, bus : Bus) : Observable<Bus> {
    return this.httpClient.put<Bus>(`${this.baseURL}/updateBusDetailsbyId/${busId}`, bus);
}
SortingCost(direction:string,field:string):Observable<Bus[]>{
  console.log(direction)
  return this.httpClient.get<Bus[]>(`${this.baseURL}/sortByCost/${direction}?fieldName=${field}`);

}
SortRating(direction:string,field:string):Observable<Bus[]>{
  console.log(direction)
  return this.httpClient.get<Bus[]>(`${this.baseURL}/sortByRating/${direction}?fieldName=${field}`);

}
}