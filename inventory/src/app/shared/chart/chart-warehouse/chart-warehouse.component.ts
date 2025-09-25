import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {WarehouseService} from '../../../page/features/warehouse/services/warehouse.service';
import {ChartModule} from 'primeng/chart';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-chart-warehouse',
  imports: [ChartModule, CommonModule],
  templateUrl: './chart-warehouse.component.html',
  styleUrl: './chart-warehouse.component.scss'
})
export class ChartWarehouseComponent implements OnInit {
  quantityDataChart: any;
  options: any;

  constructor(private warehouseService: WarehouseService, private cd: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.warehouseService.chartWarehouseofQuantity().subscribe(data => {
      let quantityArray: any[] = [];

      if (Array.isArray(data)) {
        quantityArray = data;
      }

      // map lại đúng field
      const labels = quantityArray.map(q => q.capacity); // tên kho
      const values = quantityArray.map(q => q.name);     // sức chứa

      const documentStyle = getComputedStyle(document.documentElement);
      const textColor = documentStyle.getPropertyValue('--text-color');

      this.quantityDataChart = {
        labels: labels,
        datasets: [
          {
            label: 'Sức chứa kho',
            data: values,
            fill: false,
            borderColor: documentStyle.getPropertyValue('--p-cyan-500'),
            tension: 0.4 // độ cong line (0 = thẳng)
          }
        ]
      };

      this.options = {
        plugins: {
          legend: {
            labels: {
              color: textColor
            }
          }
        },
        scales: {
          x: {
            ticks: { color: textColor }
          },
          y: {
            ticks: { color: textColor }
          }
        }
      };

      this.cd.markForCheck();
    });
  }
}
