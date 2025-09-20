import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'calcTotalItem',
  standalone: true
})
export class CalcTotalItemPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
