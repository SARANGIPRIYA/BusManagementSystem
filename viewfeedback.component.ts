import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FeedbackService } from '../feedback.service';
import { Feedbacklist } from '../feedbacklist';

@Component({
  selector: 'app-viewfeedback',
  templateUrl: './viewfeedback.component.html',
  styleUrls: ['./viewfeedback.component.css']
})
export class ViewfeedbackComponent {
  feedbackId: number = 0;
  particularFeedbacklist: any;
  
  constructor(private route: ActivatedRoute, private feedbacklist:FeedbackService ) { }

  ngOnInit(): void {
  
    this.feedbackId = this.route.snapshot.params['feedbackId'];  

    this.feedbacklist.getFeedBackById(this.feedbackId).subscribe( (data: any) => {
      this.particularFeedbacklist= data;
      console.log( this.particularFeedbacklist);
    });
}
}
