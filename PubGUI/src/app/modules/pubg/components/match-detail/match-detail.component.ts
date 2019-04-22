import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PubgserviceService } from '../../pubgservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'pubg-match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.css']
})
export class MatchDetailComponent implements OnInit {

  constructor(private route:Router,private router:ActivatedRoute,private service: PubgserviceService,
    private matSnackBar:MatSnackBar) { }

  match:any;
  id:any;
  savematch: any;
  ngOnInit() {

    this.id=this.router.snapshot.paramMap.get("id");
    console.log(this.id);
    this.service.getMatchDetails(this.id).subscribe((match) => {
      this.match=match['data'];
      
    });
  }
  save(match){
    this.savematch={
      "id":match.id,
      "gameMode":match.attributes.gameMode,
      "createdAt":match.attributes.createdAt,
      "mapName":match.attributes.mapName,
      "titleId":match.attributes.titleId

    }
    let message= `${this.savematch.id} added to your fav match list`;
    this.service.saveFavMatch(this.savematch).subscribe((result) => {
      console.log("in tournament thumbnail")
      this.matSnackBar.open(message, '', {
        duration:2000
    });
    console.log(this.savematch);


  })

  }
}
