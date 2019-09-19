package com.atiga.moviecataloguerl.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<MovieResultsItem> mListMovies = new ArrayList<>();
    private List<MovieGenresItem> mGenreMovies = new ArrayList<>();
    private final Activity mActivity;

    private List<MovieResultsItem> getListMovies() {
        return mListMovies;
    }

    private List<MovieGenresItem> getListGenres(){
        return mGenreMovies;
    }

    public MovieAdapter(Activity activity) {
        this.mActivity = activity;
    }

    void setMovies(List<MovieResultsItem> listMovie) {
        if (listMovie == null) return;
        this.mListMovies.clear();
        this.mListMovies.addAll(listMovie);
    }

    void setGenres(List<MovieGenresItem> listGenre){
        if(listGenre == null) return;
        this.mGenreMovies.clear();
        this.mGenreMovies.addAll(listGenre);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.txtTitle.setText(getListMovies().get(position).getTitle());
        holder.txtRating.setText(String.valueOf(getListMovies().get(position).getVoteAverage()));
        holder.mRatingBar.setProgress(Double.valueOf(getListMovies().get(position).getVoteAverage()).intValue());
        holder.txtGenre.setText(setGenreMovie(position));
        getListMovies().get(position).setGenreText(setGenreMovie(position));
        holder.itemView.setOnClickListener(v -> mActivity.startActivity(new Intent(mActivity, DetailActivity.class).putExtra(DetailActivity.EXTRA_MOVIE_ID, getListMovies().get(position).getId())));
        Glide.with(mActivity)
                .load(mActivity.getResources().getString(R.string.base_url_image) + getListMovies().get(position).getPosterPath())
                .into(holder.imgPoster);

        if (position == mListMovies.size() - 1) {
            int marginDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 16, mActivity.getResources().getDisplayMetrics());
            setMargins(holder.itemView, marginDp, marginDp, marginDp, marginDp);
        }
    }

    private String setGenreMovie(int i) {
        StringBuilder genre = new StringBuilder();
        for (int j = 0; j < getListMovies().get(i).getGenreIds().size(); j++) {
            for (int k = 0; k < getListGenres().size(); k++) {
                if (getListMovies().get(i).getGenreIds().get(j).equals(getListGenres().get(k).getId())) {
                    genre.append(getListGenres().get(k).getName());
                    if (j != getListMovies().get(i).getGenreIds().size() - 1) {
                        genre.append(", ");
                    }
                    break;
                }
            }
        }

        return genre.toString();
    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPoster;
        private final TextView txtTitle;
        private final TextView txtRating;
        private final TextView txtGenre;
        private final MaterialRatingBar mRatingBar;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.image_photo);
            txtTitle = itemView.findViewById(R.id.text_title);
            txtRating = itemView.findViewById(R.id.text_rating);
            txtGenre = itemView.findViewById(R.id.text_genre);
            mRatingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}
