import { Component, NgModule, OnInit } from '@angular/core';
import { ProfileService } from 'src/app/services/profile.service';
import { Profile } from 'src/app/shared/userProfile';
import { AuthService } from '../auth.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-forum',
  templateUrl: './Edit.component.html',
  styleUrls: ['./Edit.component.scss']
})
export class EditComponent implements OnInit {
  profile: Profile = new Profile();
  profilePicFile: File;
  profileCoverFile: File;
  photoUploaded: any;
  username: string;
  test : boolean;

  constructor(private profileService: ProfileService, private authService: AuthService, private http: HttpClient) { }

  ngOnInit() {
    this.authService.username.subscribe((data: string) => this.username = data);
    this.username = this.authService.getUserName();
    this.profileService.getProfileByUser(this.username).subscribe(data => {
      this.profile = data;
    })
  }
  
  getProfile(id: number): void {
    this.profileService.getProfile(id)
      .subscribe(profile => {
        this.profile = profile;
      });
  }

  handleProfilePicFileInput(event: any): void {
    const formData = new FormData();
    formData.append('file', event.target.files[0]);
    this.http.post("http://localhost:5000/verifpic", formData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          console.log("successful");
        } else {
          console.log("unsuccessful");
        }
        console.log(response.body);
        if (JSON.stringify(response.body) === JSON.stringify({ "success": true })){
          this.test = false;
          this.profilePicFile = event.target.files[0];
        }
        else {
          this.test = true;
        }
      }
    );
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.photoUploaded = reader.result;
   };
  }

  handleProfileCoverFileInput(event: any): void {
    this.profileCoverFile = event.target.files[0];
  }

  submitClick(bio: string, xp: string, skill: string, ay: string): void {
    this.profile.bio = bio;
    this.profile.experience = xp;
    this.profile.skills = skill;
    this.profile.aboutMe = ay;
    this.profileService.updateProfile(this.profile.id, this.profile)
      .subscribe(() => {
        this.getProfile(this.profile.id);
      });
    if (this.profilePicFile){
      this.profileService.updateUserProfilePic(this.profile.id, this.profilePicFile);
    }
    if (this.profileCoverFile){
      this.profileService.updateUserProfileCover(this.profile.id, this.profileCoverFile);
    }
  }

}
export class ForumModule { }