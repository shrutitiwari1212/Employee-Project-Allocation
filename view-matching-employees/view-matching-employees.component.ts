import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute , Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-view-matching-employees',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './view-matching-employees.component.html',
  styleUrls: ['./view-matching-employees.component.css']
})
export class ViewMatchingEmployeesComponent implements OnInit {
  matchingEmployees: any[] = [];
  requestId: number = 0;
  isLoading: boolean = true;
  errorMessage: string = '';
  proId: number | undefined;
  proName: string | undefined;
  successMessage: string = '';
 
 
  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {}
 
  ngOnInit(): void {
    this.requestId = Number(this.route.snapshot.paramMap.get('id'));
 
    // const navigation = this.router.getCurrentNavigation();
    // const state = navigation?.extras?.state as {proId?: number; proName?: string};
    // this.proId = state?.proId;
    // this.proName = state?.proName;
 
    if (this.requestId) {
      this.fetchMatchingEmployees();
    } else {
      this.errorMessage = 'Invalid Request ID.';
      this.isLoading = false;
    }
  }
 
  fetchMatchingEmployees(): void {
    this.http.get<any[]>(`http://localhost:8080/getEligibleEmployees/${this.requestId}`)
      .subscribe({
        next: (data) => {
          this.matchingEmployees = data;
          if(data.length > 0){
            this.proId = data[0].proId;
            this.proName = data[0].proName;
          }
          this.isLoading = false;
        },
        error: (err) => {
          this.errorMessage = 'Failed to fetch matching employees.';
          this.isLoading = false;
        }
      });
  }
 
  assignEmployee(empId: number): void{
    // this.successMessage = '';
    // this.errorMessage = '';
 
    this.http.put(`http://localhost:8080/assignEmployee?id=${this.requestId}&empId=${empId}`, null, {responseType: 'text'})
    .subscribe({
      next: (response: string) => {
        console.log("Assignmant successful:", response);
        alert(response);
        //this.successMessage = 'Employee ${empId} assigned successfully.';
        //this.matchingEmployees = this.matchingEmployees.filter(e=> e.empId !== empId);
      },
      error: () => {
        alert('Failed to assign employee');
      }
    })
  }
}
 