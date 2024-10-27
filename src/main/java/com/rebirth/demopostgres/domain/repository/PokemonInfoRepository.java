package com.rebirth.demopostgres.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rebirth.demopostgres.domain.entities.PokemonInfo;
import com.rebirth.demopostgres.domain.entities.PokemonOnlyName;

public interface PokemonInfoRepository extends JpaRepository<PokemonInfo, UUID> {

    @Query("SELECT pi0 FROM PokemonInfo pi0 JOIN FETCH pi0.pokemonType1 LEFT JOIN FETCH pi0.pokemonType2 JOIN FETCH pi0.regionList")
    List<PokemonInfo> findAllOptimize();

    @Query("SELECT new com.rebirth.demopostgres.domain.entities.PokemonOnlyName(pi0.pokemonNumber, pi0.pokemonName) FROM PokemonInfo pi0")
    List<PokemonOnlyName> findAllPokemonNames();

    @Modifying
    @Query("UPDATE PokemonInfo pi0 SET pi0.pokemonNumber = ?1, pi0.pokemonName = ?2 WHERE pi0.pokemonId = ?3")
    void updatePokemonInfo(int pokemonNumber, String pokemonName, UUID pokemonId);

}
