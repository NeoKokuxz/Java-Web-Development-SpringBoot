package com.kokuxz.consumingdemo.entity;

import java.util.Arrays;

public class Ghibli {
    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    private String[] people;
    private String[] species;
    private String[] vehicles;
    private String url;
//    private Integer length;

    public Ghibli(){

    }

    public Ghibli(String id, String title, String description, String director, String producer, String release_date, String rt_score, String[] people, String[] species, String[] vehicles, String url, Integer length) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.producer = producer;
        this.release_date = release_date;
        this.rt_score = rt_score;
        this.people = people;
        this.species = species;
        this.vehicles = vehicles;
        this.url = url;
        //this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRt_score() {
        return rt_score;
    }

    public void setRt_score(String rt_score) {
        this.rt_score = rt_score;
    }

    public String[] getPeople() {
        return people;
    }

    public void setPeople(String[] people) {
        this.people = people;
    }

    public String[] getSpecies() {
        return species;
    }

    public void setSpecies(String[] species) {
        this.species = species;
    }

    public String[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(String[] vehicles) {
        this.vehicles = vehicles;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public Integer getLength() {
//        return length;
//    }
//
//    public void setLength(Integer length) {
//        this.length = length;
//    }

    @Override
    public String toString() {
        return "Ghibli{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", release_date='" + release_date + '\'' +
                ", rt_score='" + rt_score + '\'' +
                ", people=" + Arrays.toString(people) +
                ", species=" + Arrays.toString(species) +
                ", vehicles=" + Arrays.toString(vehicles) +
                ", url='" + url + '\'' +
 //               ", length=" + length +
                '}';
    }
}
