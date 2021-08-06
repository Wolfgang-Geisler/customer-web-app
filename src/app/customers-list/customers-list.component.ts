import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Customer} from '../customers/types';
import {CustomersService} from '../customers/customers.service';


@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: ['./customers-list.component.css']
})
export class CustomersListComponent implements OnInit {

  constructor(private customersService: CustomersService) {}
  @Output() choose = new EventEmitter<number>();
  customersList: Customer[];
  searchTerm: string;
  private hideElement: boolean;

  toggleElement() {
    if (this.hideElement) {
      return false;
    } else {
      return true;
    }
  }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    console.log('Refreshing...');
    this.customersService
      .retrieveAll()
      .then(result => (this.customersList = result));
  }

  onDeleteCustomers(id: number) {
    if (confirm('Do you really want to delete the customer?')) {
      this.customersService.delete(id).then(() => this.refresh());
    }
  }

  onAddCustomers() {
    this.choose.emit();
  }

  onEditCustomers(id: number) {
   this.choose.emit(id);
  }
}
