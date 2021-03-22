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

  getPokemonEntry(id: number): Observable<PokemonEntry>{
    return this.httpClient.get<PokemonEntry>('https://pokeapi.co/api/v2/pokemon/' + id);
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
  }
  types: {
    type: {name: string}
  }[]
  weight:	number
}
