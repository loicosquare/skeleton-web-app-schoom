import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvPublicComponent } from './cv-public.component';

describe('CvPublicComponent', () => {
  let component: CvPublicComponent;
  let fixture: ComponentFixture<CvPublicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CvPublicComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CvPublicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
