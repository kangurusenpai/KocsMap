package com.example.kocsmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewViewHolder extends RecyclerView.Adapter<ReviewViewHolder.MyViewHolder> {

    Context context;
    ArrayList<Review> reviews;

    public ReviewViewHolder(Context context, ArrayList<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.review_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     Review review=reviews.get(position);
     holder.review.setText(review.getDescription());
     holder.location.setText(review.getAddress());
     holder.rating.setRating(review.getRating());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder {

        TextView review,location;
        RatingBar rating;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            review=itemView.findViewById(R.id.reviewText);
            location=itemView.findViewById(R.id.locationText);
            rating=itemView.findViewById(R.id.rating);


        }
    }
}
