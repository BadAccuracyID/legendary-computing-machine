package com.github.badaccuracyid.legendarycomputingmachine.database;

import com.github.badaccuracyid.legendarycomputingmachine.data.AnimeData;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<AnimeData> animeDataList = new ArrayList<>();

    public void addAnime(AnimeData animeData) {
        animeDataList.add(animeData);
    }

    public void removeAnime(AnimeData animeData) {
        animeDataList.remove(animeData);
    }

    public AnimeData getAnimeByID(String id) {
        for (AnimeData animeData : animeDataList) {
            if (animeData.getId().equals(id)) {
                return animeData;
            }
        }
        return null;
    }

    public List<AnimeData> getAnimeDataList() {
        return animeDataList;
    }

    public List<AnimeData> getByGenre(String genre) {
        List<AnimeData> animeDataList = new ArrayList<>();
        for (AnimeData animeData : this.animeDataList) {
            for (String genreData : animeData.getGenres()) {
                if (genreData.equalsIgnoreCase(genre)) {
                    animeDataList.add(animeData);
                    break;
                }
            }
        }

        return animeDataList;
    }

}
