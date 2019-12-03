  
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ParserToDateService {

  constructor() { }

  parser(object: any){
    if(object){ 
      var day = object.dayOfMonth;
      var month = object.monthValue - 1; // Month is 0-indexed
      var year = object.year;
  
      return new Date(Date.UTC(year, month, day));
    }
    return null;
    
  }
}