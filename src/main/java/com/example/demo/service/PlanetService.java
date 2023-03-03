package com.example.demo.service;

import com.example.demo.dto.PlanetDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Planet;
import com.example.demo.model.ResponseWrapper;
import com.example.demo.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository repository;

    public Mono<ResponseWrapper<PlanetDTO>> getAll(String page ){
        return  repository.findAll(page)
                .flatMap( planetsData -> {
                    List<PlanetDTO> planetDTOList = planetsData.getResults().stream().map(p -> PlanetDTO.parseToDTO( p )).collect(Collectors.toList());
                    ResponseWrapper<PlanetDTO> peopleDTOResponseWrapper = ResponseWrapper.<PlanetDTO>builder()
                            .count( planetsData.getCount() )
                            .next( planetsData.getNext())
                            .previous( planetsData.getPrevious())
                            .results( planetDTOList )
                            .build();

                    return Mono.just( peopleDTOResponseWrapper );
                });
    }

    public Flux<PlanetDTO> getByManyIds(List<String> ids ){
        return Flux.fromIterable( ids )
                .flatMap( this::getById )
                .map( PlanetDTO::parseToDTO );
    }

    public Mono<Planet> getById(String id ){
        return repository.findById( id )
                .switchIfEmpty( Mono.error(new ResourceNotFoundException("No se encontro la persona con id: " + id)) );
    }

}
