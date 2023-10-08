import { Component, NgModule, OnInit } from '@angular/core';
import { ProjectService } from 'src/app/services/project.service';
import { Project } from 'src/app/shared/project';


import { ProjectAppService } from 'src/app/services/project-app.service';
import { ProjectApp } from 'src/app/shared/projectApp';
import { ProfileService } from 'src/app/services/profile.service';
import { Profile } from 'src/app/shared/userProfile';
import { Freelancer } from 'src/app/shared/freelancer';
import { FreelancerService } from 'src/app/services/freelancer.service';
import { SearchService } from 'src/app/services/search.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NavbarComponent } from 'src/app/components/navbar/navbar.component';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  template: `
    <app-navbar (searchResults)="handleSearchResults($event)"></app-navbar>
    <div *ngIf="searchResults?.length > 0">
      <h2>Search Results</h2>
      <ul>
        <li *ngFor="let result of searchResults">
          {{result.title}}
        </li>
      </ul>
    </div>
    `
})

export class DashboardComponent implements OnInit {

  public projects: Project[] = [];
  public projectsApp: ProjectApp[] = [];
  public projectsrec: Project[] = [];
  public freelancers: Freelancer[] = [];
  public profile: Profile;
  profiles: Profile[] = [];
  searchResults: Project[] = [];
  profilePicBase64: string;
  profilePic: string[];
  
  constructor(private projectService: ProjectService, private projectAppservice: ProjectAppService, private profileService: ProfileService, private freelancerService: FreelancerService, private searchService: SearchService, private route: ActivatedRoute, private router: Router, private http: HttpClient) {}

  ngOnInit() {

    this.listProjects();
    this.listAppProjects();
    this.listFreelancers();
  }

  

  handleSearchResults(results: Project[]) {
    this.searchResults = results;
  }

  listProjects(){
    this.projectService.getProjectList().subscribe(
      data => {
        this.projects = data;
      }
    );
  }

  listAppProjects(){
    this.projectAppservice.getProjectList().subscribe(
      data => {
        this.projectsApp = data;
      }
    )
  }

  listFreelancers(){
    this.freelancerService.getAllFreelancers().subscribe(
      data => {
        this.freelancers = data;
        this.listProfiles();
      }
    )
  }

  getprofile(id: number){
    this.profileService.getProfile(id).subscribe(
      data => {
        this.profile = data;
        this.profiles.push(this.profile);
      }
    )
  }
  
  listProfiles(){
    for(let fr of this.freelancers){
      console.log(fr.profileId)
      this.getprofile(fr.profileId);
      this.getProfilePic(fr.profileId);
    }
  }

  goToDetail(id: number){
    this.router.navigate(['project-detail', id]);
  }

  getProfilePic(profileId: number){
    this.http.get(`http://localhost:8080/profile/profilepic/${profileId}`, { responseType: 'arraybuffer' }).subscribe(response => {
    const base64Data = btoa(String.fromCharCode(...new Uint8Array(response)));
    this.profilePicBase64 = `data:image/jpg;base64,${base64Data}`;
    this.profilePic.push(this.profilePicBase64);
    });
  }

}
