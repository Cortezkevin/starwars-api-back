package com.example.demo.repository;

import com.example.demo.model.Film;
import com.example.demo.model.ResponseWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;

@Repository
@RequiredArgsConstructor
public class FilmRepository {

    private final WebClient webClient;

    public Mono<ResponseWrapper<Film>> findAll(String page ){
        return webClient.get()
                .uri("films?page="+ page + "&format=json")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap( res -> {
                    Type type = new TypeToken<ResponseWrapper<Film>>(){}.getType();
                    ResponseWrapper<Film> filmResData = new Gson().fromJson(res, type);
                    return Mono.just( filmResData );
                });
    }

    public Mono<Film> findById(String id ){
        return webClient.get()
                .uri("films/"+ id)
                .retrieve()
                .bodyToMono(Film.class)
                .onErrorResume( err -> Mono.empty() );
    }

}
