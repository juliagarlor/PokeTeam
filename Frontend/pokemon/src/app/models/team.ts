import { PokemonTeam } from "./pokemon-team";

export class Team {

    constructor(
        private _id: number,
        private _trainerId: number,
        private _teamMates: PokemonTeam[]
    ){}

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get trainerId(): number {
        return this._trainerId;
    }
    public set trainerId(value: number) {
        this._trainerId = value;
    }
    public get teamMates(): PokemonTeam[] {
        return this._teamMates;
    }
    public set teamMates(value: PokemonTeam[]) {
        this._teamMates = value;
    }
}
