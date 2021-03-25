import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrainerService {

  url = 'http://localhost:8080'

  constructor(
    private httpClient: HttpClient
  ) { }

  getTrainers(): Observable<IncomingTrainer[]>{
    return this.httpClient.get<IncomingTrainer[]>(this.url + '/trainers/details');
  }

  newTrainer(newTrainer: IncomingTrainer): Observable<IncomingTrainer>{
    return this.httpClient.post<IncomingTrainer>(this.url + '/new/trainer', newTrainer);
  }

  removeTrainer(id: number): Observable<any>{
    return this.httpClient.delete<any>(this.url + '/remove/trainer/' + id);
  }
}

interface IncomingTrainer{
  id: number,
  name: string,
  age: number,
  hobby: string,
  photo: string
}
