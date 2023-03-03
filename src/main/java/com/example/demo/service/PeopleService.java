package com.example.demo.service;

import com.example.demo.dto.PeopleDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.People;
import com.example.demo.model.ResponseWrapper;
import com.example.demo.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository repository;

    public Mono<ResponseWrapper<PeopleDTO>> getAll( String page ){
        return  repository.findAll(page)
                .flatMap( peopleData -> {
                    List<PeopleDTO> peopleDTOList = peopleData.getResults().stream().map( p -> PeopleDTO.parseToDTO( p )).collect(Collectors.toList());
                    ResponseWrapper<PeopleDTO> peopleDTOResponseWrapper = ResponseWrapper.<PeopleDTO>builder()
                            .count( peopleData.getCount() )
                            .next( peopleData.getNext())
                            .previous( peopleData.getPrevious())
                            .results( peopleDTOList )
                            .build();

                    return Mono.just( peopleDTOResponseWrapper );
                });
    }

    public Flux<PeopleDTO> getByManyIds( List<String> ids ){
        return Flux.fromIterable( ids )
                .flatMap( this::getById )
                .map( PeopleDTO::parseToDTO );
    }

    public Mono<People> getById(String id ){
        return repository.findById( id )
                .switchIfEmpty( Mono.error(new ResourceNotFoundException("No se encontro la persona con id: " + id)) );
    }
}
