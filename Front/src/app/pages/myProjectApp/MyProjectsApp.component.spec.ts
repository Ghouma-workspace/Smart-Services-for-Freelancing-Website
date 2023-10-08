import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllProjectsAppComponent } from './MyProjectsApp.component'; 

describe('AllProjectsComponent', () => {
  let component: AllProjectsAppComponent;
  let fixture: ComponentFixture<AllProjectsAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllProjectsAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllProjectsAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});