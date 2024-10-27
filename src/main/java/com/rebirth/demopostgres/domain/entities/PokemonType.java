package com.rebirth.demopostgres.domain.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class PokemonType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID typeId;

    private String typeName;

    @JsonIgnore()
    @OneToMany(mappedBy = "pokemonType1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PokemonInfo> pokemonsInfoList;

    @JsonIgnore()
    @OneToMany(mappedBy = "pokemonType2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PokemonInfo> pokemonsInfoList2;

}
