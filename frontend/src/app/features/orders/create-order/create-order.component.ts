import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {JsonPipe, NgClass, NgIf} from "@angular/common";
import {OrderService} from "../services/order.service";
import {NotificationService} from "../../notification/services/notification.service";
import {Router} from "@angular/router";
import {OrderAddress} from "../../../core/interfaces/order-address";

interface addressForm {
  city: FormControl<string>
  street: FormControl<string>
  houseNumber: FormControl<string>
  postCode: FormControl<string>
  country: FormControl<string>
}

@Component({
  selector: 'app-create-order',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgClass,
    NgIf,
    JsonPipe
  ],
  templateUrl: './create-order.component.html',
  styleUrl: './create-order.component.css'
})
export class CreateOrderComponent {
  addressForm!: FormGroup<addressForm>
  private address: OrderAddress = {} as OrderAddress

  constructor(private fb: FormBuilder, private orderService: OrderService, private notification: NotificationService, private router: Router) {
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm(): void {
    this.addressForm = this.fb.group({
      city: this.fb.nonNullable.control<string>('', [Validators.required]),
      street: this.fb.nonNullable.control<string>('', [Validators.required]),
      houseNumber: this.fb.nonNullable.control<string>('', [Validators.required]),
      postCode: this.fb.nonNullable.control<string>('', [Validators.required]),
      country: this.fb.nonNullable.control<string>('', [Validators.required])
    });
  }

  onSubmit() {
    this.address.city = this.addressForm.value.city || ''
    this.address.street = this.addressForm.value.street || ''
    this.address.houseNumber = this.addressForm.value.houseNumber || ''
    this.address.postCode = this.addressForm.value.postCode || ''
    this.address.country = this.addressForm.value.country || ''
    this.orderService.addOrder(this.address).subscribe({
      next: () => {
        this.notification.show("order created ", 4000)
        this.router.navigate(['/'])
      }
    });

  }

  private markAllFieldsAsTouched(): void {
    Object.keys(this.addressForm.controls).forEach(key => {
      this.addressForm.get(key)?.markAsTouched();
    });
  }


  getContols() {
    return this.addressForm.controls
  }


}
