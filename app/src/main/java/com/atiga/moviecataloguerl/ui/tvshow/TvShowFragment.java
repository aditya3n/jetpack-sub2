package com.atiga.moviecataloguerl.ui.tvshow;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvShowViewModel viewModel;
    private RecyclerView rvTvShow;
    private ProgressBar mProgressBar;
    private TvShowAdapter mAdapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        mProgressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            viewModel = obtainViewModel(getActivity());
            viewModel.getTvShowGenre().observe(this, mGenreTvShow -> mAdapter.setGenres(mGenreTvShow));
            viewModel.getAllTvShows().observe(this, mListTvShow ->{
                mProgressBar.setVisibility(View.GONE);
                mAdapter.setTvShows(mListTvShow);
                mAdapter.notifyDataSetChanged();
            });

            mAdapter = new TvShowAdapter(getActivity());
            rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShow.setHasFixedSize(true);
            rvTvShow.setAdapter(mAdapter);
        }
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }
}
