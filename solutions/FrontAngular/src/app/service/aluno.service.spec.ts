import { TestBed } from '@angular/core/testing';

import { AlunoService } from './aluno.service';

describe('AlunoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AlunoService = TestBed.get(AlunoService);
    expect(service).toBeTruthy();
  });
});
