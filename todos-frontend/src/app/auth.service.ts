import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Headers, Http, RequestOptions, Response } from '@angular/http';

import { User } from './user';
import { Subject } from "rxjs/Subject";

@Injectable()
export class AuthService {

    private baseUrl = 'http://localhost:8090';
    private user: User;
    private options: RequestOptions;
    //  = new RequestOptions({
    //     headers: new Headers({
    //         'Authorization': `Basic ${btoa('user:password')}`
    //     })
    // })
    authChange: Subject<User> = new Subject<User>();

    constructor(private http: Http,
                private router: Router) {}

    login(user: User): Promise<User> {
        const url = `${this.baseUrl}/userinfo`;
        const headers = new Headers({
            'Authorization': `Basic ${btoa(user.username + ':' + user.password)}`
        });
        const options = new RequestOptions({headers})
        return this.http.get(url, options)
            .toPromise()
            .then(_ => {
                this.user = user;
                this.options = options;
                this.authChange.next(user);
                return user;
            })
            .catch(this.handleError)
    }

    logout(): void {
        this.router.navigate(['/login']);
        // this.user = null;
        // this.options = null;
        // this.authChange.next(null);
    }

    isAuthenticated(): boolean {
        return !!this.user;
    }

    getUser(): User {
        return this.user;
    }

    get(urlSuffix: string): Promise<Response> {
        const url = this.baseUrl + urlSuffix;
        return this.http.get(url, this.options)
            .toPromise()
            .catch(this.handleError);
    }

    post(urlSuffix: string, body: any): Promise<Response> {
        const url = this.baseUrl + urlSuffix;
        return this.http.post(url, body, this.options)
            .toPromise()
            .catch(this.handleError);
    }

    put(urlSuffix: string, body: any): Promise<Response> {
        const url = this.baseUrl + urlSuffix;
        return this.http.put(url, body, this.options)
            .toPromise()
            .catch(this.handleError);
    }

    delete(urlSuffix: string): Promise<Response> {
        const url = this.baseUrl + urlSuffix;
        return this.http.delete(url, this.options)
            .toPromise()
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('Http Service Error', error);
        return Promise.reject(error.text() || error);
    }

}