import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../shared/project';
import { ProjectService } from '../services/project.service';
import { NgModel } from '@angular/forms';
import { ModalComponent } from '../pages/modal/modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MatDialog } from '@angular/material/dialog';
import { ProjectAppService } from '../services/project-app.service';
import { Reclist } from '../shared/recommanded';

export interface DialogData {
  animal: 'panda' | 'unicorn' | 'lion';
}

@Component({
  selector: 'app-project-detail',
  templateUrl: './projectDetail.component.html',
  styleUrls: ['./projectDetail.component.scss']
})

export class ProjectDetailComponent implements OnInit {

  project: Project;
  projectId :number;
  showModal = false;
  lista: Reclist;
  project1: Project;
  project2: Project;
  project3: Project;
  project4: Project;
  project5: Project;
  projects: Project[] = [];

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService, private dialog: MatDialog,
     private router: Router
  ) { }

  openDialog() {
    this.dialog.open(ModalComponent, {
      data: {
        animal: 'panda',
      },
    });
  }

  ngOnInit() {
    this.project = new Project();
    this.lista = new Reclist();
    this.project1 = new Project();
    this.project2 = new Project();
    this.project3 = new Project();
    this.project4 = new Project();
    this.project5 = new Project();

    this.projectId=this.route.snapshot.params['id'];
    this.projectService.getProject(this.projectId).subscribe(data => {
      this.project = data;
    });

    this.projectService.getProjectRec(this.projectId).subscribe(data => {
      this.project = data;
      this.getRecommention(this.project.title.toLowerCase());
    });
  }

  getRecommention(title: string){
    this.projectService.getRecommandation(title).subscribe(data => {
      this.lista = data;
      console.log(this.lista)
      this.projectService.searchProjectsByTitle(this.lista.title1).subscribe(data => {
        this.project1 = data;
        this.projects.push(this.project1);
      })
      this.projectService.searchProjectsByTitle(this.lista.title2).subscribe(data => {
        this.project2 = data;
        this.projects.push(this.project2);
      })
      this.projectService.searchProjectsByTitle(this.lista.title3).subscribe(data => {
        this.project3 = data;
        this.projects.push(this.project3);
      })
      this.projectService.searchProjectsByTitle(this.lista.title4).subscribe(data => {
        this.project4 = data;
        this.projects.push(this.project4);
      })
      this.projectService.searchProjectsByTitle(this.lista.title5).subscribe(data => {
        this.project5 = data;
        this.projects.push(this.project5);
        console.log(this.projects)
      })
    });

  }

  goToDetail(id: number){
    this.router.navigate(['project-detail', id]);
  }

}
