package com.example.demo.dto;

import com.example.demo.model.People;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO {

    private String name;
    private String height;
    private String mass;
    private String birth_year;
    private String gender;
    private String homeworld;
    private String url;

    public static PeopleDTO parseToDTO( People people ){
        return PeopleDTO.builder()
                .name(people.getName())
                .height(people.getHeight())
                .mass(people.getMass())
                .birth_year(people.getBirth_year())
                .gender(people.getGender())
                .homeworld( people.getHomeworld())
                .url(people.getUrl()).build();
    }
}
