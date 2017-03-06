import { Injectable } from "@angular/core";
import { Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Todo } from './todo';
import { AuthService } from './auth.service';

@Injectable()
export class TodoService {

    constructor(private http: AuthService) {}

    getAll(): Promise<Todo[]> {
        return this.http.get('/todos')
            .then(resp => resp.json() as Todo[])
            .catch(this.handleError)
    }

    create(content: string): Promise<Todo> {
        return this.http.post('/todos', { content })
            .then(resp => resp.json() as Todo)
            .catch(this.handleError);
    }

    update(todo: Todo): Promise<Todo> {
        return this.http.post('/todos', todo)
            .then(resp => resp.json() as Todo)
            .catch(this.handleError);
    }

    delete(id: number): Promise<boolean> {
        return this.http.delete(`/todos/${id}`)
            .then(resp => true)
            .catch(this.handleError);
    }

    private handleError(error: any): void {
        alert(error.text() || alert)
    }

}