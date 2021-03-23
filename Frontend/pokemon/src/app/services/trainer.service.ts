import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrainerService {

  url = 'localhost:8080'

  constructor(
    private httpClient: HttpClient
  ) { }

  getTrainers(): Observable<IncomingTrainer[]>{
    return this.httpClient.get<IncomingTrainer[]>(this.url + '/trainers/details');
  }
}

interface IncomingTrainer{
  id: number,
  name: string,
  age: number,
  hobby: string,
  photo: string
}
