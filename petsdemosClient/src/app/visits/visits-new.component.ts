import { Component, OnInit, Inject } from '@angular/core';
import { VisitsService } from './visits.service';
import { IVisits } from './ivisits';

import { ActivatedRoute,Router} from "@angular/router";
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { first } from 'rxjs/operators';
import { Globals, BaseNewComponent, PickerDialogService, ErrorService } from 'projects/fast-code-core/src/public_api';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { PetsService } from '../pets/pets.service';
import { GlobalPermissionService } from '../core/global-permission.service';

@Component({
  selector: 'app-visits-new',
  templateUrl: './visits-new.component.html',
  styleUrls: ['./visits-new.component.scss']
})
export class VisitsNewComponent extends BaseNewComponent<IVisits> implements OnInit {
  
    title:string = "New Visits";
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public dialogRef: MatDialogRef<VisitsNewComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		public global: Globals,
		public pickerDialogService: PickerDialogService,
		public dataService: VisitsService,
		public errorService: ErrorService,
		public petsService: PetsService,
		public globalPermissionService: GlobalPermissionService,
	) {
		super(formBuilder, router, route, dialog, dialogRef, data, global, pickerDialogService, dataService, errorService);
	}
 
	ngOnInit() {
		this.entityName = 'Visits';
		this.setAssociations();
		super.ngOnInit();
    this.setForm();
		this.checkPassedData();
		this.setPickerSearchListener();
  }
 		
	setForm(){
 		this.itemForm = this.formBuilder.group({
      description: [''],
      visitDate: [''],
      petId: ['', Validators.required],
      petsDescriptiveField : [''],
    });
	}
	 
	setAssociations(){
  	
		this.associations = [
			{
				column: [
					{
						key: 'petId',
						value: undefined,
						referencedkey: 'id'
					},
					  
				],
				isParent: false,
				table: 'pets',
				type: 'ManyToOne',
				service: this.petsService,
				descriptiveField: 'petsDescriptiveField',
				referencedDescriptiveField: 'name',
		    
			},
		];
		this.parentAssociations = this.associations.filter(association => {
			return (!association.isParent);
		});

	}  
    
}
