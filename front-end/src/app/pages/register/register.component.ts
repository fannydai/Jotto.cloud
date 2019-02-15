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
    this.user.register(this.username, this.userPassword)
      .subscribe((data) => {
        if (data === null) {
          this.errorMessage = 'Error signing up';
        } else if (data.success === 'success') {
          localStorage.setItem('username', data.username);
          this.router.navigate(['/']);
        } else {
          this.errorMessage = 'Username has already been taken.';
        }
      });
  }

}
