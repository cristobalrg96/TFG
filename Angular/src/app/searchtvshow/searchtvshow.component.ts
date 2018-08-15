import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User, TvShow } from '../_models';
import { UserService, TvShowService } from '../_services';

@Component({templateUrl: 'searchtvshow.component.html'})
export class SearchTvShowComponent implements OnInit {
    currentUser: User;
    tvShows: TvShow[] = [];
    titleQuery: string;
    //users: User[] = [];


    constructor(private userService: UserService, private tvShowService: TvShowService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        this.searchTvShows();
    }

    /*
    deleteUser(id: number) {
        this.userService.delete(id).pipe(first()).subscribe(() => { 
            this.loadAllUsers() 
        });
    }
    */    
    private searchTvShows() {
        this.tvShowService.searchTvShowByTitle("th").pipe(first()).subscribe( tvShows => {
            this.tvShows = tvShows; 
        //this.userService.getAll().pipe(first()).subscribe(users => { 
        //    this.users = users;
        });
    }

}