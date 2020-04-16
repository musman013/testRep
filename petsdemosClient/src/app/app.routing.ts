
import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from "@angular/core";
import { CanDeactivateGuard } from 'projects/fast-code-core/src/public_api';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ErrorPageComponent  } from './error-page/error-page.component';
import { LoginComponent } from './login/index';
import { AuthGuard } from './core/auth-guard';
import { SchedulerRoutes } from 'projects/scheduler/src/public_api';
import { EmailRoutes } from 'projects/ip-email-builder/src/public_api';
import { EntityHistoryComponent } from "./entity-history/entity-history.component";

import { PetsListComponent, PetsDetailsComponent, PetsNewComponent } from './pets/index';
import { RolepermissionListComponent, RolepermissionDetailsComponent, RolepermissionNewComponent } from './rolepermission/index';
import { TypesListComponent, TypesDetailsComponent, TypesNewComponent } from './types/index';
import { RoleListComponent, RoleDetailsComponent, RoleNewComponent } from './role/index';
import { OwnersListComponent, OwnersDetailsComponent, OwnersNewComponent } from './owners/index';
import { PermissionListComponent, PermissionDetailsComponent, PermissionNewComponent } from './permission/index';
import { UserroleListComponent, UserroleDetailsComponent, UserroleNewComponent } from './userrole/index';
import { VisitsListComponent, VisitsDetailsComponent, VisitsNewComponent } from './visits/index';
import { SpecialtiesListComponent, SpecialtiesDetailsComponent, SpecialtiesNewComponent } from './specialties/index';
import { UserpermissionListComponent, UserpermissionDetailsComponent, UserpermissionNewComponent } from './userpermission/index';
import { VetsListComponent, VetsDetailsComponent, VetsNewComponent } from './vets/index';
import { UserListComponent, UserDetailsComponent, UserNewComponent } from './user/index';
import { VetSpecialtiesListComponent, VetSpecialtiesDetailsComponent, VetSpecialtiesNewComponent } from './vet-specialties/index';

const routes: Routes = [
	
	{ path: 'dashboard',  component: DashboardComponent ,canActivate: [ AuthGuard ]  },
	{ path: 'login', component: LoginComponent },
	{ path: 'login/:returnUrl', component: LoginComponent },
	{ path: 'pets', component: PetsListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'pets/new', component: PetsNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'pets/:id', component: PetsDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'rolepermission', component: RolepermissionListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'rolepermission/new', component: RolepermissionNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'rolepermission/:id', component: RolepermissionDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'types', component: TypesListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'types/new', component: TypesNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'types/:id', component: TypesDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'role', component: RoleListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'role/new', component: RoleNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'role/:id', component: RoleDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'owners', component: OwnersListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'owners/new', component: OwnersNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'owners/:id', component: OwnersDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'permission', component: PermissionListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'permission/new', component: PermissionNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'permission/:id', component: PermissionDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'userrole', component: UserroleListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'userrole/new', component: UserroleNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'userrole/:id', component: UserroleDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'visits', component: VisitsListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'visits/new', component: VisitsNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'visits/:id', component: VisitsDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'specialties', component: SpecialtiesListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'specialties/new', component: SpecialtiesNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'specialties/:id', component: SpecialtiesDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'userpermission', component: UserpermissionListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'userpermission/new', component: UserpermissionNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'userpermission/:id', component: UserpermissionDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'vets', component: VetsListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'vets/new', component: VetsNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'vets/:id', component: VetsDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'user', component: UserListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'user/new', component: UserNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'user/:id', component: UserDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
	{ path: 'vetspecialties', component: VetSpecialtiesListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ]},
	{ path: 'vetspecialties/new', component: VetSpecialtiesNewComponent, canActivate: [ AuthGuard ] },
	{ path: 'vetspecialties/:id', component: VetSpecialtiesDetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
  { path: 'scheduler', children: SchedulerRoutes},
  {path: 'email', children: EmailRoutes,canActivate: [ AuthGuard ] },
  { path: "entityHistory", component: EntityHistoryComponent,canActivate: [ AuthGuard ]},
  { path: '', component: HomeComponent },
  //{ path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '**', component:ErrorPageComponent},
	
];

export const routingModule: ModuleWithProviders = RouterModule.forRoot(routes);