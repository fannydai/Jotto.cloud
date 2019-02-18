import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  private username = '';
  private password = '';
  private errorMessage = '';

  constructor(
    private user: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  onSubmit(): void {
    this.errorMessage = '';
    this.user.register(this.username, this.password)
      .subscribe((data) => {
        if (data === null) {
          this.errorMessage = 'Error signing up';
        } else if (data.status === 'success') {
          localStorage.setItem('username', data.username);
          this.router.navigate(['/']);
        } else {
          this.errorMessage = 'Username has already been taken.';
        }
      });
  }

  checkPassword(password: string): boolean {
    if (!password || password.length < 10) {
      return false;
    }
    const hasUpper = /[A-Z]/.test(password);
    const hasLower = /[a-z]/.test(password);
    const hasDigit = /[0-9]/.test(password);
    const hasSymbol = /[!%#$&()*+,-./:;<=>?@[\]^_`{|}~]/.test(password);
    return hasUpper && hasLower && hasDigit && hasSymbol;
  }
}
