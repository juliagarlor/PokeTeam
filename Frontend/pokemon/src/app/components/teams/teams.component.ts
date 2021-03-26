import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PokemonPokedex } from 'src/app/models/pokemon-pokedex';
import { PokemonTeam } from 'src/app/models/pokemon-team';
import { Team } from 'src/app/models/team';
import { PokedexService } from 'src/app/services/pokedex.service';
import { TeamService } from 'src/app/services/team.service';
import { NewPokemonComponent } from '../new-pokemon/new-pokemon.component';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {
  teamList: {id: number, name: string}[] = [];
  selectedTeam: Team = new Team(0, 0, [new PokemonTeam(0, 52, 'meowth', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/52.gif',
  'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png', 40, 45, 35, 40, 40, 90, false)]);

  constructor(
    private teamService: TeamService,
    private pokedexService: PokedexService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.teamService.getNamesAndIds().subscribe(data => {
      data.forEach(team => {
        this.teamList.push({id: team[0], name: team[1]});
      });
    })
  }

  showTeam(trainer: {id: number, name: string}): void{

    this.teamService.getTeamByTrainerId(trainer.id).subscribe(incomingTeam => {
      
      this.selectedTeam = new Team(incomingTeam.id, incomingTeam.trainerId, []);
      incomingTeam.teamMates.forEach(teamMate => {
  // stats: 0:hp, 1:attack, 2:defense, 3:spAttack, 4:spDefense, 5:speed        
        this.pokedexService.getPokemonEntry(teamMate.pokedexId).subscribe(pokemon => {
          this.selectedTeam.teamMates.push(new PokemonTeam(teamMate.id, pokemon.id, pokemon.name, pokemon.sprites.versions['generation-v']['black-white'].animated.front_default,
          pokemon.sprites.front_default, pokemon.stats[0].base_stat, pokemon.stats[1].base_stat, pokemon.stats[2].base_stat, pokemon.stats[3].base_stat, 
          pokemon.stats[4].base_stat, pokemon.stats[5].base_stat, false))
        })
      });
    })
  }

  showOrHideStats(pokemon: PokemonTeam): void{
    pokemon.showingStats = !pokemon.showingStats;
  }

  removePokemon(pokemon: PokemonTeam, index: number): void{
    this.teamService.removeTeamMate(this.selectedTeam.id, pokemon.id).subscribe(data => {
      this.selectedTeam.teamMates.splice(index, 1);
    })
  }

  openDialog(){
    const dialogRef = this.dialog.open(NewPokemonComponent, {
      width: '600px'
    });

    dialogRef.afterClosed().subscribe(data => {
      if(data != undefined){
        this.teamService.addNewTeamMate(this.selectedTeam.id, data.pokedexId).subscribe(result => {
                  this.selectedTeam.teamMates.push(data);
        })
      }
    })
  }
}
