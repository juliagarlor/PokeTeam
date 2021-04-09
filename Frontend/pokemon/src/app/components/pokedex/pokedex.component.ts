import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PokemonPokedex } from 'src/app/models/pokemon-pokedex';
import { PokedexService } from 'src/app/services/pokedex.service';
import { NewPokemonComponent } from '../new-pokemon/new-pokemon.component';

@Component({
  selector: 'app-pokedex',
  templateUrl: './pokedex.component.html',
  styleUrls: ['./pokedex.component.css']
})
export class PokedexComponent implements OnInit {
  entryList: PokemonPokedex[] = [];
  showingPokemon = new PokemonPokedex(1, 'Bulbasaur', ['grass', 'poison'], 
  'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png', 
  'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png', 
  0.7, 6.9, 'A strange seed was\nplanted on its\nback at birth. The plant sprouts\nand grows with\nthis POKéMON.');

  constructor(
    private pokedexService: PokedexService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.getTenEntries(1);
  }

  getTenEntries(first: number){
    // cleaning entryList
    this.entryList = []
    // pushing pokemons into list
    for(let i = first; i < first + 11; i++){
      this.pokedexService.getPokemonEntry(i).subscribe(data => {
        let types: string[] = [];
        data.types.forEach(type => {
          types.push(type.type.name)
        });
        this.entryList.push(new PokemonPokedex(data.id, data.name, types, data.sprites.front_default, 
          data.sprites.other['official-artwork'].front_default, data.height/10, data.weight/10, ''));
        this.entryList.sort((a, b) => (a.pokedexId < b.pokedexId ? -1 : 1));
      })
    }
  }

  showPokemon(entry: PokemonPokedex){
    this.pokedexService.getPokemonDescription(entry.pokedexId).subscribe(data => {
      entry.description = data.flavor_text_entries[1].flavor_text.replace('\u000c', ' ');

      this.showingPokemon = entry;
    })
  }

  openDialog(): void{
    const dialogRef = this.dialog.open(NewPokemonComponent, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(data => {
      if(data != undefined){
        this.pokedexService.getPokemonEntry(data.pokedexId).subscribe(result => {
          let types: string[] = [];
          result.types.forEach(type => {
            types.push(type.type.name)
          });
          this.showingPokemon = new PokemonPokedex(data.pokedexId, data.name, types, result.sprites.front_default,
            result.sprites.other['official-artwork'].front_default, result.height/10, result.weight/10, '');

          this.pokedexService.getPokemonDescription(data.pokedexId).subscribe(description => {
            this.showingPokemon.description = description.flavor_text_entries[1].flavor_text.replace('\u000c', ' ');
          })

          this.getTenEntries(data.pokedexId - 5);
        });
      }
    })
  }
}
