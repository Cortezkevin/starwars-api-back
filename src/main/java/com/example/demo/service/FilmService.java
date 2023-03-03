package com.example.demo.service;

import com.example.demo.dto.FilmDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Film;
import com.example.demo.model.ResponseWrapper;
import com.example.demo.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository repository;

    public Mono<ResponseWrapper<FilmDTO>> getAll(String page ){
        return  repository.findAll(page)
                .flatMap( filmsData -> {
                    List<FilmDTO> filmDTOList = filmsData.getResults().stream().map(f -> FilmDTO.parseToDTO( f )).collect(Collectors.toList());
                    ResponseWrapper<FilmDTO> filmDTOResponseWrapper = ResponseWrapper.<FilmDTO>builder()
                            .count( filmsData.getCount() )
                            .next( filmsData.getNext())
                            .previous( filmsData.getPrevious())
                            .results( filmDTOList )
                            .build();

                    return Mono.just( filmDTOResponseWrapper );
                });
    }

    public Flux<FilmDTO> getByManyIds(List<String> ids ){
        return Flux.fromIterable( ids )
                .flatMap( this::getById )
                .map( FilmDTO::parseToDTO );
    }

    public Mono<Film> getById(String id ){
        return repository.findById( id )
                .switchIfEmpty( Mono.error( new ResourceNotFoundException("No se encontro la pelicula con id: " + id )));
    }

}
