import { PokemonPokedex } from './pokemon-pokedex';

describe('Pokemon', () => {
  it('should create an instance', () => {
    expect(new PokemonPokedex(0, '', [], '', '', 0, 0, '')).toBeTruthy();
  });
});
