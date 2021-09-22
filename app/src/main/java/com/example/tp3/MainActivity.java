package com.example.tp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static final String APP_TAG = "PokeStats";
    public static String TAG = "MainActivityPokeStats"; // Identifiant pour les messages de log
    private EditText editTextPokemonName;
    String url = "https://www.pokepedia.fr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPokemonName = findViewById(R.id.editTextPokemonName);
    }

    public void OnClick_exit(android.view.View v){
        finish();
    }

    public void OnClick_search(android.view.View v){
        //Log.d("MainActivity", editTextPokemonName.getText().toString());
        Intent intent;
        intent = new Intent(this, PokeRequest.class);
        //url = url+editTextPokemonName.getText().toString();
        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setData(Uri.parse(url));
        intent.putExtra("inputpokemonname", editTextPokemonName.getText().toString());
        startActivity(intent);
    }

}