import { Component, OnInit, ElementRef, Output, EventEmitter } from '@angular/core';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SearchService } from 'src/app/services/search.service';
import { Project } from 'src/app/shared/project';
import { ROUTES } from '../sidebar/sidebar.component';
import { ProfileService } from 'src/app/services/profile.service';
import { Profile } from 'src/app/shared/userProfile';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  template: `
    <div>
      <input #searchInput type="text" placeholder="Search...">
      <button (click)="search(searchInput.value)">Search</button>
    </div>
  `
})
export class NavbarComponent implements OnInit {
  isLoggedIn: boolean;
  username: string;
  projects: Project[];
  public focus;
  public listTitles: any[];
  public location: Location;
  profile: Profile;
  profilePicBase64: string;

  constructor(private router: Router, private authService: AuthService, private searchService: SearchService, location: Location, private profileService: ProfileService, private http: HttpClient) {
    this.location = location;
  }

  ngOnInit() {
    this.profile = new Profile();
    this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
    this.authService.username.subscribe((data: string) => this.username = data);
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();
    this.listTitles = ROUTES.filter(listTitle => listTitle);
    this.profileService.getProfileByUser(this.username).subscribe(data => {
      this.profile = data;
      this.getProfilePic(this.profile.id);
    });
    
  }

  getProfilePic(profileId: number){
    this.http.get(`http://localhost:8080/profile/profilepic/${profileId}`, { responseType: 'arraybuffer' }).subscribe(response => {
    const base64Data = btoa(String.fromCharCode(...new Uint8Array(response)));
    this.profilePicBase64 = `data:image/jpg;base64,${base64Data}`;
  });
  }
  

  logout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigateByUrl('');
  }

  getTitle(){
    var titlee = this.location.prepareExternalUrl(this.location.path());
    if(titlee.charAt(0) === '#'){
        titlee = titlee.slice( 1 );
    }

    for(var item = 0; item < this.listTitles.length; item++){
        if(this.listTitles[item].path === titlee){
            return this.listTitles[item].title;
        }
    }
    return 'Dashboard';
  }

  @Output() searchResults: EventEmitter<Project[]> = new EventEmitter<Project[]>();



  search(query: string) {
    this.searchService.searchProjectsByTitle(query).subscribe((results: Project[]) => {
      this.searchResults.emit(results);
    });
  }
}
