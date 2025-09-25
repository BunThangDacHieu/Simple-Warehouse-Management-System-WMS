import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartUserComponent } from './chart-user.component';

describe('ChartUserComponent', () => {
  let component: ChartUserComponent;
  let fixture: ComponentFixture<ChartUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChartUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChartUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
