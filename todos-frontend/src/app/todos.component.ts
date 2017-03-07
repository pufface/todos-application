import { Component, OnInit } from '@angular/core';

import { TodoService } from './todo.service';
import { Todo } from './todo';

@Component({
    moduleId: module.id,
    selector: 'todos',
    templateUrl: './todos.component.html'
})
export class TodosComponent implements OnInit {
   
    todos: Todo[] = [];
    newTodo = '';
    errorMsg = '';

    constructor(private todoService: TodoService) {}

    ngOnInit(): void {
        this.getAllTodos();
    }

    getAllTodos(): void {
        this.todoService.getAll()
          .then(todos => this.todos = todos)
          .catch(this.showError.bind(this));
    }

    createTodo(): void {
        this.todoService.create(this.newTodo)
            .then(todo => this.todos.push(todo))
            .then(()=>this.newTodo = "")
            .catch(this.showError.bind(this));
    }

    toggleTodo(todo: Todo): void {
        const cloneTodo = Object.assign({}, todo)
        cloneTodo.done = !cloneTodo.done;
        this.todoService.update(cloneTodo)
            .then(updatedTodo => {
                const i = this.todos.indexOf(todo);
                this.todos[i] = updatedTodo
            })
            .catch(this.showError.bind(this));
    }

    deleteTodo(todo: Todo): void {
        this.todoService.delete(todo.id)
            .then(result => {
                const i = this.todos.indexOf(todo);
                this.todos.splice(i, 1);
            })
            .catch(this.showError.bind(this));
    }

    showError(error: any): void {
        this.errorMsg = error;
    }

}