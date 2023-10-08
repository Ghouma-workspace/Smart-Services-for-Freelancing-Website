import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Chart from 'chart.js';
import { ProjectService } from 'src/app/services/project.service';
import { Project } from 'src/app/shared/project';
import { chartExample1, chartExample2, chartOptions, parseOptions } from 'src/app/variables/charts';

@Component({
  selector: 'app-allprojects',
  templateUrl: './AllProjects.component.html',
  styleUrls: ['./AllProjects.component.scss']
})
export class AllProjectsComponent implements OnInit {
    public datasets: any;
    public data: any;
    public salesChart;
    public clicked: boolean = true;
    public clicked1: boolean = false;
    projects: Project[] = [];
    public projectsrec: Project[] = [];
  
    test: String ;
  
    //Ena zedtou !!!!
    constructor(private projectService: ProjectService,  private route: ActivatedRoute, private router: Router) {}
    ////////////
  ngOnInit(){
    //Ena zedtha !!!!
    this.listProjects();
    this.listProjectsRec();
    /////////////////////
    

  }

  //Ena zedtha !!!!
   listProjects(){
    this.projectService.getProjectList().subscribe(
      data => {
        this.projects = data;
      }
    )
  }
  listProjectsRec(){
    this.projectService.getAllProjectRec().subscribe(
      data => {
        this.projectsrec = data;
      }
    );
  }
  goToDetail(id: number){
    this.router.navigate(['project-detail', id]);
  }

}