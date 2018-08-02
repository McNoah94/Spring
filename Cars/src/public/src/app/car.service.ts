import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http: HttpClient){}

  allCars(): Observable<any>{
    return this.http.get("http://localhost:8080/cars/all");
  }

  findCar(id){
    return this.http.get("http://localhost:8080/car/" + id);
  }
}
