import { Pipe, PipeTransform} from '@angular/core';
import { Customer} from './types';

@Pipe({
  name: 'filter'
})

export class FilterPipe implements PipeTransform {
  transform(customers: Customer[], searchTerm: string): Customer[] {
    if (!customers || !searchTerm) {
      return customers;
    }

    return customers.filter(
      customer =>
        customer.secondName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1
    );
  }
}
