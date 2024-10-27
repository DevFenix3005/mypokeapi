package com.rebirth.demopostgres.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.rebirth.demopostgres.domain.entities.PokemonInfo;
import com.rebirth.demopostgres.domain.entities.PokemonOnlyName;

public interface PokemonInfoService {

    Optional<PokemonInfo> getPokemonInfoById(final UUID pokemonId, final String separador);

    default Optional<PokemonInfo> getPokemonInfoById(final UUID pokemonId) {
        return getPokemonInfoById(pokemonId, "/");
    }

    List<PokemonInfo> getAllPokemonInfo();

    List<PokemonOnlyName> getAllPokemonOnlyName();

    PokemonInfo createPokemonInfo(final PokemonInfo pokemonInfo);

    PokemonOnlyName getExternalPokemonFromApi(final int number);

}
