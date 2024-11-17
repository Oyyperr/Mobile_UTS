package com.example.infopariwisataapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Tampilkan splash screen selama 2 detik
        new Handler().postDelayed(() -> {
            // Siapkan data untuk dikirim
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("place_name", "Batu");
            intent.putExtra("place_description", "Kota wisata yang indah di Jawa Timur.");
            startActivity(intent);
            finish();
        }, 2000);

    }
}

