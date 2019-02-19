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
        } else if (data.status === 'success') {
          this.user.setLoggedIn(data.username);
          this.router.navigate(['/game']);
        } else {
          this.errorMessage = 'Incorrect username and/or password.';
        }
      });
  }


}
