import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';  
@Component({
  selector: 'app-view-employee-details',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './view-employee-details.component.html',
  styleUrls: ['./view-employee-details.component.css']
})
export class ViewEmployeeDetailsComponent implements OnInit {
 
  empId: number = 0;
  employee: any = null;
  errorMessage: string = '';
 
  constructor(private http: HttpClient, private router: Router) {}
 
  ngOnInit() {
    // Auto fetch employeeId from localStorage if available
    const savedEmpId = localStorage.getItem('empId');
    if (savedEmpId) {
      this.empId = Number(savedEmpId);
      this.fetchEmployeeDetails();
    }
  }
 
  fetchEmployeeDetails() {
    if (this.empId > 0) {  // Avoid calling API with invalid ID
this.http.get(`http://localhost:8080/getEmployeeById/${this.empId}`)
        .subscribe({
          next: (data) => {
            this.employee = data;
            this.errorMessage = '';
          },
          error: (error) => {
            this.errorMessage = 'Employee not found or error fetching details';
            this.employee = null;
          }
        });
    } else {
      this.errorMessage = 'Invalid Employee ID';
    }
  }

  goBackToDashboard(): void {
  this.router.navigate(['/employee-dashboard']);
  }

}
