import { Component, OnInit } from '@angular/core';
import { BusService } from '../bus-service.service';
import { Bus } from '../bus';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-bus',
  templateUrl: './update-bus.component.html',
  styleUrls: ['./update-bus.component.css']
})
export class UpdateBusComponent implements OnInit{
  busId: number = 0;
  bus: Bus = new Bus();
  constructor(private busService: BusService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.busId = this.route.snapshot.params['busId'];  

    this.busService.getBusById(this.busId).subscribe(data => {
      this.bus = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.busService.updateBus(this.busId, this.bus).subscribe( data =>{
      this.getAllBusDetails();
    }
    , error => console.log(error));
  }

  getAllBusDetails(){
    this.router.navigate(['/see-all-bus']);
  }
}
