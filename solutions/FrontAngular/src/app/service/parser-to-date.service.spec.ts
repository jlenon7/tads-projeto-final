import { TestBed } from '@angular/core/testing';

import { ParserToDateService } from './parser-to-date.service';

describe('ParserToDateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ParserToDateService = TestBed.get(ParserToDateService);
    expect(service).toBeTruthy();
  });
});