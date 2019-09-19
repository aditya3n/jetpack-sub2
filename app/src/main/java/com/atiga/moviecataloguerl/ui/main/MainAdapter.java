package com.atiga.moviecataloguerl.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.ui.tvshow.TvShowFragment;
import com.atiga.moviecataloguerl.ui.movie.MovieFragment;

class MainAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.title_movie, R.string.title_tv};
    private final Context mContext;

    public MainAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MovieFragment();
            case 1:
                return new TvShowFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
