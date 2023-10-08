import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectWorkingDetailComponent } from './project-working-detail.component';

describe('ProjectWorkingDetailComponent', () => {
  let component: ProjectWorkingDetailComponent;
  let fixture: ComponentFixture<ProjectWorkingDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectWorkingDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectWorkingDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
