package com.example.demo.dto;

import com.example.demo.model.Film;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private int episode_id;
    private String title;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    private String url;

    public static FilmDTO parseToDTO( Film film){
        return FilmDTO.builder()
                .episode_id( film.getEpisode_id() )
                .title( film.getTitle())
                .opening_crawl( film.getOpening_crawl() )
                .director(film.getDirector())
                .producer(film.getProducer())
                .release_date(film.getRelease_date())
                .url(film.getUrl())
                .build();
    }
}
