import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'transform'
})
export class TransformPipe implements PipeTransform {

  transform(value: string, args?: any): any {
    return value.charAt(0).toUpperCase() + value.slice(1);
  }

}
