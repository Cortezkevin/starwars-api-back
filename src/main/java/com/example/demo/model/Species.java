package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Species {
    public String name;
    public String classification;
    public String designation;
    public String average_height;
    public String skin_colors;
    public String hair_colors;
    public String eye_colors;
    public String average_lifespan;
    public String homeworld;
    public String language;
    public List<String> people;
    public List<String> films;
    public String created;
    public String edited;
    public String url;
}
