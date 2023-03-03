package com.example.demo.dto;

import com.example.demo.model.People;
import com.example.demo.model.Species;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesDTO {

    public String name;
    public String classification;
    public String designation;
    public String average_height;
    public String average_lifespan;
    public String url;

    public static SpeciesDTO parseToDTO( Species species ){
        return SpeciesDTO.builder()
                .name(species.getName())
                .classification(species.getClassification())
                .designation(species.getDesignation())
                .average_height(species.getAverage_height())
                .average_lifespan(species.getAverage_lifespan())
                .url(species.getUrl())
                .build();
    }
}
