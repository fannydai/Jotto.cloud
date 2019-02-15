import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private username = '';
  private userPassword = '';
  private errorMessage = '';

  constructor(
    private user: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  onSubmit(): void {
    this.errorMessage = '';
    this.user.login(this.username, this.userPassword)
      .subscribe((data) => {
        if (data === null) {
          this.errorMessage = 'Error logging in';
        } else if (data.success === 'success') {
          localStorage.setItem('username', data.username);
          this.router.navigate(['/']);
        } else {
          this.errorMessage = 'Username has already been taken.';
        }
      });
  }


}
