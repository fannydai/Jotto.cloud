import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {
  private loginError = '';
  private loginName = '';
  private loginPassword = '';

  private signupError = '';
  private signupName = '';
  private signupPassword = '';
  private signupConfirm = '';

  constructor(
    private user: UserService
  ) { }

  ngOnInit() {
  }

  onLogin(): void {
    this.loginError = '';
    console.log('LOGGING IN');
    console.log('Username:', this.loginName);
    console.log('Password:', this.loginPassword);
    this.user.login(this.loginName, this.loginPassword)
      .subscribe(data => {
        if (data === null) {
          this.loginError = 'Server is down.';
        } else if (data.status === 'success') {
          this.user.setLoggedIn(data.username);
        } else {
          this.loginError = 'Incorrect username and/or password.';
        }
      });
  }

  onSignUp(): void {
    console.log('SIGNING UP');
    console.log('Username:', this.signupName);
    console.log('Password:', this.signupPassword);
    console.log('Confirm:', this.signupConfirm);
    this.signupError = '';
    if (this.signupPassword !== this.signupConfirm) {
      this.signupError = 'Password and Confirm Password must match';
    } else {
      this.user.register(this.signupName, this.signupPassword)
        .subscribe(data => {
          if (data == null) {
            this.signupError = 'Server is down.';
          } else if (data.status === 'success') {
            this.user.setLoggedIn(data.username);
          } else {
            this.loginError = 'Username has been taken.';
          }
        });
    }
  }

  onLogout(): void {
    console.log('LOGGING OUT');
    this.user.logout();
  }
}
