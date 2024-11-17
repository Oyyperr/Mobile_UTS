package com.example.infopariwisataapp;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.VideoView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TouristPlace> places;
    private List<TouristPlace> filteredPlaces; // Untuk menampilkan hasil pencarian
    private TouristPlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi VideoView
        VideoView videoView = findViewById(R.id.videoView);

        // Set video dari file lokal di folder res/raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kota_batu);
        videoView.setVideoURI(videoUri);

        // Mulai video otomatis dengan looping
        videoView.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
            videoView.start();
        });
        // Tambahkan MediaController ke VideoView
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);




        // Inisialisasi tempat wisata dan filteredPlaces
        places = new ArrayList<>();
        filteredPlaces = new ArrayList<>();
        populateData();
        filteredPlaces.addAll(places); // Awalnya tampilkan semua tempat wisata

        // Set Adapter RecyclerView
        adapter = new TouristPlaceAdapter(this, filteredPlaces);
        recyclerView.setAdapter(adapter);

        // Inisialisasi SearchView
        SearchView searchView = findViewById(R.id.searchView);

        // Filter tempat wisata berdasarkan nama
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        // Akses elemen teks dalam SearchView
        int textViewId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = searchView.findViewById(textViewId);

        // Ubah warna teks hint dan teks biasa
        searchText.setHintTextColor(ContextCompat.getColor(this, R.color.green_700)); // Warna hijau
        searchText.setTextColor(ContextCompat.getColor(this, R.color.black)); // Warna teks biasa
    }

    // Data Tempat Wisata
    private void populateData() {
        places.add(new TouristPlace("Jatim Park 1", "Batu, Jawa Timur",
                "Jatim Park 1 adalah destinasi wisata edukasi dan hiburan yang menawarkan berbagai wahana seru, mulai dari science center yang edukatif, kolam renang dengan tema unik, hingga atraksi interaktif seperti Volcano Coaster dan Sky Ride. Tempat ini cocok untuk liburan keluarga dengan pengalaman belajar yang menyenangkan.", R.drawable.jtp1));

        places.add(new TouristPlace("Jatim Park 2", "Batu, Jawa Timur",
                "Jatim Park 2 menghadirkan Batu Secret Zoo, kebun binatang modern dengan koleksi hewan dari berbagai belahan dunia, serta Museum Satwa yang menyimpan replika hewan langka dengan tampilan yang memukau. Selain itu, Eco Green Park di dalamnya menyajikan konsep taman hijau yang mendidik tentang kelestarian lingkungan.", R.drawable.jtp2));

        places.add(new TouristPlace("Jatim Park 3", "Batu, Jawa Timur",
                "Jatim Park 3 adalah pusat hiburan futuristik yang memanjakan pengunjung dengan Dino Park, taman dinosaurus yang realistis dan edukatif. Selain itu, ada Galeri Musik Dunia yang menyajikan koleksi alat musik dari seluruh dunia, serta The Legend Stars Park dengan replika landmark dan tokoh dunia terkenal.", R.drawable.jtp3));

        places.add(new TouristPlace("Museum Angkut", "Batu, Jawa Timur",
                "Museum Angkut merupakan museum transportasi terbesar di Asia Tenggara. Tempat ini menampilkan koleksi kendaraan klasik dari berbagai negara, mulai dari mobil antik, pesawat, hingga kendaraan tradisional. Setiap zona tematiknya membawa pengunjung menjelajahi budaya transportasi dari berbagai belahan dunia.", R.drawable.museum_angkut));

        places.add(new TouristPlace("Batu Night Spectacular", "Batu, Jawa Timur",
                "Batu Night Spectacular (BNS) adalah destinasi wisata malam yang memadukan keindahan lampion garden dengan beragam wahana hiburan seperti roller coaster dan taman bermain. Di sini juga terdapat pasar malam yang menjual berbagai kuliner dan suvenir unik. Tempat ini sempurna untuk menikmati suasana malam Batu yang meriah.", R.drawable.bns));

        places.add(new TouristPlace("Eco Green Park", "Batu, Jawa Timur",
                "Eco Green Park menawarkan pengalaman belajar tentang lingkungan dan ekosistem melalui berbagai atraksi seperti rumah terbalik, taman burung, dan wahana edukasi tentang pengelolaan limbah. Tempat ini cocok untuk keluarga yang ingin mengajarkan anak-anak pentingnya menjaga alam sambil bermain.", R.drawable.eco_green));

        places.add(new TouristPlace("Batu Love Garden", "Batu, Jawa Timur",
                "Batu Love Garden atau Baloga adalah taman bunga modern dengan koleksi tanaman yang beragam. Selain pemandangan bunga yang memukau, taman ini juga memiliki area edukasi dan spot foto yang sangat instagramable. Baloga menjadi pilihan tepat untuk menikmati keindahan alam yang memanjakan mata.", R.drawable.baloga));

        places.add(new TouristPlace("Selecta", "Batu, Jawa Timur",
                "Selecta adalah taman wisata yang telah menjadi ikon Batu sejak lama. Tempat ini memiliki taman bunga yang indah, kolam renang dengan air yang jernih, dan berbagai fasilitas rekreasi seperti wahana perahu dayung. Udara segar pegunungan semakin melengkapi pengalaman liburan di sini.", R.drawable.selecta));

        places.add(new TouristPlace("Alun-Alun Kota Batu", "Batu, Jawa Timur",
                "Alun-Alun Kota Batu adalah pusat kota yang menawarkan suasana santai dengan bianglala besar sebagai ikon utamanya. Di sini, pengunjung dapat menikmati jajanan khas seperti ketan dan susu segar, serta bersantai di taman bermain yang ramah anak. Tempat ini menjadi favorit bagi wisatawan lokal dan luar kota.", R.drawable.alun_alun));
    }

    // Filter Tempat Wisata
    private void filterList(String query) {
        filteredPlaces.clear();
        for (TouristPlace place : places) {
            if (place.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredPlaces.add(place);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
