import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'filterPipe'})
export class FilterPipe implements PipeTransform {

  transform(crew: any[], term: any): any[] {

    if (term === undefined) {  return crew; }

    return crew.filter(function(crew) {
      return crew.name.toLowerCase().includes(term.toLowerCase());
    });
  }
  //
  // transform(items: any[], filter: Object): any {
  //   if (!items || !filter) {
  //     return items;
  //   }
  //   return items.filter(item => item.title.indexOf(filter.title) !== -1);
  // }

}
