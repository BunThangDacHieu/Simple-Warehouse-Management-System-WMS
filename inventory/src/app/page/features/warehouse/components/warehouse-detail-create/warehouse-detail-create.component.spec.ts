import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseDetailCreateComponent } from './warehouse-detail-create.component';

describe('WarehouseDetailCreateComponent', () => {
  let component: WarehouseDetailCreateComponent;
  let fixture: ComponentFixture<WarehouseDetailCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WarehouseDetailCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WarehouseDetailCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
