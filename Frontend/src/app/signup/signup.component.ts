import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';

 
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
 
  user = {
    empName: '',
    username: '',
    password: '',
    role: '',
    designation: '',
    available: '',
    proId: 0,
    skills: {
      Java: false,
      Cpp: false,
      Python: false,
      SpringBoot: false,
      SQL: false,
      MEAN: false,
      MERN: false
    },
    skillSet: ''
  };
 
  skillValidationError = false;
  projectIds: number[] = [];
  isDarkMode: boolean = false;
 
  constructor(private http: HttpClient, private router: Router) {}
 
  ngOnInit(): void {
      this.fetchProjectIds();
     
  }
 
  fetchProjectIds(): void{
    this.http.get<number[]>('http://localhost:8080/getAllProjectIds').subscribe({
      next: (data)=> {
        this.projectIds = data;
      },
      error:(err) => {
        console.error("Error fetching project IDs:", err);
      }
    });
  }
 
  onSubmit() {
    const selectedSkills = Object.entries(this.user.skills)
      .filter(([, value]) => value)
      .map(([key]) => key);
 
    if (selectedSkills.length === 0) {
      this.skillValidationError = true;
      return;
    }
 
    this.skillValidationError = false;
 
    const payload = {
      empName: this.user.empName,
      username: this.user.username,
      password: this.user.password,
      role: this.user.role,
      designation: this.user.designation,
      available: this.user.available,
      proId: this.user.proId,
      skills: selectedSkills.join(',')
    };
 
    console.log('Signup payload', payload);
   
    this.http.post('http://localhost:8080/signup', payload, {responseType: 'text'}).subscribe({
      next: (response) => {
        console.log('Signup successful:', response);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Signup failed:', error);
      }
    });
  }
 
}
 