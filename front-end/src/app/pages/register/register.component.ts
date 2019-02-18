import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  private username = '';
  private password = '';
  private errorMessage = '';
  // registerForm: FormGroup;

  constructor(
    private user: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    /*
    this.registerForm = new FormGroup({
      'username': new FormControl(this.username, [
        Validators.required,
        Validators.minLength(6)
      ]),
      'password': new FormControl(this.password, [
        Validators.required,
        Validators.minLength(10),
        this.checkPassword()
      ])
    });*/
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
    console.log("hasUpper ", hasUpper);
    console.log("hasLower ", hasLower);
    console.log("hasDigit ", hasDigit);
    console.log("hasSymbol ", hasSymbol);
    return hasUpper && hasLower && hasDigit && hasSymbol;
  }
  /*
  checkPassword(): ValidatorFn {
    console.log('hello');
    return (control: AbstractControl): {[key: string]: any} | null => {
      const hasUpper = /[A-Z]/.test(control.value);
      const hasLower = /[a-z]/.test(control.value);
      const hasDigit = /[0-9]/.test(control.value);
      const hasSymbol = /[!%#$&()*+,-./:;<=>?@[\]^_`{|}~]/.test(control.value);
      return hasUpper && hasLower && hasDigit && hasSymbol ? null : {'passwordError': {value: control.value}};
    };
  }*/
}
