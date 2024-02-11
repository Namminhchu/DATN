import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ManageOrderService } from '../../services/manage-order.service';
import { BookingService} from '../../../shop/services/booking.service';

import { formatDate } from '@angular/common';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private manageOrderService: ManageOrderService,
              // tslint:disable-next-line:no-shadowed-variable
              private bookingService: BookingService,
              private router: Router,
              private toastr: ToastrService) {
    }

  revenues1: any[] = [];
  years: number[] = [2020, 2021, 2022, 2023, 2024];
  year: any = 2021;
  yearss: any = 2021;
  revenues: any[] = [];
  revenuesAppoint: any[] = [];
  // Trong component.ts

  view: any[] = [1200, 370];

  // options
  legendTitle: string = 'Doanh thu năm ' + this.year;
  legendPosition = 'below'; // ['right', 'below']
  legend = true;

  xAxis = true;
  yAxis = true;

  yAxisLabel = 'Tổng giá trị đơn đặt hàng';
  yAxisLabel1 = 'Tổng giá trị lịch hẹn';
  xAxisLabel: string = 'Doanh thu năm ' + this.year + ' Đơn vị (đ)';
  showXAxisLabel = true;
  showYAxisLabel = true;

  // Trục Oy
  yAxisTicks: any[] = [5000000, 10000000, 20000000, 40000000, 60000000];

  animations = true; // animations on load

  showGridLines = true; // grid lines

  // Màu từng cột
  colorScheme = {
    domain: ['rgba(90,31,220,0.9)', 'rgba(236,11,11,0.87)', '#f1dd58', 'rgba(73,238,39,0.7)',
    '#25706F', '#704FC4', '#4B852C', '#B67A3D', '#5B6FC8',
    '#25706F', '#4B852C']
  };
  schemeType = 'ordinal'; // 'ordinal' or 'linear'
  // Khoảng cách 2 cột
  barPadding = 50;
  // Hiển thị số lượng khi hover vào cột
  tooltipDisabled = false;

  // Thuộc tính biểu đồ tròn
  gradient = false;
  showLabels = true;
  isDoughnut = true;

  selectedDate: Date = new Date(); // Mặc định là ngày hiện tại

  // Event biểu đồ tròn
  formatDate: any;

  ngOnInit(): void {
    this.checkUser();
    this.getRevenue();
    this.getAppiontment();
  }

  checkUser() {
    const currentUser = JSON.parse(localStorage.getItem('userCurrent'));

    if (!currentUser) {
      this.router.navigate(['/home']);
      return;
    }
    let checked = false;
    currentUser.roles.forEach(role => {
      if (role === 'ADMIN') {
        checked = true;
      }
    });
    if (!checked) {
      this.toastr.error('Từ chối truy cập');
      this.router.navigate(['/home']);
      return;
    }
  }

  // Chuyển tên từng cột thành chữ hoa
  formatString(input: string): string {
    return input.toUpperCase();
  }

  formatNumber(input: number): number {
    return input;
  }

  // Thay đổi năm
  changeYear(event) {
    this.year = event.value;
    this. legendTitle = 'Doanh thu năm ' + this.year;
    this.xAxisLabel = 'Doanh thu năm ' + this.year + ' Đơn vị (đ)';
    this.getRevenue();
  }

  getRevenue() {
    this.manageOrderService.getRevenue(this.year).subscribe(data => {
      if (null === data) {
        this.toastr.error('Từ chối truy cập');
        this.router.navigate(['/home']);
      }
      this.revenues = data;
    });
  }




  revenues1Pie: any[] = [];
  revenues1Bar: any[] = [];
  changeYear1(event) {
    this.year = event.value;
    this. legendTitle = 'Doanh thu năm ' + this.year;
    this.xAxisLabel = 'Doanh thu năm ' + this.year + ' Đơn vị (đ)';
    this.getAppiontment();
  }

  getAppiontment() {
    this.bookingService.getAppointment(this.year).subscribe(data => {
      if (null === data) {
        this.toastr.error('Từ chối truy cập');
        this.router.navigate(['/home']);
      }
      this.revenuesAppoint = data;
    });
  }



  onSelect(data): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }
  onActivate(data): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
}
