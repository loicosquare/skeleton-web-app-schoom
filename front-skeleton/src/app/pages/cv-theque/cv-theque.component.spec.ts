import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvThequeComponent } from './cv-theque.component';

describe('CvThequeComponent', () => {
  let component: CvThequeComponent;
  let fixture: ComponentFixture<CvThequeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CvThequeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CvThequeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
