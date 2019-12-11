package com.codes.amr.movietrailer.data.model;


import java.io.Serializable;
import java.util.ArrayList;

public class MovieApiResponse implements Serializable {

    ArrayList<Movie> results = new ArrayList();
    private float page;
    private float total_results;
    private Dates dates;
    private float total_pages;


    // Getter Methods

    public float getPage() {
        return page;
    }

    public float getTotal_results() {
        return total_results;
    }

    public Dates getDates() {
        return dates;
    }

    public float getTotal_pages() {
        return total_pages;
    }

    // Setter Methods

    public void setPage(float page) {
        this.page = page;
    }

    public void setTotal_results(float total_results) {
        this.total_results = total_results;
    }

    public void setDates(Dates datesObject) {
        dates = datesObject;
    }

    public void setTotal_pages(float total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    static class Dates {
        private String maximum;
        private String minimum;


        // Getter Methods

        public String getMaximum() {
            return maximum;
        }

        public String getMinimum() {
            return minimum;
        }

        // Setter Methods

        public void setMaximum(String maximum) {
            this.maximum = maximum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }
    }
}

