import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PokedexComponent } from './components/pokedex/pokedex.component';
import { TeamsComponent } from './components/teams/teams.component';
import { TrainersComponent } from './components/trainers/trainers.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button'; 
import {MatCardModule} from '@angular/material/card';
import { RegisterComponent } from './components/register/register.component'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatDialogModule} from '@angular/material/dialog';
import { NewPokemonComponent } from './components/new-pokemon/new-pokemon.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { GuessComponent } from './components/guess/guess.component';

@NgModule({
  declarations: [
    AppComponent,
    PokedexComponent,
    TeamsComponent,
    TrainersComponent,
    HeaderComponent,
    HomeComponent,
    RegisterComponent,
    NewPokemonComponent,
    GuessComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatDialogModule,
    MatSlideToggleModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
