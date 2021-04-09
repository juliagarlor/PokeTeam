import { Component, OnInit } from '@angular/core';
import { PokemonPokedex } from 'src/app/models/pokemon-pokedex';
import { PokedexService } from 'src/app/services/pokedex.service';

@Component({
  selector: 'app-guess',
  templateUrl: './guess.component.html',
  styleUrls: ['./guess.component.css']
})
export class GuessComponent implements OnInit {
  unknownPokemon: PokemonPokedex = new PokemonPokedex(132, 'ditto', [''], '', 
  'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png', 0, 0, '');
  contrast: number = 0;
  guess: string = '';
  result: string = '';

  constructor(
    private pokedexService: PokedexService
  ) { }

  ngOnInit(): void {
    let randomId = Math.floor(Math.random() * 898) + 1;
    this.pokedexService.getPokemonEntry(randomId).subscribe(data => {
      this.unknownPokemon = new PokemonPokedex(data.id, data.name, [], '', data.sprites.other['official-artwork'].front_default, 0, 0, '');
    })
  }

  discover():void {
    this.contrast = 100;
    if(this.guess.toLowerCase() == this.unknownPokemon.name){
      this.result = `Yes! It\'s ${this.unknownPokemon.name}`;
    } else {
      this.result = `Nooooooo! It\'s ${this.unknownPokemon.name}`;
    }
  }

  newGuess():void{
    this.guess = '';
    this.result = '';
    this.contrast = 0;
    let randomId = Math.floor(Math.random() * 898) + 1;
    this.pokedexService.getPokemonEntry(randomId).subscribe(data => {
      this.unknownPokemon = new PokemonPokedex(data.id, data.name, [], '', data.sprites.other['official-artwork'].front_default, 0, 0, '');
    })
  }
}
