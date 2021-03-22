import { Component, OnInit } from '@angular/core';
import { Pokemon } from 'src/app/models/pokemon';
import { PokedexService } from 'src/app/services/pokedex.service';

@Component({
  selector: 'app-pokedex',
  templateUrl: './pokedex.component.html',
  styleUrls: ['./pokedex.component.css']
})
export class PokedexComponent implements OnInit {
  entryList: Pokemon[] = [];
  showingPokemon = new Pokemon(1, 'Bulbasaur', ['grass', 'poison'], 
  'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png', 
  'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png', 
  0.7, 6.9);

  constructor(
    private pokedexService: PokedexService
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
        this.entryList.push(new Pokemon(data.id, data.name, types, data.sprites.front_default, 
          data.sprites.other['official-artwork'].front_default, data.height, data.weight));
      })
    }
  }

}
