import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PubgRoutingModule } from './pubg-routing.module';
import { TournamentThumbnailComponent } from './components/tournament-thumbnail/tournament-thumbnail.component';
import { MatchThumbnailComponent } from './components/match-thumbnail/match-thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule, MatInputModule } from '@angular/material';
import { PubgserviceService } from './pubgservice.service';
import { MatchDetailComponent } from './components/match-detail/match-detail.component';
import { FavMatchComponent } from './components/fav-match/fav-match.component';

@NgModule({
  declarations: [TournamentThumbnailComponent, MatchThumbnailComponent, MatchDetailComponent, FavMatchComponent],
  imports: [
    CommonModule,
    PubgRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    FormsModule,
    MatDialogModule,
    MatInputModule
  ],
  exports:[TournamentThumbnailComponent, MatchThumbnailComponent],
  providers:[PubgserviceService]
})
export class PubgModule { }
