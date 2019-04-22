import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PubgserviceService } from '../../pubgservice.service';

@Component({
  selector: 'pubg-tournament-thumbnail',
  templateUrl: './tournament-thumbnail.component.html',
  styleUrls: ['./tournament-thumbnail.component.css']
})
export class TournamentThumbnailComponent implements OnInit {

  constructor(private route:Router,private service: PubgserviceService) { }

  tournaments:any;
  match:any;
  ngOnInit() {
    
    this.service.getTournaments().subscribe((tournaments) => {
      this.tournaments=tournaments['data'];
    
      console.log(this.tournaments)
      console.log("in tournament thumbnail")
    });

  }

  Add(id){
    console.log(id)
    this.route.navigate(['tournament/match',id])
  }

}

