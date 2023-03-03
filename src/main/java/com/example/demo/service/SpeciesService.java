package com.example.demo.service;

import com.example.demo.dto.SpeciesDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ResponseWrapper;
import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository repository;

    public Mono<ResponseWrapper<SpeciesDTO>> getAll(String page ){
        return  repository.findAll(page)
                .flatMap( speciesData -> {
                    List<SpeciesDTO> speciesDTOList = speciesData.getResults().stream().map(s -> SpeciesDTO.parseToDTO( s )).collect(Collectors.toList());
                    ResponseWrapper<SpeciesDTO> speciesDTOResponseWrapper = ResponseWrapper.<SpeciesDTO>builder()
                            .count( speciesData.getCount() )
                            .next( speciesData.getNext())
                            .previous( speciesData.getPrevious())
                            .results( speciesDTOList )
                            .build();

                    return Mono.just( speciesDTOResponseWrapper );
                });
    }

    public Flux<SpeciesDTO> getByManyIds(List<String> ids ){
        return Flux.fromIterable( ids )
                .flatMap( this::getById )
                .map( SpeciesDTO::parseToDTO );
    }

    public Mono<Species> getById(String id ){
        return repository.findById( id )
                .switchIfEmpty( Mono.error(new ResourceNotFoundException("No se encontro la persona con id: " + id)) );
    }

}
