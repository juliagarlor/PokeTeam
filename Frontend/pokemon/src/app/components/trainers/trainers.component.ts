import { Component, OnInit } from '@angular/core';
import { Trainer } from 'src/app/models/trainer';
import { TrainerService } from 'src/app/services/trainer.service';
import { RegisterComponent } from '../register/register.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-trainers',
  templateUrl: './trainers.component.html',
  styleUrls: ['./trainers.component.css']
})
export class TrainersComponent implements OnInit {
  trainerList: Trainer[] = [];
  // in case backend is down:
  showingTrainer: Trainer = new Trainer(0, 'Meowth', 4, 'talking', 'assets/images/meowth.png');

  constructor(
    private trainerService: TrainerService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.trainerService.getTrainers().subscribe(data => {
      data.forEach(trainer => {
        this.trainerList.push(new Trainer(trainer.id, trainer.name, trainer.age, trainer.hobby, trainer.photo));
      });
      if(data.length != 0) this.showingTrainer = this.trainerList[0];
    })
  }

  showTrainer(trainer: Trainer): void{
    this.showingTrainer = trainer;
  }

  openDialog():void{
    const dialogRef = this.dialog.open(RegisterComponent, {
      width: '600px'
    });

    dialogRef.afterClosed().subscribe(data => {
      if(data != undefined){
        this.trainerList.push(data);
        this.showingTrainer = this.trainerList[0];
      }
    })
  }

  removeTrainer(trainer: Trainer, index: number): void{
    this.trainerService.removeTrainer(trainer.id).subscribe(data => {
      this.trainerList.splice(index, 1);
      if(this.trainerList.length == 0){
        this.showingTrainer = new Trainer(0, 'Meowth', 4, 'talking', 'assets/images/meowth.png');
      }else{
        this.showingTrainer = this.trainerList[0];
      }
    })
  }
}
