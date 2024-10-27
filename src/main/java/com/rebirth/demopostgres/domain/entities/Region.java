package com.rebirth.demopostgres.domain.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID regionId;

    private String regionName;

    @JsonIgnore()
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "region_pokemon_info",
            joinColumns = @JoinColumn(name = "region_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<PokemonInfo> pokemonInfoList;

}
