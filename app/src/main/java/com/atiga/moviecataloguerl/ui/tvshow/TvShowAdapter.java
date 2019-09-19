package com.atiga.moviecataloguerl.ui.tvshow;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;
import com.atiga.moviecataloguerl.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private final List<TvShowResultsItem> mListTvShows = new ArrayList<>();
    private List<TvShowGenresItem> mGenreTvShow = new ArrayList<>();
    private final Activity mActivity;

    private List<TvShowResultsItem> getListTvShows(){
        return mListTvShows;
    }

    private List<TvShowGenresItem> getListGenres(){
        return mGenreTvShow;
    }

    public TvShowAdapter(Activity mActivity){
        this.mActivity=mActivity;
    }

    void setTvShows(List<TvShowResultsItem> listTvShow) {
        if (listTvShow == null) return;
        this.mListTvShows.clear();
        this.mListTvShows.addAll(listTvShow);
    }

    void setGenres(List<TvShowGenresItem> listGenre){
        if(listGenre == null) return;
        this.mGenreTvShow.clear();
        this.mGenreTvShow.addAll(listGenre);
        Log.d("cekgenre", "setGenres: "+listGenre.size());
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.txtTitle.setText(getListTvShows().get(position).getName());
        holder.txtRating.setText(String.valueOf(getListTvShows().get(position).getVoteAverage()));
        holder.mRatingBar.setProgress(Double.valueOf(getListTvShows().get(position).getVoteAverage()).intValue());
        holder.txtGenre.setText(setGenreTv(position));
        getListTvShows().get(position).setGenreText(setGenreTv(position));
        holder.itemView.setOnClickListener(v -> mActivity.startActivity(new Intent(mActivity, DetailActivity.class).putExtra(DetailActivity.EXTRA_TV_ID, getListTvShows().get(position).getId())));
        Glide.with(mActivity)
                .load(mActivity.getResources().getString(R.string.base_url_image) + getListTvShows().get(position).getPosterPath())
                .into(holder.imgPoster);

        if (position == getListTvShows().size() - 1) {
            int marginDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 16, mActivity.getResources().getDisplayMetrics());
            setMargins(holder.itemView, marginDp, marginDp, marginDp, marginDp);
        }
    }

    private String setGenreTv(int i) {
        StringBuilder genre = new StringBuilder();
        for (int j = 0; j < getListTvShows().get(i).getGenreIds().size(); j++) {
            for (int k = 0; k < getListGenres().size(); k++) {
                if (getListTvShows().get(i).getGenreIds().get(j).equals(getListGenres().get(k).getId())) {
                    genre.append(getListGenres().get(k).getName());
                    if (j != getListTvShows().get(i).getGenreIds().size() - 1) {
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
        return getListTvShows().size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPoster;
        private final TextView txtTitle;
        private final TextView txtRating;
        private final TextView txtGenre;
        private final MaterialRatingBar mRatingBar;
        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.image_photo);
            txtTitle = itemView.findViewById(R.id.text_title);
            txtRating = itemView.findViewById(R.id.text_rating);
            txtGenre = itemView.findViewById(R.id.text_genre);
            mRatingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}
