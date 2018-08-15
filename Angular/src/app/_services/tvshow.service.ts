import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { TvShow } from '../_models';

@Injectable()
export class TvShowService {
    constructor(private http: HttpClient) { }

    searchTvShowByTitle(title: string) {
        return this.http.get<TvShow[]>(`${environment.apiUrl}/tvshow?title=` + title);    
    }
    
}