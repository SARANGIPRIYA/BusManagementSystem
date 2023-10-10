import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  logout() {
    throw new Error('Method not implemented.');
  }
  private baseURL = "http://localhost:8080/BusBooking.com";
  

  constructor(private httpClient: HttpClient,private router:Router) {}

  signup(user:User):Observable<any>
  {
     const headers={'content-type':'application/json'};
     const body=JSON.stringify(user);
     return this.httpClient.post(`${this.baseURL}/signup`,body,{'headers':headers});
  }
  isUserLoggedIn() {
    let status = sessionStorage.getItem('loginStatus')
    // console.log(status === null)
    return !(status === null)
  }
  // logOut() {
  //   sessionStorage.removeItem('loginStatus')
  //   sessionStorage.removeItem('email')
  //   sessionStorage.removeItem('userId')
  //   sessionStorage.removeItem('userRole')
  //   sessionStorage.removeItem('userName')
    
  // }
  signin(email:any, password:any):Observable<any>
  {
    
    const params=new HttpParams().set('email',email).set('password',password);
    console.log(params);
    return this.httpClient.get(`${this.baseURL}/login`,{params});
  }
  getUserList(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}/getalluserdetails`);
  }
  getUserById(userId : number) : Observable<User> {
    return this.httpClient.get<User>(`${this.baseURL}/getUserdetailsById/${userId}`);
  }
  
}
  

