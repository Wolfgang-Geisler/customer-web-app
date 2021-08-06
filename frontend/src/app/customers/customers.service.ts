import { Injectable} from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Customer} from './types';

const CUSTOMERS_RESOURCE_URL = 'http://localhost:8081/customers/resources/customers';

@Injectable({
  providedIn: 'root'
})
export class CustomersService {
  constructor(private httpClient: HttpClient) {}

  retrieveAll(): Promise<Customer[]> {
    return this.httpClient.get<Customer[]>(CUSTOMERS_RESOURCE_URL).toPromise();
  }

  retrieve(id: number): Promise<Customer> {
    return this.httpClient.get<Customer>(CUSTOMERS_RESOURCE_URL + '/' + id).toPromise();
  }

  create(customers: Customer): Promise<any> {
    return this.httpClient.post(CUSTOMERS_RESOURCE_URL, customers).toPromise();
  }

  update(customers: Customer): Promise<any> {
    return this.httpClient.put(CUSTOMERS_RESOURCE_URL + '/' + customers.id, customers).toPromise();
  }

  delete(id: number): Promise<any> {
    return this.httpClient.delete(CUSTOMERS_RESOURCE_URL + '/' + id).toPromise();
  }
}
