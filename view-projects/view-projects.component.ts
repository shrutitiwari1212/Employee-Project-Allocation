import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
 
 
export interface Project {
  proId: number;
  proName: string;
  proDescription: string;
  requiredSkills: string;
  capacity: number;
}
@Component({
  selector: 'app-view-projects',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-projects.component.html',
  styleUrls: ['./view-projects.component.css']
})
export class ViewProjectsComponent implements OnInit {
  projects: any[] = [];
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    this.fetchProjects();
  }
 
  fetchProjects(): void {
this.http.get<Project[]>('http://localhost:8080/getAllProjects').subscribe({
      next: (data) => {
        this.projects = data;
      },
      error: (error) => {
        console.error('Error fetching projects:', error);
        this.errorMessage = 'Failed to load projects.';
      }
    });
  }
}
 
 
