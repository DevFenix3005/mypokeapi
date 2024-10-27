package com.rebirth.demopostgres.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PokemonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID pokemonId;

    @Column(unique = true, nullable = false)
    private int pokemonNumber;

    @Column(unique = true, nullable = false, length = 250)
    private String pokemonName;

    @Column(unique = true, nullable = false, length = 250)
    private String pokemonSpecies;

    @Column(unique = true, nullable = false, length = 250)
    private String pokemonDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private PokemonType pokemonType1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_2_id")
    private PokemonType pokemonType2;

    @ManyToMany(mappedBy = "pokemonInfoList")
    private List<Region> regionList;

}
