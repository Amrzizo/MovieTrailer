package com.codes.amr.movietrailer.data.model;

import com.codes.amr.movietrailer.di.module.ApplicationModule;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {

    public static String IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    private float popularity;
    private float vote_count;
    private boolean video;
    private String poster_path;
    private float id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    ArrayList<Integer> genre_ids = new ArrayList();
    private String title;
    private float vote_average;
    private String overview;
    private String release_date;


    // Getter Methods

    public float getPopularity() {
        return popularity;
    }

    public float getVote_count() {
        return  vote_count;
    }

    public boolean getVideo() {
        return video;
    }

    public String getPoster_path() {
        return  IMAGE_URL + poster_path;
    }

    public float getId() {
        return id;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    // Setter Methods

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setVote_count(float vote_count) {
        this.vote_count = vote_count;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public boolean isVideo() {
        return video;
    }

    public boolean isAdult() {
        return adult;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }
}
