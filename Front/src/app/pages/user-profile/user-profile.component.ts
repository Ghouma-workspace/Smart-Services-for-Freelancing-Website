import { Component, OnInit } from '@angular/core';
import { ProfileService } from 'src/app/services/profile.service';
import { Profile } from 'src/app/shared/userProfile';
import { AuthService } from '../auth.service';
import { HttpClient } from '@angular/common/http';
import { FreelancerService } from 'src/app/services/freelancer.service';
import { Freelancer } from 'src/app/shared/freelancer';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  profile: Profile;
  profilePicFile: File;
  username: string;
  profilePicUrl: string;
  varaib: string;
  imageURL: string;
  profilePicBase64: string;
  profileCoverBase64: string;
  freelancers: Freelancer[];
  me: Freelancer;


  constructor(private profileService: ProfileService, private authService: AuthService, private http: HttpClient,
    private freelancerService: FreelancerService) { }

  ngOnInit() {
    this.profile = new Profile();
    this.authService.username.subscribe((data: string) => this.username = data);
    this.username = this.authService.getUserName();
    this.profileService.getProfileByUser(this.username).subscribe(data => {
      this.profile = data;
      
      this.getProfileCover(this.profile.id);
      this.getProfilePic(this.profile.id);
    });
    this.getMe();
  }

  getProfilePic(profileId: number){
    this.http.get(`http://localhost:8080/profile/profilepic/${profileId}`, { responseType: 'arraybuffer' }).subscribe(response => {
    const base64Data = btoa(String.fromCharCode(...new Uint8Array(response)));
    this.profilePicBase64 = `data:image/jpg;base64,${base64Data}`;
  });
  }

  getProfileCover(profileId: number){
    this.http.get(`http://localhost:8080/profile/profilecover/${profileId}`, { responseType: 'arraybuffer' }).subscribe(response => {
    const base64Data = btoa(String.fromCharCode(...new Uint8Array(response)));
    this.profileCoverBase64 = `data:image/jpg;base64,${base64Data}`;
  });
  }

  getMe(){
    this.freelancerService.getAllFreelancers().subscribe(data => {
      this.freelancers = data;
      for(let fr of this.freelancers){
        if (this.username == fr.username)
          this.me = fr;
      }
    })
  }

}
