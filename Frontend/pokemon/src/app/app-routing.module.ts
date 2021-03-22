import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PokedexComponent } from './components/pokedex/pokedex.component';
import { TeamsComponent } from './components/teams/teams.component';
import { TrainersComponent } from './components/trainers/trainers.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'pokedex',
    component: PokedexComponent
  },
  {
    path: 'trainers',
    component: TrainersComponent
  },
  {
    path: 'teams',
    component: TeamsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
