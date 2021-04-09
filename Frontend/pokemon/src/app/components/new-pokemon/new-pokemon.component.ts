import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { PokemonTeam } from 'src/app/models/pokemon-team';
import { PokedexService } from 'src/app/services/pokedex.service';
import { map, startWith } from 'rxjs/operators';

@Component({
  selector: 'app-new-pokemon',
  templateUrl: './new-pokemon.component.html',
  styleUrls: ['./new-pokemon.component.css']
})
export class NewPokemonComponent implements OnInit {
  pokemonName: string = '';
  pokemonNotFound: boolean = false;

  incomingNames: Observable<string[]> | undefined;
  content: string[] = [];
  input = new FormControl();

  constructor(
    private dialogRef: MatDialogRef<NewPokemonComponent>,
    private pokedexService: PokedexService
  ) { }

  ngOnInit(): void {
    this.pokedexService.getAllNames().subscribe(data => {
      data.results.forEach(pokemon => {
        this.content.push(pokemon.name);
      })
    });

    this.incomingNames = this.input.valueChanges.pipe(
        startWith(''), map(value => this.trim(value))
      );
  }

  private trim(value: string): string[] {
    const begining = value.toLowerCase();

    return this.content.filter(option => option.toLowerCase().includes(begining));
  }

  onNoClick(): void{
    this.dialogRef.close();
  }

  searchPokemon():void{
    this.pokedexService.getPokemonEntry(this.pokemonName.toLowerCase()).subscribe(data => {

      let newPokemon = new PokemonTeam(0, data.id, this.pokemonName, data.sprites.versions['generation-v']['black-white'].animated.front_default,
      data.sprites.front_default, data.stats[0].base_stat, data.stats[1].base_stat, data.stats[2].base_stat, data.stats[3].base_stat,
      data.stats[4].base_stat, data.stats[5].base_stat, false);

      this.dialogRef.close(newPokemon);
    }, error => {
      this.pokemonNotFound = true
    })
  }

  checkKey(event: KeyboardEvent): void{
    if(event.key == 'Enter'){
      this.searchPokemon();
    }
  }
}
