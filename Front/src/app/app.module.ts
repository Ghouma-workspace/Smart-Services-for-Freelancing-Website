import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { ToastrModule } from 'ngx-toastr';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { ProjectService } from './services/project.service';
import { AllProjectsComponent } from './pages/AllProjects/AllProjects.component';
import {MatDialogModule} from '@angular/material/dialog';
import { AllProjectsAppComponent } from './pages/AllProjectsApp/AllProjectsApp.component';
import { ProjectAppService } from './services/project-app.service';
import { AuthService } from './pages/auth.service';
import { FreelancerService } from './services/freelancer.service';
import { ProfileService } from './services/profile.service';
import { MyProjectsAppComponent } from './pages/myProjectApp/MyProjectsApp.component';
import { SearchService } from './services/search.service';
import { HomeComponent } from './pages/Home/home.component';
import { MyProjectWorkingComponent } from './pages/my-project-working/my-project-working.component';
import { AllCommentsComponent } from './pages/all-comments/all-comments.component';
import { ForumComponent } from './pages/forum/forum.component';
import { CommentsService } from './services/comments.service';
import { ReactiveFormsModule } from '@angular/forms';
import { ContactComponent } from './pages/contact/contact.component';
import { ProjectWorkingDetailComponent } from './pages/project-working-detail/project-working-detail.component';
import { ProjectWorkingService } from './services/project-working.service';
import { ClientComponent } from './pages/client/client.component';




@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
    AllProjectsComponent,
    AllProjectsAppComponent,
    MyProjectsAppComponent,
    HomeComponent,
    MyProjectWorkingComponent,
    AllCommentsComponent,
    ForumComponent,
    ContactComponent,
    ClientComponent
    
  ],
  imports: [
    BrowserAnimationsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
    AppRoutingModule,
    ToastrModule.forRoot(),
    NgxWebstorageModule.forRoot(),
    MatDialogModule,
    ReactiveFormsModule
  ],
  providers: [AuthService, ProjectService, ProjectAppService, FreelancerService, ProfileService, SearchService, CommentsService, ProjectWorkingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
