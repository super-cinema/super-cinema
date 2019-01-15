import { Pipe, PipeTransform } from '@angular/core';
import {Crew} from '../crew/model/crew';

@Pipe({
  name: 'sortName'
})
export class SortNamePipe implements PipeTransform {

  transform(value: Array<Crew>, args?: any): Array<Crew> {
    return value.sort((a, b) => {
      if (a.surname.toLowerCase() > b.surname.toLowerCase()) {
        return 1;
      } else {
        return -1;
      }
    });
  }

}
