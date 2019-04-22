import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TournamentThumbnailComponent } from './components/tournament-thumbnail/tournament-thumbnail.component';
import { MatchThumbnailComponent } from './components/match-thumbnail/match-thumbnail.component';
import { MatchDetailComponent } from './components/match-detail/match-detail.component';
import { AuthGuardService } from '../../authGaurd.service';
import { FavMatchComponent } from './components/fav-match/fav-match.component';


const matchRoutes: Routes = [  
  {
      path: 'tournament', 
      canActivate: [AuthGuardService],
      children: [
          {
              path: '',
              redirectTo: '/tournament/all',
              pathMatch: 'full'
          },
          {
              path: 'all',
              component: TournamentThumbnailComponent,
            
          },
          {

              path: 'match/:id',
              component: MatchThumbnailComponent,
              
          },
          {

            path: 'detail/:id',
            component: MatchDetailComponent,
            
        },
        {

            path: 'favMatch',
            component: FavMatchComponent,
            
        }

      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(matchRoutes)],
  exports: [RouterModule]
})
export class PubgRoutingModule { }
