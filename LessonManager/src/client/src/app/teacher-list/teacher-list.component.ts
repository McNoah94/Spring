import { Component, OnInit } from '@angular/core';
import { TeacherService } from '../shared/teacher/teacher.service';

@Component({
  selector: 'app-teacher-list',
  templateUrl: './teacher-list.component.html',
  styleUrls: ['./teacher-list.component.css']
})
export class TeacherListComponent implements OnInit {
  teachers: Array<any>;

  constructor(private ts: TeacherService) { }

  ngOnInit() {
    this.ts.getAll().subscribe(data => {
      this.teachers = data;
    })
  }
}
