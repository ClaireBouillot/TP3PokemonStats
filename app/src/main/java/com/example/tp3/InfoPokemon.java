package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class InfoPokemon extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);
        String editTextPokemonName = getIntent().getStringExtra("inputpokemonname");
        TextView InfoPoke = findViewById(R.id.info);
        InfoPoke.setText(editTextPokemonName);
    }

    public void buttonGoBack(View view) {
        onBackPressed();
    }
}