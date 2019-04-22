import { Component, OnInit } from '@angular/core';

import { AuthenticationService } from '../../authentication.service';
import { User } from '../../User';
import { Router } from '@angular/router';
import { AppComponent } from '../../../../app.component';
import { MatSnackBarModule, MatSnackBar } from '@angular/material';
import { error } from '@angular/compiler/src/util';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'authentication-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User=new User();
 message:any;
  constructor(private appComponent:AppComponent,private authService: AuthenticationService, private router: Router,private matSnackBar:MatSnackBar) { }

  ngOnInit() {

  }

  loginUser() {
    console.log("Login user", this.user);
    this.message="username or password incorrect";
    this.authService.loginUser(this.user).subscribe((data) => {
      console.log("Login successful");
      
      console.log(data['token']);
      if(data['token']) {
        this.authService.setToken(data['token'],this.user.userId);
        this.appComponent.ngOnInit();
        
        this.router.navigate(['/tournament']);
      }},
      (error:HttpErrorResponse)=>{
        this.matSnackBar.open(this.message, '', {
          duration:1000
        });
      })
    
  }

}
