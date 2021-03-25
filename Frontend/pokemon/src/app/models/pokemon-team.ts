export class PokemonTeam {
    
    constructor(
        private _id: number,
        private _pokedexId: number,
        private _name: string,
        private _animation: string,
        private _sprite: string,
        private _hp: number,
        private _attack: number,
        private _defense: number,
        private _spAttack: number,
        private _spDeffense: number,
        private _speed: number
    ){}

    public get speed(): number {
        return this._speed;
    }
    public set speed(value: number) {
        this._speed = value;
    }
    public get spDeffense(): number {
        return this._spDeffense;
    }
    public set spDeffense(value: number) {
        this._spDeffense = value;
    }
    public get spAttack(): number {
        return this._spAttack;
    }
    public set spAttack(value: number) {
        this._spAttack = value;
    }
    public get defense(): number {
        return this._defense;
    }
    public set defense(value: number) {
        this._defense = value;
    }
    public get attack(): number {
        return this._attack;
    }
    public set attack(value: number) {
        this._attack = value;
    }
    public get hp(): number {
        return this._hp;
    }
    public set hp(value: number) {
        this._hp = value;
    }
    public get sprite(): string {
        return this._sprite;
    }
    public set sprite(value: string) {
        this._sprite = value;
    }
    public get animation(): string {
        return this._animation;
    }
    public set animation(value: string) {
        this._animation = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get pokedexId(): number {
        return this._pokedexId;
    }
    public set pokedexId(value: number) {
        this._pokedexId = value;
    }
}
