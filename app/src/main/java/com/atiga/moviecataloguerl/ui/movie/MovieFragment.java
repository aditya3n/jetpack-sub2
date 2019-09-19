package com.atiga.moviecataloguerl.ui.movie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.viewmodel.ViewModelFactory;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private MovieViewModel viewModel;
    private RecyclerView rvMovie;
    private ProgressBar mProgressBar;
    private MovieAdapter mAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        mProgressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            viewModel = obtainViewModel(getActivity());
            viewModel.getMovieGenre().observe(this, mGenreMovies -> mAdapter.setGenres(mGenreMovies));
            viewModel.getAllMovies().observe(this, mListMovies ->{
                mProgressBar.setVisibility(View.GONE);
                mAdapter.setMovies(mListMovies);
                mAdapter.notifyDataSetChanged();
            });

            mAdapter = new MovieAdapter(getActivity());
            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(mAdapter);
        }
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }
}
