import { Component, OnInit } from '@angular/core';
import { CarService } from '../car.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  car: any;
  wikiUrl: any;

  constructor(private cs: CarService, private route: ActivatedRoute){}

  ngOnInit(){
    this.cs.findCar(this.route.params["_value"]["id"]).subscribe(data =>{
      console.log(data);
      this.car = data;
      this.cs.wikiCar(this.car.make, this.car.model).subscribe(data => {
        this.wikiUrl = data;
      })
    })
  }
}
