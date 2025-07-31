import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { Title } from '@angular/platform-browser';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
constructor(private titleService: Title) {
    this.titleService.setTitle('Employee Management');
  }

  // title = 'Employee Management';
 

  

  ngOnInit(): void {

  }

  
}

 