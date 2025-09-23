import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseDetailUpdateComponent } from './warehouse-detail-update.component';

describe('WarehouseDetailUpdateComponent', () => {
  let component: WarehouseDetailUpdateComponent;
  let fixture: ComponentFixture<WarehouseDetailUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WarehouseDetailUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WarehouseDetailUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
