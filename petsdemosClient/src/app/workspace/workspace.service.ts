
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IWorkspace } from './iworkspace';
import { GenericApiService } from '../../../projects/fast-code-core/src/public_api';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WorkspaceService extends GenericApiService<IWorkspace> { 
  constructor(private httpclient: HttpClient) { 
    super(httpclient, { apiUrl: environment.apiUrl }, "workspace");
  }
  
  
}
