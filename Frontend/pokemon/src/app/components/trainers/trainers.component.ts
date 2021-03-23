import { Component, OnInit } from '@angular/core';
import { Trainer } from 'src/app/models/trainer';
import { TrainerService } from 'src/app/services/trainer.service';

@Component({
  selector: 'app-trainers',
  templateUrl: './trainers.component.html',
  styleUrls: ['./trainers.component.css']
})
export class TrainersComponent implements OnInit {
  trainerList: Trainer[] = [];
  // in case backend is down:
  showingTrainer: Trainer = new Trainer(0, 'Meowth', 4, 'talking', 'assets/images/meowth.gif');

  constructor(
    private trainerService: TrainerService
  ) { }

  ngOnInit(): void {
    this.trainerService.getTrainers().subscribe(data => {
      data.forEach(trainer => {
        this.trainerList.push(new Trainer(trainer.id, trainer.name, trainer.age, trainer.hobby, trainer.photo));
      });
      if(data.length != 0){
        this.showingTrainer = this.trainerList[0];
      }
    })
  }

  showTrainer(trainer: Trainer): void{
    this.showingTrainer = trainer;
  }

  openDialog():void{
    // open register component
  }
}
