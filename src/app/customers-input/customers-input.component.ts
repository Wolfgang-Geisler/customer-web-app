import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Customer} from '../customers/types';
import {CustomersService} from '../customers/customers.service';

@Component({
  selector: 'app-customers-input',
  templateUrl: './customers-input.component.html',
  styleUrls: ['./customers-input.component.css']
})
export class CustomersInputComponent implements OnInit {
  @Input() customers: Customer;
  @Output() ok = new EventEmitter();
  @Output() cancel = new EventEmitter();

  constructor(private customersService: CustomersService) { }

  onFinishWithOk() {
    this.createOrUpdate().then(
      () => {
        this.ok.emit();
        this.customers = null;
      }
    );
  }

  onFinishWithCancel() {
    this.cancel.emit();
    this.customers = null;
  }

  createOrUpdate(): Promise<any> {
    if (!this.customers.id) {
      return this.customersService.create(this.customers);
    } else {
      return this.customersService.update(this.customers);
    }
  }

  ngOnInit() {
  }

}
