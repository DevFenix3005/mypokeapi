package com.rebirth.demopostgres;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.h2.util.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.rebirth.demopostgres.domain.entities.PokemonOnlyName;
import com.rebirth.demopostgres.service.PokemonInfoService;

@SpringBootTest
public class ExternalTests {

    @Autowired
    private PokemonInfoService pokemonInfoService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    public void init() {
        try {
            ClassPathResource pikachuData = new ClassPathResource("pikachudata.json");
            String pikachuJson = new String(pikachuData.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            ClassPathResource raichuData = new ClassPathResource("raichudata.json");
            String raichuJson = new String(raichuData.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
            mockRestServiceServer.expect(requestTo("https://pokeapi.co/api/v2/pokemon/25"))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(pikachuJson, MediaType.APPLICATION_JSON));
            mockRestServiceServer.expect(requestTo("https://pokeapi.co/api/v2/pokemon/26"))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(raichuJson, MediaType.APPLICATION_JSON));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getRemotePokemonFromApi() {
        PokemonOnlyName pokemon25 = pokemonInfoService.getExternalPokemonFromApi(25);
        Assertions.assertEquals("pikachu", pokemon25.getPokemonName());

        PokemonOnlyName pokemon26 = pokemonInfoService.getExternalPokemonFromApi(26);
        Assertions.assertEquals("raichu", pokemon26.getPokemonName());
    }

}
