package com.rebirth.demopostgres.web.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rebirth.demopostgres.domain.entities.PokemonInfo;
import com.rebirth.demopostgres.domain.entities.PokemonOnlyName;
import com.rebirth.demopostgres.service.PokemonInfoService;

@RestController
@RequestMapping("/api/v0/pokemon-info")
public class PokemonInfoController {

    private final PokemonInfoService pokemonInfoService;

    @Autowired
    public PokemonInfoController(final PokemonInfoService pokemonInfoService) {
        this.pokemonInfoService = pokemonInfoService;
    }

    @GetMapping()
    public ResponseEntity<List<PokemonInfo>> getAllPokemonInfo() {
        return ResponseEntity.ok(pokemonInfoService.getAllPokemonInfo());
    }

    @GetMapping("/onlyNames")
    public ResponseEntity<List<PokemonOnlyName>> getAllPokemonOnlyName() {

        return ResponseEntity.ok(pokemonInfoService.getAllPokemonOnlyName());
    }

    @GetMapping("/{pokemonId}")
    public ResponseEntity<PokemonInfo> getPokemonInfoById(@PathVariable UUID pokemonId) {
        return ResponseEntity.of(pokemonInfoService.getPokemonInfoById(pokemonId));
    }

    @PostMapping()
    public ResponseEntity<PokemonInfo> postPokemonInfo(@RequestBody PokemonInfo pokemonInfo) {
        PokemonInfo newPokemonInfo = pokemonInfoService.createPokemonInfo(pokemonInfo);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(newPokemonInfo.getPokemonId());
        return ResponseEntity.created(uri).body(newPokemonInfo);
    }

}
