import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PubgserviceService } from '../../pubgservice.service';

@Component({
  selector: 'pubg-match-thumbnail',
  templateUrl: './match-thumbnail.component.html',
  styleUrls: ['./match-thumbnail.component.css']
})
export class MatchThumbnailComponent implements OnInit {

  constructor(private route:Router,private router:ActivatedRoute,private service: PubgserviceService) { }

  id:any;
  matches:any;
  ngOnInit() {
    this.id=this.router.snapshot.paramMap.get("id");
    console.log(this.id);
    this.service.getMatches(this.id).subscribe((matches) => {
      this.matches=matches['included'];
      console.log(this.matches);
    });

  }
  details(id){
    console.log(id)
    this.route.navigate(['tournament/detail',id])
  }

}
