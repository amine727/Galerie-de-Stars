package com.example.starsgallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {

    private List<Star> stars;
    private List<Star> starsFilter;
    private Context context;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
    }

    // ViewHolder
    public static class StarViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name;
        RatingBar rating;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star s = starsFilter.get(position);

        holder.name.setText(s.getName());
        holder.rating.setRating(s.getRating());

        Glide.with(context)
                .load(s.getImg())
                .into(holder.img);

        holder.itemView.setOnClickListener(v -> {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null);

            RatingBar ratingBar = view.findViewById(R.id.ratingBar);
            ratingBar.setRating(s.getRating());

            builder.setView(view);

            builder.setPositiveButton("Valider", (dialog, which) -> {
                s.setRating(ratingBar.getRating());
                notifyDataSetChanged();
            });

            builder.setNegativeButton("Annuler", null);

            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return starsFilter.size();
    }

    // 🔍 FILTRAGE
    public void filter(String text) {
        starsFilter.clear();

        if (text.isEmpty()) {
            starsFilter.addAll(stars);
        } else {
            text = text.toLowerCase();
            for (Star s : stars) {
                if (s.getName().toLowerCase().contains(text)) {
                    starsFilter.add(s);
                }
            }
        }
        notifyDataSetChanged();
    }
}