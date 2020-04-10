import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

import { IWorkspace } from './iworkspace';
import { WorkspaceService } from './workspace.service';
import { Router, ActivatedRoute } from '@angular/router';
import { WorkspaceNewComponent } from './workspace-new.component';
import { BaseListComponent, Globals, IListColumn, listColumnType, PickerDialogService, ErrorService } from 'projects/fast-code-core/src/public_api';

import { GlobalPermissionService } from '../core/global-permission.service';

@Component({
  selector: 'app-workspace-list',
  templateUrl: './workspace-list.component.html',
  styleUrls: ['./workspace-list.component.scss']
})
export class WorkspaceListComponent extends BaseListComponent<IWorkspace> implements OnInit {

	title:string = "Workspace";
	constructor(
		public router: Router,
		public route: ActivatedRoute,
		public global: Globals,
		public dialog: MatDialog,
		public changeDetectorRefs: ChangeDetectorRef,
		public pickerDialogService: PickerDialogService,
		public dataService: WorkspaceService,
		public errorService: ErrorService,
		public globalPermissionService: GlobalPermissionService,
	) { 
		super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, dataService, errorService)
  }

	ngOnInit() {
		this.entityName = 'Workspace';
		this.setAssociation();
		this.setColumns();
		this.primaryKeys = [ "id",  ]
		super.ngOnInit();
	}
  
  
	setAssociation(){
  	
		this.associations = [
		];
	}
  
  	setColumns(){
  		this.columns = [
    		{
				column: 'id',
				label: 'id',
				sort: false,
				filter: false,
				type: listColumnType.Number
			},
    		{
				column: 'name',
				label: 'name',
				sort: true,
				filter: true,
				type: listColumnType.String
			},
		  	{
				column: 'actions',
				label: 'Actions',
				sort: false,
				filter: false,
				type: listColumnType.String
			}
		];
		this.selectedColumns = this.columns;
		this.displayedColumns = this.columns.map((obj) => { return obj.column });
  	}
	addNew() {
		super.addNew(WorkspaceNewComponent);
	}
  
}
