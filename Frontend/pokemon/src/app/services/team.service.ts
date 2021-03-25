import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  url: string = 'http://localhost:8080'

  constructor(
    private httpClient: HttpClient
  ) { }

  getNamesAndIds(): Observable<any[]>{
    return this.httpClient.get<any>(this.url + '/trainer/list');
  }

  getTeamByTrainerId(id: number): Observable<IncomingTeam>{
    return this.httpClient.get<IncomingTeam>(this.url + '/team/' + id);
  }

  addNewTeamMate(teamId: number, pokedexId: number): Observable<any>{
    return this.httpClient.put<any>(this.url + '/new/team-mate/' + teamId, pokedexId);
  }

  removeTeamMate(teamId: number, pokemonId: number): Observable<any>{
    return this.httpClient.put<any>(this.url + '/remove/team-mate/' + teamId, pokemonId);
  }
}

interface IncomingTeam{
  id: number,
  trainerId: number,
  teamMates: {
    id: number,
    pokedexId: number
  }[]
}
