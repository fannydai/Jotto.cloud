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
    this.user.login(this.loginName, this.loginPassword)
      .subscribe(data => {
        if (data === null) {
          this.loginError = 'Server is down.';
        } else if (data.status === 'success') {
          this.loginName = '';
          this.loginPassword = '';
          this.user.setLoggedIn(data.username);
        } else {
          this.loginError = 'Incorrect username and/or password.';
        }
      });
  }

  onSignUp(): void {
    this.signupError = '';
    if (this.signupPassword !== this.signupConfirm) {
      this.signupError = 'Password must match Confirm.';
    } else {
      this.user.register(this.signupName, this.signupPassword)
        .subscribe(data => {
          if (data == null) {
            this.signupError = 'Server is down.';
          } else if (data.status === 'success') {
            this.signupName = '';
            this.signupPassword = '';
            this.signupConfirm = '';
            this.user.setLoggedIn(data.username);
          } else {
            this.signupError = 'Username has been taken.';
          }
        });
    }
  }

  onLogout(): void {
    this.user.logout();
  }
}
