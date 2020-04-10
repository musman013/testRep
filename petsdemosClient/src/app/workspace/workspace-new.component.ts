import { Component, OnInit, Inject } from '@angular/core';
import { WorkspaceService } from './workspace.service';
import { IWorkspace } from './iworkspace';

import { ActivatedRoute,Router} from "@angular/router";
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { first } from 'rxjs/operators';
import { Globals, BaseNewComponent, PickerDialogService, ErrorService } from 'projects/fast-code-core/src/public_api';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { GlobalPermissionService } from '../core/global-permission.service';

@Component({
  selector: 'app-workspace-new',
  templateUrl: './workspace-new.component.html',
  styleUrls: ['./workspace-new.component.scss']
})
export class WorkspaceNewComponent extends BaseNewComponent<IWorkspace> implements OnInit {
  
    title:string = "New Workspace";
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public dialogRef: MatDialogRef<WorkspaceNewComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		public global: Globals,
		public pickerDialogService: PickerDialogService,
		public dataService: WorkspaceService,
		public errorService: ErrorService,
		public globalPermissionService: GlobalPermissionService,
	) {
		super(formBuilder, router, route, dialog, dialogRef, data, global, pickerDialogService, dataService, errorService);
	}
 
	ngOnInit() {
		this.entityName = 'Workspace';
		this.setAssociations();
		super.ngOnInit();
    this.setForm();
		this.checkPassedData();
		this.setPickerSearchListener();
  }
 		
	setForm(){
 		this.itemForm = this.formBuilder.group({
      id: ['', Validators.required],
      name: ['', Validators.required],
    });
	}
	 
	setAssociations(){
  	
		this.associations = [
		];
		this.parentAssociations = this.associations.filter(association => {
			return (!association.isParent);
		});

	}  
    
}
