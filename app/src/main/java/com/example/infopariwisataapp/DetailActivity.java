package com.example.infopariwisataapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ToggleButton buttonFavorite = findViewById(R.id.buttonFavorite);
        TextView textName = findViewById(R.id.textName);
        TextView textLocation = findViewById(R.id.textLocation);
        TextView textDescription = findViewById(R.id.textDescription);
        ImageView image = findViewById(R.id.imageLarge);
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());
        ;
        String placeName = getIntent().getStringExtra("name");

        // Tampilkan Toast
        Toast.makeText(this, "Anda membuka: " + placeName, Toast.LENGTH_SHORT).show();

        // Atau gunakan Snackbar (pilihan lain)
        Snackbar.make(findViewById(android.R.id.content), "Menampilkan detail " + placeName, Snackbar.LENGTH_SHORT).show();

// SharedPreferences untuk menyimpan favorit
        SharedPreferences preferences = getSharedPreferences("favorites", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Cek status favorit
        boolean isFavorite = preferences.getBoolean(placeName, false);
        buttonFavorite.setChecked(isFavorite);

        // Logika untuk toggle favorit
        buttonFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean(placeName, isChecked);
            editor.apply();

            String message = isChecked ? placeName + " ditambahkan ke Favorit" : placeName + " dihapus dari Favorit";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textName.setText(extras.getString("name"));
            textLocation.setText(extras.getString("location"));
            textDescription.setText(extras.getString("description"));
            image.setImageResource(extras.getInt("image"));
        }
    }

}
