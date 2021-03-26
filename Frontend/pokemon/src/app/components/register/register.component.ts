import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Trainer } from 'src/app/models/trainer';
import { TrainerService } from 'src/app/services/trainer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  name: FormControl;
  hobbies: FormControl;
  age: FormControl;
  profiles: string[] = ['aristocrat', 'bird_keeper', 'boy', 'girl', 'bug_catcher', 'camper', 
   'cool_trainer(female)', 'cool_trainer(male)', 'fisherman', 'hiker', 'medium',
   'model', 'picknicker', 'pokefan(female)', 'pokefan(male)', 'pokemon_breeder(female)', 
   'pokemon_breeder(male)', 'pokenerd', 'rich_girl', 'rich_boy', 'sailor', 'scientist(female)', 
   'swimmer(female)', 'swimmer(male)', 'witch'];
  photo: string = 'I am a ...';

  constructor(
    private dialogRef: MatDialogRef<RegisterComponent>,
    private trainerService: TrainerService
  ) { 
    this.name = new FormControl('', [Validators.required]);
    this.hobbies = new FormControl('', []);
    this.age = new FormControl(0, [Validators.required, Validators.min(0)]);
    this.form = new FormGroup({
      name: this.name,
      hobbies: this.hobbies,
      age: this.age
    })
  }

  ngOnInit(): void {
  }

  onSubmit(): void{
    let newTrainer = {id: 0, name: this.name.value, age: this.age.value, hobby: this.hobbies.value, photo: 'assets/images/' + this.photo + '.JPG'};
    this.trainerService.newTrainer(newTrainer).subscribe(data => {
      let output = new Trainer(data.id, newTrainer.name, newTrainer.age, newTrainer.hobby, newTrainer.photo);
      this.dialogRef.close(output);
    })
  }

  onNoClick(): void{
    this.dialogRef.close();
  }

}
