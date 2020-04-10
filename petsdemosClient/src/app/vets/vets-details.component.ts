import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router} from "@angular/router";
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { first } from 'rxjs/operators';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';

import { VetsService } from './vets.service';
import { IVets } from './ivets';
import { BaseDetailsComponent, Globals, PickerDialogService, ErrorService } from 'projects/fast-code-core/src/public_api';


import { GlobalPermissionService } from '../core/global-permission.service';

@Component({
  selector: 'app-vets-details',
  templateUrl: './vets-details.component.html',
  styleUrls: ['./vets-details.component.scss']
})
export class VetsDetailsComponent extends BaseDetailsComponent<IVets> implements OnInit {
	title:string='Vets';
	parentUrl:string='vets';
	//roles: IRole[];  
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public dataService: VetsService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public globalPermissionService: GlobalPermissionService,
	) {
		super(formBuilder, router, route, dialog, global, pickerDialogService, dataService, errorService);
  }

	ngOnInit() {
		this.entityName = 'Vets';
		this.setAssociations();
		super.ngOnInit();
		this.setForm();
    this.getItem();
    this.setPickerSearchListener();
  }
  
  setForm(){
    this.itemForm = this.formBuilder.group({
      firstName: [''],
      id: [{value: '', disabled: true}, Validators.required],
      lastName: [''],
      
    });
      
  }
  
  onItemFetched(item: IVets) {
    this.item = item;
		this.itemForm.patchValue(item);
  }
  
	setAssociations(){
  	
		this.associations = [
			{
				column: [
					{
						key: 'vetId',
						value: undefined,
						referencedkey: 'specialtyId'
					},
				],
				isParent: true,
				table: 'vetSpecialties',
				type: 'OneToMany',
			},
		];
		
		this.childAssociations = this.associations.filter(association => {
			return (association.isParent);
		});

		this.parentAssociations = this.associations.filter(association => {
			return (!association.isParent);
		});
	}
}
