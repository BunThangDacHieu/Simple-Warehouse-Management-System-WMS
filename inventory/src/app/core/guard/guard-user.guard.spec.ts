import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { guardUserGuard } from './guard-user.guard';

describe('guardUserGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => guardUserGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
