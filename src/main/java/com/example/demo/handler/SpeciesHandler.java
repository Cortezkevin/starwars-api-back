package com.example.demo.handler;

import com.example.demo.dto.SpeciesDTO;
import com.example.demo.model.ResponseWrapper;
import com.example.demo.service.SpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SpeciesHandler {

    private final SpeciesService service;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest){
        String page = serverRequest.queryParam("page").orElse("1");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.getAll( page ), ResponseWrapper.class);
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.getById( id ), SpeciesDTO.class);
    }

    public Mono<ServerResponse> getByManyIds(ServerRequest serverRequest){
        String ids = serverRequest.pathVariable("ids");
        List<String> idList = Arrays.stream( ids.split(",") ).collect(Collectors.toList());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body( service.getByManyIds(idList) , SpeciesDTO.class );
    }

}
