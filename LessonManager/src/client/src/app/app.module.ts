import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TeacherService } from './shared/teacher/teacher.service';

import { AppComponent } from './app.component';
import { HttpClientModule } from '../../node_modules/@angular/common/http';
import { TeacherListComponent } from './teacher-list/teacher-list.component';

@NgModule({
  declarations: [
    AppComponent,
    TeacherListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [TeacherService],
  bootstrap: [AppComponent]
})
export class AppModule { }
