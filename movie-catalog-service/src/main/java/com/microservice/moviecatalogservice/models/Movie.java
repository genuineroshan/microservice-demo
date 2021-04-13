package com.microservice.moviecatalogservice.models;

public class Movie {
    private String movieId;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;
    private String name;

    public Movie(String movieId, String movieDesc, String name) {
        this.movieId = movieId;
        this.desc = movieDesc;
        this.name = name;
    }

    public Movie() {
    }


    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
