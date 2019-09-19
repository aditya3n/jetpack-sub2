package com.atiga.moviecataloguerl.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.atiga.moviecataloguerl.di.Injection;
import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.ui.detail.DetailViewModel;
import com.atiga.moviecataloguerl.ui.movie.MovieViewModel;
import com.atiga.moviecataloguerl.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MainRepository mRepository;

    private ViewModelFactory(MainRepository repository) {
        mRepository = repository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(mRepository);
        }
        else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            //noinspection unchecked
            return (T) new TvShowViewModel(mRepository);
        }
        else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailViewModel(mRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
