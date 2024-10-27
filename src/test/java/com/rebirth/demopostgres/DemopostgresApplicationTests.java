package com.rebirth.demopostgres;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rebirth.demopostgres.domain.entities.PokemonInfo;
import com.rebirth.demopostgres.service.PokemonInfoService;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemopostgresApplicationTests {

    @Autowired
    private PokemonInfoService pokemonInfoService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetPikachu() {
        Optional<PokemonInfo> podriaSerPikachu =
                pokemonInfoService.getPokemonInfoById(UUID.fromString("032030ab-d33e-471c-b974-75d028f3c571"));
        Assertions.assertTrue(podriaSerPikachu.isPresent());
        Assertions.assertEquals("#25 / Pikachu", podriaSerPikachu.get().getPokemonName());
        Assertions.assertEquals("Mouse", podriaSerPikachu.get().getPokemonSpecies());
        Assertions.assertEquals(25, podriaSerPikachu.get().getPokemonNumber());
        Assertions.assertNotNull(podriaSerPikachu.get().getPokemonDescription());
        Assertions.assertTrue(podriaSerPikachu.get().getPokemonDescription().contains("Generation 1"));
    }

    @Test
    void testGetPikachuWithDefineSeparator() {
        Optional<PokemonInfo> podriaSerPikachu =
                pokemonInfoService.getPokemonInfoById(UUID.fromString("032030ab-d33e-471c-b974-75d028f3c571"), "$");
        Assertions.assertTrue(podriaSerPikachu.isPresent());
        Assertions.assertEquals("#25 $ Pikachu", podriaSerPikachu.get().getPokemonName());
        Assertions.assertEquals("Mouse", podriaSerPikachu.get().getPokemonSpecies());
        Assertions.assertEquals(25, podriaSerPikachu.get().getPokemonNumber());
        Assertions.assertNotNull(podriaSerPikachu.get().getPokemonDescription());
        Assertions.assertTrue(podriaSerPikachu.get().getPokemonDescription().contains("Generation 1"));
    }

    @Test
    void testGetBadPokemon() {
        Optional<PokemonInfo> podriaSerPikachu =
                pokemonInfoService.getPokemonInfoById(UUID.fromString("032030ab-d33e-471c-b974-75d028f3c478"));
        Assertions.assertTrue(podriaSerPikachu.isEmpty());
    }



}
