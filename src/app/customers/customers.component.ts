import { Component, OnInit } from '@angular/core';
import {formatNumber} from '@angular/common';
import {CustomersService} from './customers.service';
import {Customer} from './types';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  selectedCustomers: Customer;

  constructor(private customersService: CustomersService) { }

  selectCustomers(id: number) {
      // edit
      if (id) {
        this.customersService
          .retrieve(id)
          .then(result => (this.selectedCustomers = result));
      } else {
        // add
        this.selectedCustomers = new Customer();
      }
  }


  ngOnInit() {
  }

}
