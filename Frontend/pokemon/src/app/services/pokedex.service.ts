import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PokedexService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getPokemonEntry(id: number | string): Observable<PokemonEntry>{
    return this.httpClient.get<PokemonEntry>('https://pokeapi.co/api/v2/pokemon/' + id);
  }

  getPokemonDescription(id: number): Observable<PokemonSpecies>{
    return this.httpClient.get<PokemonSpecies>('https://pokeapi.co/api/v2/pokemon-species/' + id + '/');
  }
}

interface PokemonEntry{
  height:	number,
  id:	number,
  name:	string,
  sprites:	{
    front_default:	string,
    other: {
      'official-artwork': {
        front_default: string
      }
    }
    versions: {
      'generation-v': {
        'black-white': {
          'animated': {
            front_default: string
          }
        }
      }
    }
  }
  stats:{
    base_stat: number
  }[]
  types: {
    type: {name: string}
  }[]
  weight:	number
}

interface PokemonSpecies{
  flavor_text_entries: {
    '1': {
      flavor_text: string
    }
  }
}
