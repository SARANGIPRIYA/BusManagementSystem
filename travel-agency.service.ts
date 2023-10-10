import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TravelAgency } from './travel-agency';

@Injectable({
  providedIn: 'root'
})
export class TravelAgencyService {
  getAgencyList: any;
  createTravelAgency() {
    throw new Error('Method not implemented.');
  }
  private baseURL = "http://localhost:8080/BusBooking.com";

  constructor(private httpClient: HttpClient) { }

  getTravelAgencyById(agencyId : number) : Observable<TravelAgency> {
    return this.httpClient.get<TravelAgency>(`${this.baseURL}/getAgencyDetailsById/${agencyId}`);
}
deleteAllAgencyDetails(): Observable<any> {
  return this.httpClient.delete(`${this.baseURL}/deleteAllAgencyDetails`);
}

getTravelAgencyList(): Observable<TravelAgency[]>{
  return this.httpClient.get<TravelAgency[]>(`${this.baseURL}/getAllAgencyDetails`);
}

deleteAgencyDetailsById(agencyId : number): Observable<Object>{
  return this.httpClient.delete(`${this.baseURL}/deleteAgencyDetails/${agencyId}`);

}
createAgencyDetails(travelAgency : TravelAgency): Observable<Object>{
  return this.httpClient.post(`${this.baseURL}/saveAgencyDetails`,travelAgency);
}
updateAgencyDetailsById(agencyId : number, travelAgency : TravelAgency) : Observable<TravelAgency> {
  return this.httpClient.put<TravelAgency>(`${this.baseURL}/updateAgencyName/${agencyId}`, travelAgency);
}
}
