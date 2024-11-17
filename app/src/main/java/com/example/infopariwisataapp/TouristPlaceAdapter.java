package com.example.infopariwisataapp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TouristPlaceAdapter extends RecyclerView.Adapter<TouristPlaceAdapter.ViewHolder> {
    private Context context;
    private List<TouristPlace> places;

    public TouristPlaceAdapter(Context context, List<TouristPlace> places) {
        this.context = context;
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tourist_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TouristPlace place = places.get(position);
        holder.textName.setText(place.getName());
        holder.textLocation.setText(place.getLocation());
        holder.imageThumbnail.setImageResource(place.getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("name", place.getName());
            intent.putExtra("location", place.getLocation());
            intent.putExtra("description", place.getDescription());
            intent.putExtra("image", place.getImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textLocation;
        ImageView imageThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textLocation = itemView.findViewById(R.id.textLocation);
            imageThumbnail = itemView.findViewById(R.id.imageThumbnail);
        }
    }
}

