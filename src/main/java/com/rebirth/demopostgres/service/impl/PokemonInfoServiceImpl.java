package com.rebirth.demopostgres.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rebirth.demopostgres.domain.entities.PokemonInfo;
import com.rebirth.demopostgres.domain.entities.PokemonOnlyName;
import com.rebirth.demopostgres.domain.repository.PokemonInfoRepository;
import com.rebirth.demopostgres.service.PokemonInfoService;

@Service
public class PokemonInfoServiceImpl implements PokemonInfoService {

    private final PokemonInfoRepository pokemonInfoRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public PokemonInfoServiceImpl(final PokemonInfoRepository pokemonInfoRepository, final RestTemplate restTemplate) {
        this.pokemonInfoRepository = pokemonInfoRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<PokemonInfo> getPokemonInfoById(final UUID pokemonId, String separador) {
        return pokemonInfoRepository.findById(pokemonId)
                .map(pokemonInfo -> {
                    pokemonInfo.setPokemonName(
                            String.format("#%d %s %s", pokemonInfo.getPokemonNumber(), separador, pokemonInfo.getPokemonName()));
                    return pokemonInfo;
                });
    }

    @Override
    public List<PokemonInfo> getAllPokemonInfo() {
        return pokemonInfoRepository.findAllOptimize();
    }

    @Override
    public List<PokemonOnlyName> getAllPokemonOnlyName() {
        return pokemonInfoRepository.findAllPokemonNames();
    }

    @Override
    public PokemonInfo createPokemonInfo(final PokemonInfo pokemonInfo) {
        return pokemonInfoRepository.save(pokemonInfo);
    }

    @Override
    public PokemonOnlyName getExternalPokemonFromApi(final int number) {
        Map<String, Object> externalPokemon = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/{number}", Map.class, number);
        return new PokemonOnlyName((Integer)externalPokemon.get("id"), externalPokemon.get("name").toString());
    }
}
