package com.codes.amr.movietrailer.ui.movielist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codes.amr.movietrailer.R;
import com.codes.amr.movietrailer.data.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends PagedListAdapter<Movie, MovieListAdapter.MovieViewHolder> {



    private MovieSelectedListener movieSelectedListener;
    private PagedList<Movie> movies;
    public Context context;

    MovieListAdapter(MovieListViewModel viewModel, LifecycleOwner lifecycleOwner, MovieSelectedListener movieSelectedListener) {
        super(DIFF_CALLBACK);
        this.movieSelectedListener = movieSelectedListener;
    }

    @Nullable
    @Override
    protected Movie getItem(int position) {
        return super.getItem(position);
    }

    public Movie getMovieAt(int position) {
        return getItem(position);
    }

    @Override
    public PagedList<Movie> getCurrentList() {
        return super.getCurrentList();
    }


    @NonNull
    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_row, parent, false);
        context = parent.getContext();
        return new MovieViewHolder(view, movieSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder holder, int position) {

        holder.bind(getItem(position));

    }


    // It determine if two list objects are the same or not
    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
            return oldMovie.getOriginal_title().equals(newMovie.getOriginal_title());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
            return oldMovie.equals(newMovie);
        }
    };

    class MovieViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.movie_title)
        TextView movieTitle;
        @BindView(R.id.movie_rating)
        TextView movieRating;
        @BindView(R.id.movie_poster)
        ImageView moviePoster;

        private Movie movie;
        public MovieViewHolder(@NonNull View itemView, MovieSelectedListener movieSelectedListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (movie != null) {
                    movieSelectedListener.onMovieSelected(movie);
                }
            });
        }

        void bind( Movie movie) {
            this.movie = movie;

            movieTitle.setText(movie.getTitle());
            movieRating.setText(String.valueOf(movie.getVote_average()));

            Glide.with(itemView.getContext())
                    .load(movie.getPoster_path())
                    .into(moviePoster);
        }


    }


}
