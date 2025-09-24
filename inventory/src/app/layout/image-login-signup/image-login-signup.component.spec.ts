import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageLoginSignupComponent } from './image-login-signup.component';

describe('ImageLoginSignupComponent', () => {
  let component: ImageLoginSignupComponent;
  let fixture: ComponentFixture<ImageLoginSignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImageLoginSignupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImageLoginSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
