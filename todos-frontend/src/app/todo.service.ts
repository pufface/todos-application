import { Injectable } from "@angular/core";
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Todo } from './todo';

@Injectable()
export class TodoService {

    // private headers = new Headers({'Content-Type': 'application/json'});
    private headers = new Headers({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${btoa('user:password')}`
    });

    private baseUrl = 'http://localhost:8090';

    constructor(private http: Http) {}

    getAll(): Promise<Todo[]> {
        const url = `${this.baseUrl}/todos`;
        return this.http.get(url, { headers: this.headers})
            .toPromise()
            .then(resp => resp.json() as Todo[])
            .catch(this.handleError)
    }

    create(content: string): Promise<Todo> {
        const url = `${this.baseUrl}/todos`;
        return this.http.post(url, { content }, { headers: this.headers})
            .toPromise()
            .then(resp => resp.json() as Todo)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('Todo Service Error', error);
        return Promise.reject(error.message || error);
    }

}