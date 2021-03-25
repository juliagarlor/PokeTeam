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
  profiles: string[] = ['aristocrata.JPG', 'campista.JPG', 'cazabichos.JPG', 'chica_criapokemon.JPG',
   'chico_criapokemon.JPG', 'chica.JPG', 'joven.JPG', 'cientifica.JPG', 'cientifico.JPG', 'dominguero.JPG', 
   'entrenador_guay.JPG', 'entrenadora_guay.JPG', 'hombre_pokefan.JPG', 'mujer_pokefan.JPG', 'marinero.JPG', 
   'medium.JPG', 'modelo.JPG', 'montañero.JPG', 'nadador.JPG', 'nadadora.JPG', 'niña_bien.JPG', 'niño_bien.JPG', 
   'ornitologo.JPG', 'pescador.JPG', 'pokemaniaco.JPG'];
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
    let newTrainer = {id: 0, name: this.name.value, age: this.age.value, hobby: this.hobbies.value, photo: 'assets/images/' + this.photo};
    this.trainerService.newTrainer(newTrainer).subscribe(data => {
      let output = new Trainer(data.id, newTrainer.name, newTrainer.age, newTrainer.hobby, newTrainer.photo);
      this.dialogRef.close(output);
    })
  }

  onNoClick(): void{
    this.dialogRef.close();
  }

}
