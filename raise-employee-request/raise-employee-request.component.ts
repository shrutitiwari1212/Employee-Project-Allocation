import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
 
@Component({
  imports: [FormsModule, CommonModule],
  selector: 'app-raise-employee-request',
  templateUrl: './raise-employee-request.component.html',
  styleUrls: ['./raise-employee-request.component.css']
})
export class RaiseEmployeeRequestComponent implements OnInit {
 
  designations: string[] = ['Software Developer', 'FullStack Developer', 'Database Administrator','Senior Engineer', 'Data Analyst', 'DevOps Engineer', 'UI/UX Developer', 'HR', 'Tester', 'System Analyst', 'Network Administrator'];
  selectedDesignation: string = '';
  selectedProId: number = 0;
  managerEmpId: number = 0;
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    this.managerEmpId = Number(localStorage.getItem('empId'));
    this.fetchAssignedProjectId();
  }
 
  fetchAssignedProjectId(): void {
const url = `http://localhost:8080/getProjectByEmployeeId/${this.managerEmpId}`;
    this.http.get<any>(url).subscribe({
      next: (project) => {
        this.selectedProId = project.proId;
      },
      error: (err) => {
        console.error('Failed to fetch assigned project:', err);
      }
    });
  }
 
  raiseRequest(): void {
    const requestBody = {
      designation: this.selectedDesignation,
      manager: {
        empId: this.managerEmpId
      },
      project: {
        proId: this.selectedProId
      }
    };
 
// raiseRequest(): void {
//   const requestBody = {
//     designation: this.selectedDesignation,
//     managerEmpId: this.managerEmpId,
//     proId: this.selectedProId
//   };

  const url = 'http://localhost:8080/raiseRequest';
  this.http.post(url, requestBody).subscribe({
    next: () => {
      alert('Request raised successfully!');
      this.selectedDesignation = '';
    },
    error: (err) => {
      console.error('Error:', err);
      alert('Failed to raise request.');
    }
  });
}
}
 