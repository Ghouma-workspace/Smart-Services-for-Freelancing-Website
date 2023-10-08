import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeComponent } from 'src/app/pages/Home/home.component';
import { ForumComponent } from 'src/app/pages/forum/forum.component';
import { ProjectWorking } from 'src/app/shared/project-working';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
    component?: any; 
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/user-profile', title: 'User profile',  icon:'ni-single-02 text-yellow', class: '' },
    { path: '/all-projects', title: 'All Projects',  icon:'ni-planet text-blue', class: '' }, 
    { path: '/all-ProjectsApp', title: 'All Proejcts Applications',  icon:'ni-planet text-blue', class: '' },
    { path: '/forum', title: 'forum des freelacers',  icon:'ni ni-chat-round', class: '',component: ForumComponent },
    {path : '/home', title : 'Home page', icon:'ni-bullet-list-67 text-red', class:'', component: HomeComponent}
    
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;

  constructor(private router: Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
  }
}
