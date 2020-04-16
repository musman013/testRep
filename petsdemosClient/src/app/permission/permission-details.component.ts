import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router} from "@angular/router";
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { first } from 'rxjs/operators';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';

import { PickerDialogService, ErrorService, BaseDetailsComponent, Globals } from 'projects/fast-code-core/src/public_api';

import { PermissionService } from './permission.service';
import { IPermission } from './ipermission';
import { GlobalPermissionService } from '../core/global-permission.service';

@Component({
  selector: 'app-permission-details',
  templateUrl: './permission-details.component.html',
  styleUrls: ['./permission-details.component.scss']
})
export class PermissionDetailsComponent extends BaseDetailsComponent<IPermission> implements OnInit {
  title:string='Permission';
  parentUrl:string='permission';
  //roles: IRole[];  
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public dataService: PermissionService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public globalPermissionService: GlobalPermissionService,
	) {
		super(formBuilder, router, route, dialog, global, pickerDialogService, dataService, errorService);
  }

	ngOnInit() {
		this.entityName = 'Permission';
		this.setAssociations();
		super.ngOnInit();
		this.setForm();
		this.getItem();
    this.setPickerSearchListener();
	}
	
	setForm(){
    this.itemForm = this.formBuilder.group({
      displayName: ['', Validators.required],
      id: [{value: '', disabled: true}, Validators.required],
      name: ['', Validators.required],
    });
	}
  
	setAssociations(){
  	
		this.associations = [
			{
				column: [
					{
						key: 'permissionId',
						value: undefined,
						referencedkey: 'id'
					},
				],
				isParent: true,
				table: 'rolepermission',
				type: 'OneToMany',
			},
			{
				column: [
					{
						key: 'permissionId',
						value: undefined,
						referencedkey: 'id'
					},
				],
				isParent: true,
				table: 'userpermission',
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
