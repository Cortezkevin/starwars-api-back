package com.example.demo.dto;

import com.example.demo.model.People;
import com.example.demo.model.Planet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDTO {

    public String name;
    public String rotation_period;
    public String orbital_period;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    public String url;

    public static PlanetDTO parseToDTO( Planet planet ){
        return PlanetDTO.builder()
                .name(planet.getName())
                .rotation_period(planet.getRotation_period())
                .orbital_period(planet.getOrbital_period())
                .diameter(planet.getDiameter())
                .climate(planet.getClimate())
                .gravity(planet.getGravity())
                .terrain(planet.getTerrain())
                .url(planet.getUrl()).build();
    }
}
