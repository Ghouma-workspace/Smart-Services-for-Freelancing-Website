import { TestBed } from '@angular/core/testing';

import { ProjectAppService } from './project-app.service';

describe('ProjectAppService', () => {
  let service: ProjectAppService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectAppService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
