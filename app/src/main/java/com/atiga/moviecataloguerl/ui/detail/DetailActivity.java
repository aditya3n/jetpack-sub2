package com.atiga.moviecataloguerl.ui.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;
import com.atiga.moviecataloguerl.utils.EspressoIdlingResource;
import com.atiga.moviecataloguerl.viewmodel.ViewModelFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";
    public static final String EXTRA_TV_ID = "extra_tv_id";
    private ImageView imgDetail, imgPoster, imgDark;
    private TextView txtRating, txtGenre, txtOverview, txtDate, txtOverviewTitle;
    private MaterialRatingBar mRatingBar;
    private ProgressBar mProgressBar;
    private List<MovieGenresItem> mGenreMovies = new ArrayList<>();
    private List<TvShowGenresItem> mGenreTvShow = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPoster = findViewById(R.id.image_poster);
        imgDetail = findViewById(R.id.image_detail);
        imgDark = findViewById(R.id.image_dark);
        mRatingBar = findViewById(R.id.rating_bar);
        txtRating = findViewById(R.id.text_rating);
        txtGenre = findViewById(R.id.text_genre);
        txtOverviewTitle = findViewById(R.id.text_overview_title);
        txtOverview = findViewById(R.id.text_overview);
        txtDate = findViewById(R.id.text_date);
        mProgressBar = findViewById(R.id.progressBar);
        Toolbar mToolbar = findViewById(R.id.toolbar_detail);

        DetailViewModel viewModel = obtainViewModel(this);

        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int movieId = extras.getInt(EXTRA_MOVIE_ID);
            int tvId = extras.getInt(EXTRA_TV_ID);
            if (movieId > 0) {
                Log.d("cekdetail", "onCreate: "+movieId);
                viewModel.setMovieId(movieId);
                viewModel.getMovieGenre().observe(this, genre-> mGenreMovies.addAll(genre));
                viewModel.getMovieWithId().observe(this, movie -> {
                    Log.d("cekdetail", "onCreate: movie with id");
                    EspressoIdlingResource.increment();
                    showMovieDetail(movie);
                });
            } else if (tvId > 0) {
                viewModel.setTvId(tvId);
                viewModel.getTvShowGenre().observe(this, genre -> mGenreTvShow.addAll(genre));
                viewModel.getTvShowWithId().observe(this, this::showTvShowDetail);
            }
        }

    }

    private void showMovieDetail(MovieResultsItem movie){
        Glide.with(this)
                .load(getResources().getString(R.string.base_url_image_med_res) + movie.getBackdropPath())
                .into(imgDetail);
        Glide.with(this)
                .load(getResources().getString(R.string.base_url_image_high_res) + movie.getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        EspressoIdlingResource.decrement();
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        EspressoIdlingResource.decrement();
                        mProgressBar.setVisibility(View.GONE);
                        if (getSupportActionBar() != null)
                            getSupportActionBar().setTitle(movie.getTitle());
                        txtRating.setText(String.valueOf(movie.getVoteAverage()));
                        txtRating.setVisibility(View.VISIBLE);
                        try {
                            txtDate.setText(changeDate(movie.getReleaseDate()));
                        } catch (ParseException e) {
                            txtDate.setText(movie.getReleaseDate());
                            e.printStackTrace();
                        }
                        txtGenre.setText(setGenreMovie(movie));
                        txtOverview.setText(movie.getOverview());
                        mRatingBar.setProgress((int) movie.getVoteAverage());
                        imgDetail.setVisibility(View.VISIBLE);
                        imgDark.setVisibility(View.VISIBLE);
                        mRatingBar.setVisibility(View.VISIBLE);
                        txtOverviewTitle.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(imgPoster);
    }

    private void showTvShowDetail(TvShowResultsItem tvShow) {
        Glide.with(this)
                .load(getResources().getString(R.string.base_url_image_med_res) + tvShow.getBackdropPath())
                .into(imgDetail);
        Glide.with(this)
                .load(getResources().getString(R.string.base_url_image_high_res) + tvShow.getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        if (getSupportActionBar() != null)
                            getSupportActionBar().setTitle(tvShow.getName());
                        txtRating.setText(String.valueOf(tvShow.getVoteAverage()));
                        txtRating.setVisibility(View.VISIBLE);
                        try {
                            txtDate.setText(changeDate(tvShow.getFirstAirDate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        txtGenre.setText(setGenreTvShow(tvShow));
                        txtOverview.setText(tvShow.getOverview());
                        mRatingBar.setProgress((int) tvShow.getVoteAverage());
                        imgDetail.setVisibility(View.VISIBLE);
                        imgDark.setVisibility(View.VISIBLE);
                        mRatingBar.setVisibility(View.VISIBLE);
                        txtOverviewTitle.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(imgPoster);
    }

    private String setGenreMovie(MovieResultsItem movie) {
        StringBuilder genre = new StringBuilder();
        for (int j = 0; j < movie.getGenreIds().size(); j++) {
            for (int k = 0; k < mGenreMovies.size(); k++) {
                if (movie.getGenreIds().get(j).equals(mGenreMovies.get(k).getId())) {
                    genre.append(mGenreMovies.get(k).getName());
                    if (j != movie.getGenreIds().size() - 1) {
                        genre.append(", ");
                    }
                    break;
                }
            }
        }

        return genre.toString();
    }

    private String setGenreTvShow(TvShowResultsItem tvShow) {
        StringBuilder genre = new StringBuilder();
        for (int j = 0; j < tvShow.getGenreIds().size(); j++) {
            for (int k = 0; k < mGenreTvShow.size(); k++) {
                if (tvShow.getGenreIds().get(j).equals(mGenreTvShow.get(k).getId())) {
                    genre.append(mGenreTvShow.get(k).getName());
                    if (j != tvShow.getGenreIds().size() - 1) {
                        genre.append(", ");
                    }
                    break;
                }
            }
        }

        return genre.toString();
    }

    private String changeDate(String date) throws ParseException {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date newDate = spf.parse(date);
        spf = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
        date = spf.format(Objects.requireNonNull(newDate));
        return date;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
    }
}
