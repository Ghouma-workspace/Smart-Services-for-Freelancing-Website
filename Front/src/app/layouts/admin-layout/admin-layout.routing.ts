import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
//import { IconsComponent } from '../../pages/icons/icons.component';
//import { MapsComponent } from '../../pages/maps/maps.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { ForumComponent } from 'src/app/pages/forum/forum.component';
import { AllProjectsComponent } from 'src/app/pages/AllProjects/AllProjects.component';
import { ProjectDetailComponent } from 'src/app/ProjectDetail/ProjectDetail.component';
import { EditComponent } from 'src/app/pages/Edit/Edit.component';
import { AllFreelancersComponent } from 'src/app/pages/AllFreelancers/AllFreelancers.component';
import { FreelancerDetailComponent } from 'src/app/pages/FreelancerDetail/FreelancerDetail.component';
import { AllProjectsAppComponent } from 'src/app/pages/AllProjectsApp/AllProjectsApp.component';
import { ProjectAppDetailComponent } from 'src/app/pages/ProjectAppDetail/ProjectAppDetail.component';
import { MyProjectsAppComponent } from 'src/app/pages/myProjectApp/MyProjectsApp.component';
import { HomeComponent } from 'src/app/pages/Home/home.component';
import { MyProjectWorkingComponent } from 'src/app/pages/my-project-working/my-project-working.component';
import { ContactComponent } from 'src/app/pages/contact/contact.component';
import { ProjectWorkingDetailComponent } from 'src/app/pages/project-working-detail/project-working-detail.component';
import { ClientComponent } from 'src/app/pages/client/client.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'my-projects-apps',   component: MyProjectsAppComponent },
    { path: 'tables',         component: TablesComponent },
    { path : 'forum',         component:ForumComponent},
    { path : 'all-projects'  ,   component : AllProjectsComponent},
    {path : 'project-detail/:id'  ,    component : ProjectDetailComponent},
    {path : 'edit-profile',        component: EditComponent},
    {path : 'all-freelancers',      component: AllFreelancersComponent},
    {path : 'freelancer-detail',     component: FreelancerDetailComponent},
    {path : 'all-ProjectsApp', component: AllProjectsAppComponent},
    {path : 'projectApp-detail/:id'  ,    component : ProjectAppDetailComponent},
    { path: 'home' , component: HomeComponent },
    { path: 'my-projects-working', component: MyProjectWorkingComponent },
    { path: 'contract', component: ContactComponent },
    { path: 'project-working-detail/:id', component: ProjectWorkingDetailComponent },
    { path: 'client', component: ClientComponent }
];
