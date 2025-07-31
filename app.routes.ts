import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { EmployeeDashboardComponent } from './employee-dashboard/employee-dashboard.component';
import { ViewEmployeeDetailsComponent } from './view-employee-details/view-employee-details.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { ViewEmployeesComponent } from './view-employees/view-employees.component';
import { ViewAssignedProjectComponent } from './view-assigned-project/view-assigned-project.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { ViewProjectsComponent } from './view-projects/view-projects.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { ProjectsWithoutEmployeesComponent } from './projects-without-employees/projects-without-employees.component';
import { UnderstaffedProjectsComponent } from './understaffed-projects/understaffed-projects.component';
import { ViewAvailableEmployeesComponent } from './view-available-employees/view-available-employees.component';
import { SearchEmployeesByDesignationComponent } from './search-employees-by-designation/search-employees-by-designation.component';
import { ManagerDashboardComponent } from './manager-dashboard/manager-dashboard.component';
import { UpdateSkillsComponent } from './update-skills/update-skills.component';
import { ManageRoleRequestsComponent } from './manage-role-requests/manage-role-requests.component';
import { RaiseEmployeeRequestComponent } from './raise-employee-request/raise-employee-request.component';
import { ViewMatchingEmployeesComponent } from './view-matching-employees/view-matching-employees.component';
import { ManagerRequestStatusComponent } from './manager-request-status/manager-request-status.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { GetMatchingProjectsComponent } from './get-matching-projects/get-matching-projects.component';
 
 
export const routes: Routes = [
  { path: '', component: LoginComponent },       // Default route
  { path: 'signup', component: SignupComponent }, // Signup route
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'employee-dashboard', component: EmployeeDashboardComponent },  
  { path: 'view-employee-details', component: ViewEmployeeDetailsComponent},
  {path: 'view-assigned-project', component: ViewAssignedProjectComponent},
  { path: 'add-employee', component: AddEmployeeComponent },
  { path: 'view-employees', component: ViewEmployeesComponent },
  { path: 'add-project', component: AddProjectComponent },    
  { path: 'view-projects', component: ViewProjectsComponent },
  {path: 'update-employee', component: UpdateEmployeeComponent},
  { path: 'projects-without-employees', component: ProjectsWithoutEmployeesComponent},
  {path: 'understaffed-projects', component: UnderstaffedProjectsComponent},
  {path: 'view-available-employees', component: ViewAvailableEmployeesComponent},
  {path: 'search-employees-by-designation', component: SearchEmployeesByDesignationComponent},
  { path: 'manager-dashboard', component: ManagerDashboardComponent },
  { path: 'update-skills', component: UpdateSkillsComponent },
  { path: 'update-password', component: UpdatePasswordComponent },
  {path: 'raise-employee-request', component: RaiseEmployeeRequestComponent},
  {path: 'manage-role-requests', component: ManageRoleRequestsComponent},
  {path: 'view-matching-employees/:id', component: ViewMatchingEmployeesComponent},
  {path: 'manager-request-status', component: ManagerRequestStatusComponent},
  {path: 'get-matching-projects', component: GetMatchingProjectsComponent},

  { path: '**', redirectTo: '' },
  
  // {
  //   path: 'manager-dashboard',
  //   loadComponent: () => import('./manager-dashboard/manager-dashboard.component').then(m => m.ManagerDashboardComponent)
  // },

  

           
];
 
 