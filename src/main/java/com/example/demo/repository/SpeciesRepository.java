package com.example.demo.repository;

import com.example.demo.model.People;
import com.example.demo.model.ResponseWrapper;
import com.example.demo.model.Species;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;

@Repository
@RequiredArgsConstructor
public class SpeciesRepository {

    private final WebClient webClient;

    public Mono<ResponseWrapper<Species>> findAll(String page ){
        return webClient.get()
                .uri("species?page="+ page + "&format=json")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap( res -> {
                    Type type = new TypeToken<ResponseWrapper<Species>>(){}.getType();
                    ResponseWrapper<Species> speciesResData = new Gson().fromJson(res, type);
                    return Mono.just( speciesResData );
                });
    }

    public Mono<Species> findById(String id ){
        return webClient.get()
                .uri("species/"+ id)
                .retrieve()
                .bodyToMono(Species.class)
                .onErrorResume( err -> Mono.empty() );
    }

}
