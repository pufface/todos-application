import { Component } from '@angular/core';

import { AuthService } from './auth.service';
import { User } from './user';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: './app.component.html',
})
export class AppComponent  {
  name = 'Todos Application';
  user: User;

  constructor(private authService: AuthService) {
    this.authService.authChange.subscribe(user => {
      this.user = user;
    });
  }

  logout(): void {
    this.authService.logout();
  }

}
