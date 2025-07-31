import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-update-skills',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-skills.component.html',
  styleUrls: ['./update-skills.component.css']
})
export class UpdateSkillsComponent {
  allSkills: string[] = ['Java', 'CPP', 'Python', 'SQL', 'SpringBoot', 'MEAN', 'MERN', 'Testing', 'Automation testing', 'AWS', 'Docker', 'DevOps', 'SDLC', 'Agile'];
  selectedSkills: string[] = [];
  updateMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
//   ngOnInit(): void {
//     const empId = localStorage.getItem('empId');
//     if (empId) {
// this.http.get<string>(`http://localhost:8080/employeeSkills/${empId}`,{ responseType: 'text' as 'json'}).subscribe(
//    (response: string) => {
//           this.selectedSkills = response.split(',').map(skill => skill.trim());
//         },
//         (error) => {
//           console.error('Error fetching skillset', error);
//         }
//       );
//     }
//   }


  
ngOnInit(): void {
  const empId = localStorage.getItem('empId');
  if (empId) {
    this.http.get<any>(`http://localhost:8080/employeeSkills/${empId}`).subscribe(
      (response: string[]) => {
        this.selectedSkills = response;
      },
      (error) => {
        console.error('Error fetching skillset', error);
      }
    );
  }
}

 
  onCheckboxChange(skill: string, event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    if (isChecked && !this.selectedSkills.includes(skill)) {
      this.selectedSkills.push(skill);
    } else if (!isChecked) {
      this.selectedSkills = this.selectedSkills.filter(s => s !== skill);
    }
  }
 
  updateSkills(): void {
    const empId = localStorage.getItem('empId');
    if (!empId) {
      console.error('Employee ID not found');
      return;
    }
 
    // const body = {
    //   skillSet: this.selectedSkills.join(',')
    // };
 
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
 
  this.http.put(`http://localhost:8080/updateEmployeeSkills/${empId}`, this.selectedSkills, { headers, responseType: 'text' }).subscribe({
      next: () => {
        alert('Skill set updated successfully');
        this.updateMessage = 'Skills updated successfully';
      },
      error: err => {
        console.error('Failed to update skill set', err);
        alert('Failed to update skill set');
        this.updateMessage = 'Failed to update skills.';
      }
    });
  }

  newSkill: string = '';

  addNewSkill(): void {
    const trimmedSkill = this.newSkill.trim();
    if (trimmedSkill && !this.allSkills.includes(trimmedSkill)) {
      this.allSkills.push(trimmedSkill);
      this.selectedSkills.push(trimmedSkill);
      this.newSkill = ''; //  Clear input
    } else if (this.allSkills.includes(trimmedSkill)) {
      alert('Skill already exists.');
    }
  }

}