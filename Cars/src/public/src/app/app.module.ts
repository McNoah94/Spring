import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarsComponent } from './cars/cars.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CarService } from './car.service';

import { HttpClientModule } from '@angular/common/http';
import { CarComponent } from './car/car.component'

@NgModule({
  declarations: [
    AppComponent,
    CarsComponent,
    HomeComponent,
    LoginComponent,
    CarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [CarService],
  bootstrap: [AppComponent]
})
export class AppModule { }
