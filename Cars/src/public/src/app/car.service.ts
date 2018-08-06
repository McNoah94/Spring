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

  wikiCar(make, model){
    console.log("https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + make + "%20" + model)
    return this.http.get("https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + make + "%20" + model)
  }
}
