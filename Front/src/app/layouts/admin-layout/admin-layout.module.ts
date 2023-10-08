import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClipboardModule } from 'ngx-clipboard';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EditComponent } from 'src/app/pages/Edit/Edit.component';
import { ModalComponent } from 'src/app/pages/modal/modal.component';
import { ProjectDetailComponent } from 'src/app/ProjectDetail/ProjectDetail.component';
import { ProjectAppDetailComponent } from 'src/app/pages/ProjectAppDetail/ProjectAppDetail.component';
import { ComponentsModule } from 'src/app/components/components.module';
import { ProjectWorkingDetailComponent } from 'src/app/pages/project-working-detail/project-working-detail.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ClipboardModule,
    ComponentsModule
    //ModalModule.forRoot()
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    TablesComponent,
    EditComponent,
    ModalComponent,
    ProjectDetailComponent,
    ProjectAppDetailComponent,
    ProjectWorkingDetailComponent

  ]
})

export class AdminLayoutModule {}
