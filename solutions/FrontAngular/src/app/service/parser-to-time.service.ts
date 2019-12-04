import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ParserToTimeService {

  constructor() { }

  parser(object: any){
    if(object){ 
      var day = object.dayOfMonth;
      var month = object.monthValue - 1; // Month is 0-indexed
      var year = object.year;
      var hours = object.hours;
      var minutes = object.minutes;

      return new Date(Date.UTC(year, month, day, hours, minutes));
    }
    return null;

  }
}