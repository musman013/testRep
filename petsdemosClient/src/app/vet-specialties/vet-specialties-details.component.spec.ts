import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from "@angular/platform-browser";
import { of } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { Location } from '@angular/common';

import { TestingModule, EntryComponents } from '../../testing/utils';
import { IVetSpecialties, VetSpecialtiesService, VetSpecialtiesDetailsComponent, VetSpecialtiesListComponent } from './index';

describe('VetSpecialtiesDetailsComponent', () => {
  let component: VetSpecialtiesDetailsComponent;
  let fixture: ComponentFixture<VetSpecialtiesDetailsComponent>;
  let el: HTMLElement;
    
  let data:IVetSpecialties = {
    specialtyId: 1,
    vetId: 1,
    specialtiesDescriptiveField: 'name1',
    vetsDescriptiveField: 'firstName1',
  };
  
  describe('Unit Tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          VetSpecialtiesDetailsComponent       
        ],
        imports: [TestingModule],
        providers: [
          VetSpecialtiesService,
        ],
        schemas: [NO_ERRORS_SCHEMA]  
      }).compileComponents();
    }));
  
    beforeEach(() => {
      fixture = TestBed.createComponent(VetSpecialtiesDetailsComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should run #ngOnInit()', async () => {
      spyOn(component.dataService, "getById").and.returnValue(of(data));
      component.ngOnInit();

      expect(component.item).toEqual(data);
      expect(component.itemForm.getRawValue()).toEqual(data);
      component.itemForm.enable();
      expect(component.itemForm.valid).toEqual(true);
      expect(component.title.length).toBeGreaterThan(0);
      expect(component.associations).toBeDefined();
      expect(component.childAssociations).toBeDefined();
      expect(component.parentAssociations).toBeDefined();
    });

    it('should run #onSubmit()', async () => {
      component.item = data;
      component.itemForm.patchValue(data);
      component.itemForm.enable();
      fixture.detectChanges();

      spyOn(component, "onSubmit").and.returnValue();
      el = fixture.debugElement.query(By.css('button[name=save]')).nativeElement;
      el.click();

      expect(component.onSubmit).toHaveBeenCalled();
    });

    it('should call the back', async () => {
      component.item = data;
      fixture.detectChanges();

      spyOn(component, "onBack").and.returnValue();
      el = fixture.debugElement.query(By.css('button[name=back]')).nativeElement;
      el.click();
      
      expect(component.onBack).toHaveBeenCalled();
    });

  });
  
  describe('Integration Tests', () => {
    beforeEach(async(() => {

      TestBed.configureTestingModule({
        declarations: [
          VetSpecialtiesDetailsComponent,
          VetSpecialtiesListComponent
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'vetspecialties', component: VetSpecialtiesDetailsComponent },
            { path: 'vetspecialties/:id', component: VetSpecialtiesListComponent }
          ])
        ],
        providers: [
          VetSpecialtiesService
        ]

      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(VetSpecialtiesDetailsComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should run #ngOnInit()', async () => {
      spyOn(component.dataService, "getById").and.returnValue(of(data));

      component.ngOnInit();

      expect(component.item).toEqual(data);
      expect(component.itemForm.getRawValue()).toEqual(data);
      component.itemForm.enable();
      expect(component.itemForm.valid).toEqual(true);
      expect(component.title.length).toBeGreaterThan(0);
      expect(component.associations).toBeDefined();
      expect(component.childAssociations).toBeDefined();
      expect(component.parentAssociations).toBeDefined();
    });

    it('should update the record and redirect to list component', async () => {
      const router = TestBed.get(Router);
      const location = TestBed.get(Location);
      let navigationSpy = spyOn(router, 'navigate').and.callThrough();

      component.item = data;
      component.itemForm.patchValue(data);
      component.itemForm.enable();
      fixture.detectChanges();

      spyOn(component.dataService, 'update').and.returnValue(of(data));
      el = fixture.debugElement.query(By.css('button[name=save]')).nativeElement;
      el.click();

      let responsePromise = navigationSpy.calls.mostRecent().returnValue;
      await responsePromise;
      expect(location.path()).toBe('/vetspecialties');

    });

    it('should go back to list component when back button is clicked', async () => {
      const router = TestBed.get(Router);
      const location = TestBed.get(Location);
      let navigationSpy = spyOn(router, 'navigate').and.callThrough();

      component.item = data;
      fixture.detectChanges();
      el = fixture.debugElement.query(By.css('button[name=back]')).nativeElement;
      el.click();

      let responsePromise = navigationSpy.calls.mostRecent().returnValue;
      await responsePromise;
      expect(location.path()).toBe('/vetspecialties');

    });

  });
  
});
