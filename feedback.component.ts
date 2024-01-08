import { Component, OnInit } from '@angular/core';
import { Feedbacklist } from '../feedbacklist';
import { FeedbackService } from '../feedback.service';
import { Router } from '@angular/router';
import { BusService } from '../bus-service.service';
import { UserService } from '../user.service';
import { Bus } from '../bus';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit{
  feedbacklist:Feedbacklist = new Feedbacklist();
  feedbacklists:Feedbacklist[]=[]
  id:number;
  bus : Bus=new Bus();
  // user:User=new User(userName,userPassword,email,dob,about,role);
  selectedBusIds:number[]=[];
  selectedUserIds:number[]=[];

  i:number;
  j:number;
 
  constructor(private feedbackService:FeedbackService,private router:Router,private busService:BusService,private userService:UserService){}

  ngOnInit(): void {
    this.getAllFeedbacks();
    // this.busService.getBusList().subscribe(data=>{
    //   this.bus[this.i]=data;
    //   console.log(data);

    // })
    // this.userService.viewAllUsers().subscribe(data=>{
    //   this.user[this.j]=data;
    //   console.log(this.user);
    // })
  }
   getAllFeedbacks(){
    this.feedbackService.getAllFeedBackList().subscribe((data: Feedbacklist[]) => {
       this.feedbacklists = data;
      console.log(data);
    });
  }
//   getAllFeedbacks(){
//     this.feedbackService.getAllFeedBackList().subscribe(data=>{
//       this.feedbacklist=data;
//       console.log(this.feedbacklist);
//     })
//  }
 viewFeedback(feedBackId:number){
  this.router.navigate([`/view-feedback`,feedBackId])
 }
 deleteFeedback(feedBackId:number){
  var status=confirm("Are you sure you want to delete this record");
  if(status==true){
    this.feedbackService.deleteFeedBackById(feedBackId).subscribe(details=>{
      this.getAllFeedbacks();
    })
  }
  else{
    this.getAllFeedbacks();
  }
 }
}
