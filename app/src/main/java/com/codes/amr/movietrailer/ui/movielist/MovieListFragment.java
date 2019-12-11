package com.codes.amr.movietrailer.ui.movielist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.amr.movietrailer.R;
import com.codes.amr.movietrailer.base.BaseFragment;
import com.codes.amr.movietrailer.data.model.Movie;
import com.codes.amr.movietrailer.ui.main.MainActivity;
import com.codes.amr.movietrailer.ui.movie_details.MovieDetailsFragment;
import com.codes.amr.movietrailer.ui.movie_details.MovieDetailsViewModel;
import com.codes.amr.movietrailer.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class MovieListFragment extends BaseFragment implements MovieSelectedListener {


    @BindView(R.id.movie_recyclerView)
    RecyclerView movieRecyclerView;
    @BindView(R.id.loading_error)
    TextView errorTextView;
    @BindView(R.id.loading_progress_bar)
    View loadingView;

    MovieListAdapter movieAdapter;
    MovieSearchAdapter searchAdapter;
    @Inject
    ViewModelFactory viewModelFactory;
    private MovieListViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_movie_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel.class);


        movieAdapter = new MovieListAdapter(viewModel, this, this);
        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 3 : 4));
        observableViewModel();
        ((MainActivity)getActivity()).showSearchView(true);
    }



    public void observableViewModel() {
        viewModel.moviePagedList.observe(this, movies -> {

            if (movies != null) {
                movieRecyclerView.setVisibility(View.VISIBLE);
                movieAdapter.submitList(movies);
            }

            movieRecyclerView.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    public void searchForMovie(String query){
        viewModel.searchForMovie(query);
        viewModel.getSearchMovieResponse().observe(this, movies -> {
            if (movies != null){

                searchAdapter = new MovieSearchAdapter(movies.getResults(), MovieListFragment.this);
                movieRecyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
                }



        });

    }

    @Override
    public void onMovieSelected(Movie movie) {
            MovieDetailsViewModel movieDetailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(MovieDetailsViewModel.class);
            movieDetailsViewModel.setselectedMovie(movie);
            getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.screenContainer, new MovieDetailsFragment())
                    .addToBackStack(null).commit();

    }

    public void closeSearch(){
        if (searchAdapter.getItemCount()>0) {
            searchAdapter.clear();
            observableViewModel();
        }
    }
}
