import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { PokemonTeam } from 'src/app/models/pokemon-team';
import { PokedexService } from 'src/app/services/pokedex.service';

@Component({
  selector: 'app-new-pokemon',
  templateUrl: './new-pokemon.component.html',
  styleUrls: ['./new-pokemon.component.css']
})
export class NewPokemonComponent implements OnInit {
  pokemonName: string = '';
  pokemonFound: boolean = true;

  constructor(
    private dialogRef: MatDialogRef<NewPokemonComponent>,
    private pokedexService: PokedexService
  ) { }

  ngOnInit(): void {
  }

  onNoClick(): void{
    this.dialogRef.close();
  }

  searchPokemon():void{
    this.pokedexService.getPokemonEntry(this.pokemonName).subscribe(data => {
      console.log(data);
      let newPokemon = new PokemonTeam(0, data.id, this.pokemonName, data.sprites.versions['generation-v']['black-white'].animated.front_default,
      data.sprites.front_default, data.stats[0].base_stat, data.stats[1].base_stat, data.stats[2].base_stat, data.stats[3].base_stat,
      data.stats[4].base_stat, data.stats[5].base_stat);
      this.dialogRef.close(newPokemon);
    }, error => {this.pokemonFound = false})
  }
}
