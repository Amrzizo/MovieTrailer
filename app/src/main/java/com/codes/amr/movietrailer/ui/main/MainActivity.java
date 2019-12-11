package com.codes.amr.movietrailer.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.codes.amr.movietrailer.R;
import com.codes.amr.movietrailer.base.BaseActivity;
import com.codes.amr.movietrailer.ui.movielist.MovieListFragment;

public class MainActivity extends BaseActivity {
    private MovieListFragment movieListFragment;
    private MenuItem searchViewItem;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            movieListFragment = new MovieListFragment();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, movieListFragment).commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

         searchViewItem = menu.findItem(R.id.search);

        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_for_movies));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Search(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String txt) {

                Search(txt);

                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                movieListFragment.closeSearch();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void Search(String txt) {
        movieListFragment.searchForMovie(txt);
    }

    public void showSearchView(boolean hide){

        if(searchViewItem!= null)
            searchViewItem.setVisible(hide);


    }
}
