package com.atiga.moviecataloguerl.di;

import android.app.Application;

import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.data.NetworkRepository;

public class Injection {
    public static MainRepository provideRepository(Application application) {

        NetworkRepository remoteRepository = NetworkRepository.getInstance();

        return MainRepository.getInstance(remoteRepository);
    }
}
