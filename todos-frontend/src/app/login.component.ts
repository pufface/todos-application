import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User } from './user';
import { AuthService } from './auth.service';

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: './login.component.html'
})
export class LoginComponent {
    user = new User();
    errorMsg: string;

    constructor(private authService: AuthService,
                private router: Router){}

    login(): void {
        this.authService.login(this.user)
            .then(user => this.router.navigate(['/todos']))
            .catch(err => this.errorMsg = err)
    }

}