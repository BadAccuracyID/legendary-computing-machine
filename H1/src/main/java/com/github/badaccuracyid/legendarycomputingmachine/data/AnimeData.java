package com.github.badaccuracyid.legendarycomputingmachine.data;

import java.util.ArrayList;
import java.util.List;

public class AnimeData {

    private final String id;
    private String title, description;
    private int episodes;
    private String releaseDate;
    private int genreCount;
    private List<String> genres = new ArrayList<>();
    private double rating = 0.0;

    public AnimeData(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getGenreCount() {
        return genreCount;
    }

    public void setGenreCount(int genreCount) {
        this.genreCount = genreCount;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void addGenre(String genre) {
        this.genres.add(genre);
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void addRating(double rating) {
        this.rating = (this.rating + rating) / 2;
    }
}
