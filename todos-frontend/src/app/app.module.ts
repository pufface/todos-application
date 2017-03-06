import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent }  from './app.component';
import { TodosComponent } from './todos.component';
import { LoginComponent } from './login.component';
import { TodoService } from './todo.service';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth.guard';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'todos', component: TodosComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports:      [ BrowserModule,
                  FormsModule,
                  HttpModule,
                  RouterModule.forRoot(appRoutes)],
  declarations: [ AppComponent,
                  TodosComponent,
                  LoginComponent ],
  providers:    [ TodoService, AuthService, AuthGuard ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
