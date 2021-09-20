package com.example.tp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivityPokeStats"; // Identifiant pour les messages de log
    private EditText editTextPokemonName;


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
        Intent intent;
        intent = new Intent(this, InfoPokemon.class);
        intent.putExtra("inputpokemonname", (Parcelable) editTextPokemonName);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        String value = editTextPokemonName.toString();
        outState.putString("editTextPokemonName", value);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        String PokeName;
        if(savedInstanceState.containsKey("editTextPokemonName"))
            PokeName = savedInstanceState.getString("editTextPokemonName");
        else
            PokeName = "";
    }
}