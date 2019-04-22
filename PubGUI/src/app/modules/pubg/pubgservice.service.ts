import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators'
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';
import { ObserveOnMessage } from 'rxjs/internal/operators/observeOn';

@Injectable({
  providedIn: 'root'
})
export class PubgserviceService {

  pubgTornamentEndpoint: string;
  pubgMatchEndpoint: string;
  apiKey: string;
  springEndPoint: string;

  constructor(private http: HttpClient) {
    this.apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhOWRlY2FlMC0zMWJiLTAxMzctNWM2OC01ZDdjYjNkY2FkOGIiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTUzNTgwNTc5LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6Ii00ZDI1MGYxOS1hNWFlLTQ4MjMtOTkzZi1mYzE4Yzg2YjY0NmQifQ.QjIH50a8hHP1C0SbRl1taheXkve9d-1RYCfIFREwNQA";
    this.pubgTornamentEndpoint = "https://api.pubg.com/tournaments";
    this.springEndPoint = "http://localhost:8090/api/v1/match";
    this.pubgMatchEndpoint = "https://api.pubg.com/shards/tournament/matches/"

  }

  getTournaments() {
    const headers = new HttpHeaders();
    return this.http.get(this.pubgTornamentEndpoint, { headers: { 'Authorization': this.apiKey, 'accept': 'application/vnd.api+json' } })
  }


  getMatches(id) {
    const headers = new HttpHeaders();
    return this.http.get(this.pubgTornamentEndpoint + "/" + id, { headers: { 'Authorization': this.apiKey, 'accept': 'application/vnd.api+json' } });

  }

  getMatchDetails(id) {
    const headers = new HttpHeaders();
    return this.http.get(this.pubgMatchEndpoint + id, { headers: { 'Authorization': this.apiKey, 'accept': 'application/vnd.api+json' } })
  }

  saveFavMatch(match) {
    const headers = new HttpHeaders();
    console.log(match);

    return this.http.post(this.springEndPoint, match, { headers: { 'Authorization': "Bearer " + localStorage.getItem("jwt_token") } });
  }
  
  getMyFavMatches() {
    return this.http.get(this.springEndPoint, { headers: { 'Authorization': "Bearer " + localStorage.getItem("jwt_token") } })
  }
  
  deleteFromFavList(match){
    const url = ` ${this.springEndPoint}/${match.userId}/${match.id}`;
    return this.http.delete(url,{responseType: 'text'});
  }

  editFromFavList(match){
    const url=` ${this.springEndPoint}/12`;
    return this.http.put(url,match,{ headers: { 'Authorization': "Bearer " + localStorage.getItem("jwt_token") } })
  }
}
