import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';

@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        return this.http.get<any>(`${environment.apiUrl}/login?username=`+ username+`&password=`+password)
            .pipe(map(user => {
                localStorage.setItem('currentUser', JSON.stringify(user));
                return user;
            }));
    }
    logout() {
        localStorage.removeItem('currentUser');
    }
}