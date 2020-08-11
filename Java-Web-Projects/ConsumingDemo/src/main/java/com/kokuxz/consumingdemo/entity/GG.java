package com.kokuxz.consumingdemo.entity;

public class GG {
    private String id;
    private String title;
    private String description;
    private String director;

    public GG() {
    }

    public GG(String id, String title, String description, String director) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
    }

    @Override
    public String toString() {
        return "GG{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                '}';
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
}
