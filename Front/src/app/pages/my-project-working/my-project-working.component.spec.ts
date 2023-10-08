import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyProjectWorkingComponent } from './my-project-working.component';

describe('MyProjectWorkingComponent', () => {
  let component: MyProjectWorkingComponent;
  let fixture: ComponentFixture<MyProjectWorkingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyProjectWorkingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyProjectWorkingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
