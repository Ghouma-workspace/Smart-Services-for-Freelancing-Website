import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectWorkingService } from 'src/app/services/project-working.service';
import { ProjectWorking } from 'src/app/shared/project-working';
import noUiSlider from "nouislider";


@Component({
  selector: 'app-project-working-detail',
  templateUrl: './project-working-detail.component.html',
  styleUrls: ['./project-working-detail.component.scss']
})
export class ProjectWorkingDetailComponent implements OnInit, AfterViewInit {

  project: ProjectWorking;
  projectId : number;
  username: string;
  selectedFile: File = null;

  constructor(
    private route: ActivatedRoute,
    private projectWorkingService: ProjectWorkingService
  ) {   }

  ngOnInit(): void {
    this.project = new ProjectWorking();

    this.projectId=this.route.snapshot.params['id'];
    this.projectWorkingService.getProject(this.projectId).subscribe(data => {
      this.project = data;
    })
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
  }

  // onUpload() {
  //   const formData = new FormData();
  //   formData.append('file', this.selectedFile, this.selectedFile.name);
  // }

  ngAfterViewInit(){
      var slider = document.getElementById("test");
      noUiSlider.create(slider, {
        start: 60,
        connect: [true, false],
        range: {
          min: 0,
          max: 100
        }
      });
    }

}
