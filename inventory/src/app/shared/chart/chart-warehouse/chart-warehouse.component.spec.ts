import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartWarehouseComponent } from './chart-warehouse.component';

describe('ChartWarehouseComponent', () => {
  let component: ChartWarehouseComponent;
  let fixture: ComponentFixture<ChartWarehouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChartWarehouseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChartWarehouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
