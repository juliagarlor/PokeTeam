import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment'

@Injectable({
  providedIn: 'root'
})
export class TrainerService {

  url = environment.baseUrl;

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
