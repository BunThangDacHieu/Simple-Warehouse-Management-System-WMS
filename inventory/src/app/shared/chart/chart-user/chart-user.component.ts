import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {UsersService} from '../../../page/features/user/services/users.service';
import { ChartModule } from 'primeng/chart';
import {CommonModule} from '@angular/common';


@Component({
  selector: 'app-chart-user',
  imports: [ChartModule, CommonModule],
  templateUrl: './chart-user.component.html',
  styleUrl: './chart-user.component.scss'
})
export class ChartUserComponent implements OnInit {
  dataChart: any;
  options: any;
  constructor(private userService: UsersService, private cd: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.userService.getUserRolebyCount().subscribe(data => {
      let roleArray: any[] = [];

      if(!Array.isArray(data)){
        roleArray = Object.keys(data).filter(key => key != 'MANAGER').map(key => ({
          role: key,
          count: data[key]
        }))
      } else{
        roleArray = data.filter(key => key != 'MANAGER')
      }


      const role_data = roleArray.map(c => c.role);
      const count_data = roleArray.map(c => c.count);
      const documentStyle = getComputedStyle(document.documentElement);
      const textColor = documentStyle.getPropertyValue('--text-color');

      this.dataChart = {
        labels: role_data,
        datasets: [{
          data: count_data,
          backgroundColor: [
            documentStyle.getPropertyValue('--p-cyan-500'),
            documentStyle.getPropertyValue('--p-orange-500'),
            documentStyle.getPropertyValue('--p-gray-500')],
          hoverBackgroundColor: [
            documentStyle.getPropertyValue('--p-cyan-400'),
            documentStyle.getPropertyValue('--p-orange-400'),
            documentStyle.getPropertyValue('--p-gray-400')
          ]
        }]
      }
      this.options = {
        plugins: {
          legend: {
            labels: {
              usePointStyle: true,
              color: textColor
            }
          }
        }
      };
      this.cd.markForCheck()

    })
  }
  getLegendColor(index: number): string {
    return this.dataChart.datasets[0].backgroundColor[index];
  }
}
