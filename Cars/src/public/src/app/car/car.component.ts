import { Component, OnInit } from '@angular/core';
import { CarService } from '../car.service';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  cars:Array<any>;

  constructor(private cs: CarService){}

  ngOnInit(){
    this.cs.getAll().subscribe(data => {
      console.log(data)
      this.cars = data;
    })
  }

}
