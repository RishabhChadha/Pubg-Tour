import { Component, OnInit } from '@angular/core';
import { PubgserviceService } from '../../pubgservice.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'pubg-fav-match',
  templateUrl: './fav-match.component.html',
  styleUrls: ['./fav-match.component.css']
})
export class FavMatchComponent implements OnInit {

  constructor(private pubgservice: PubgserviceService,private matSnackBar:MatSnackBar) { }
matches:any;
  ngOnInit() {
    this.pubgservice.getMyFavMatches().subscribe((matches) => {
      this.matches=matches;
    
      console.log(this.matches)
      console.log("in fav match thumbnail")
    });

  }
  
  delete(match){
    console.log("114")
    let message= `${match.id} delete from your watch list`;
   /*  for(var i=0;i<this.movies.length;i++){
      if(this.movies[i].title === movie.title){
        this.movies.splice(i,1);
      }
    } */
    this.pubgservice.deleteFromFavList(match).subscribe((match) =>{
      this.matSnackBar.open(message, '', {
        duration:1000
      });
    });

    const index = this.matches.indexOf(match);
      this.matches.splice(index,1);
  }

  edit(match){
    let message= `${match.id} editted`;
    this.pubgservice.editFromFavList(match).subscribe((match) =>{
      this.matSnackBar.open(message, '', {
        duration:1000
      });
    });
  }

}
