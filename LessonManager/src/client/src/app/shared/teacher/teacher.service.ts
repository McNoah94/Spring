import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from '../../../../node_modules/rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(private http: HttpClient){}

  getAll(): Observable<any>{
    console.log(this.http.get("localhost:8080/teachers"));
    return this.http.get("localhost:8080/teachers");
  }
}
