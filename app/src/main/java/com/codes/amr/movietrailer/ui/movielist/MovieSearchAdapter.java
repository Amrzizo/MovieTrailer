package com.codes.amr.movietrailer.ui.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codes.amr.movietrailer.R;
import com.codes.amr.movietrailer.data.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.SearchViewHolder> {

    private Context mContext;
    private List<Movie> movieList;


    private MovieSelectedListener movieSelectedListener;

    public MovieSearchAdapter(List<Movie> movieList, MovieSelectedListener movieSelectedListener) {
        this.movieList = movieList;
        this.movieSelectedListener = movieSelectedListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_row, parent, false);
        return new SearchViewHolder(view, movieSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bind(movieList.get(position));

    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.size();
    }

    public void clear() {
        int size = movieList.size();
        movieList.clear();
        notifyItemRangeRemoved(0, size);
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_title)
        TextView movieTitle;
        @BindView(R.id.movie_rating)
        TextView movieRating;
        @BindView(R.id.movie_poster)
        ImageView moviePoster;

        private Movie movie;

        public SearchViewHolder(@NonNull View itemView, MovieSelectedListener movieSelectedListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (movie != null) {
                    movieSelectedListener.onMovieSelected(movie);
                }
            });
        }

        void bind(Movie movie) {
            this.movie = movie;

            movieTitle.setText(movie.getTitle());
            movieRating.setText(String.valueOf(movie.getVote_average()));

            Glide.with(itemView.getContext())
                    .load(movie.getPoster_path())
                    .into(moviePoster);
        }

    }


}
