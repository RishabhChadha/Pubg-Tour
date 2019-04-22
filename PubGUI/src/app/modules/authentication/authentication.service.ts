import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import * as jwt_decode from 'jwt-decode';

export const TOKEN_NAME:string = "jwt_token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  authServiceEndpoint:string = "http://localhost:8089/user";
  token:string;

  constructor(private http: HttpClient) { }

  setToken(token:string, userName:string) {
    localStorage.setItem("userId", userName)
    return localStorage.setItem(TOKEN_NAME, token);
  }

  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken() {
    localStorage.removeItem("userId");
    return localStorage.removeItem(TOKEN_NAME);
  }

  getTokenExpirationDate(token: string) {
    const decoded = jwt_decode(token);
    if(decoded.exp === undefined) {
      return null;
    }
    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if(!token) {
      token = this.getToken();
    }
    if(!token) {
      return true;
    }
    const date = this.getTokenExpirationDate(token);
    if(date === undefined || date === null) {
      return false;
    }
    return !(date.valueOf() > new Date().valueOf());
  }


  registerUser(newUser) {
    const url = `${this.authServiceEndpoint}/register`;
    return this.http.post(url, newUser, {responseType: 'text'});
  }

  loginUser(newUser):Observable<any> {
    const url = `${this.authServiceEndpoint}/login`;
    return this.http.post(url, newUser);
  }

}
