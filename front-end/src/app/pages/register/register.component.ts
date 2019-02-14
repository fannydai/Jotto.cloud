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

  constructor(
    private user: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  onSubmit(): void {
    this.user.register(this.username, this.userPassword)
      .subscribe((data) => {
        // Do some checking
        this.router.navigate(['/']);
      });
  }

}
