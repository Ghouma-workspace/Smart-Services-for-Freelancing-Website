import { TestBed } from '@angular/core/testing';

import { ProjectWorkingService } from './project-working.service';

describe('ProjectWorkingService', () => {
  let service: ProjectWorkingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectWorkingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
