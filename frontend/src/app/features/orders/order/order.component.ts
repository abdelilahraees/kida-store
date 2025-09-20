import {Component, Input, OnInit} from '@angular/core';
import {DatePipe, NgForOf} from "@angular/common";
import {Order} from "../../../core/interfaces/order";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [
    NgForOf,
    DatePipe,
    RouterLink
  ],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent {
  @Input({required:true}) orders: Order[] = []

  constructor() {
  }


}
