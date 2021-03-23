export class Pokemon {
    
    constructor(
        private _pokedexId: number,
        private _name: string,
        private _types: string[],
        private _sprite: string,
        private _artwork: string,
        private _height: number,
        private _weight: number,
        private _description: string
        ){}
        
    public get pokedexId(): number {
        return this._pokedexId;
    }
    public set pokedexId(value: number) {
        this._pokedexId = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get types(): string[] {
        return this._types;
    }
    public set types(value: string[]) {
        this._types = value;
    }
    public get sprite(): string {
        return this._sprite;
    }
    public set sprite(value: string) {
        this._sprite = value;
    }
    public get artwork(): string {
        return this._artwork;
    }
    public set artwork(value: string) {
        this._artwork = value;
    }
    public get height(): number {
        return this._height;
    }
    public set height(value: number) {
        this._height = value;
    }
    public get weight(): number {
        return this._weight;
    }
    public set weight(value: number) {
        this._weight = value;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
}
