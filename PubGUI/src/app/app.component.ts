import { Component } from '@angular/core';
import {  OnInit } from '@angular/core';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'PubGUI';
  userNameIs:any;

  constructor(private auth: AuthenticationService, private route: Router){}
  ngOnInit() {
    
    if(localStorage.getItem("userId")){
  this.userNameIs="Welcome, "+localStorage.getItem("userId")
  }
}
logOut(){
  this.auth.deleteToken();
  this.userNameIs="";
  this.route.navigate(['/login']);
}

}
