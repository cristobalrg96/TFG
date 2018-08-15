import { Component, OnInit } from '@angular/core';
import {MatIconModule,MatFormFieldModule} from '@angular/material';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { User } from '../_models';
import { UserService } from '../_services';
import { TvShowService } from '../_services';

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
    searchForm: FormGroup;
    currentUser: User;
    tvShowTitle = new FormControl('');

    constructor(private userService: UserService, private tvShowService: TvShowService, private formBuilder: FormBuilder, private router: Router) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        //this.loadAllUsers();
        this.searchForm = this.formBuilder.group({ tvShowTitle: ['', Validators.required] });
    }

    onSubmit() {
        //this.tvShowService.searchTvShowByTitle(this.tvShowTitle.value).pipe(first()).subscribe(
          //  data => {
                this.router.navigate(['/searchTvShow']);
           // }
        //)
    }
}
        /*this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate(['/searchtvshow']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });*/
    
    /*
    deleteUser(id: number) {
        this.userService.delete(id).pipe(first()).subscribe(() => { 
            this.loadAllUsers() 
        });
    }
    /*
    private loadAllUsers() {
        this.userService.getAll().pipe(first()).subscribe(users => { 
            this.users = users; 
        });
    }
    */

