import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-update-order',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf
  ],
  templateUrl: './update-order.component.html',
  styleUrl: './update-order.component.css'
})
export class UpdateOrderComponent {
  @Input() orderId!: string | number;
  @Input() currentStatus!: string;

  @Output() close = new EventEmitter<void>();
  @Output() save = new EventEmitter<string>();

  selectedStatus!: string;
  statuses = ['Processing', 'Pending', 'Confirmed', 'Cancelled','Delivered', 'Shipped', 'Returned'];

  ngOnInit(): void {
    this.selectedStatus = this.currentStatus;
  }

  onSave(): void {
    if (this.selectedStatus !== this.currentStatus) {
      this.save.emit(this.selectedStatus);
      console.log()
    }
  }

  onClose(): void {
    this.close.emit();
  }

}
