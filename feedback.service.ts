import { Injectable } from '@angular/core';
import { Feedbacklist } from './feedbacklist';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseURL = "http://localhost:8080/feedback.com";
   snapshot: any;

  constructor(private httpClient: HttpClient) { }

  
getFeedBackById(feedBackId : number) : Observable<Feedbacklist> {
  return this.httpClient.get<Feedbacklist>(`${this.baseURL}/feedback/${feedBackId}`);
}
getAllFeedBackList(): Observable<Feedbacklist[]>{
return this.httpClient.get<Feedbacklist[]>(`${this.baseURL}/feedback/all`);
}

deleteFeedBackById(feedBackId : number): Observable<Object>{
return this.httpClient.delete(`${this.baseURL}/feedback/delete/${feedBackId}`);

}

  createFeedBackDetails(obj:Feedbacklist,busId:number,userId:number):any{
  return this.httpClient.post(`${this.baseURL}/add/${busId}/${userId}`,obj);
}
}

