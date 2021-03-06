package com.example.tp3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

class PokeRequest extends AsyncTask<Void,Integer,Void> {
    private String name;
    private String restype;
    private String resname;
    private String resweight;
    private String ressize;


    public PokeRequest(String name) {
        this.name = name;
        restype = "<none>";
        resname = "<none>";
        resweight = "<none>";
        ressize = "<none>";

    }

    @Override
    protected Void doInBackground(Void... voids) { // Se fait en background du thread UI
        try {
            Document doc = Jsoup.connect("https://www.pokepedia.fr/" + name).get();
            Element tableinfo = doc.selectFirst("table.tableaustandard");

            Elements names = tableinfo.select("th.entĂȘtesection");
            for (Element e : names) {
                resname = e.ownText();
                Log.v(MainActivity.APP_TAG,"Entete section: " + resname);
            }

            Log.v(MainActivity.APP_TAG,"=====>>>>>  FINAL Entete section: " + resname);

            Elements rows = tableinfo.select("tr");
            for (Element row : rows) {
                Log.v(MainActivity.APP_TAG,"=====>>>>>  new line. ");
                if(row.select("a[title*=taille]").size() > 0) {
                    Element target = row.selectFirst("td");
                    if(target != null) {
                        ressize = target.ownText();
                        Log.v(MainActivity.APP_TAG,"=====>>>>>  Find a size: " + ressize);
                    }
                    else
                        Toast.makeText(InfoPokemon.this,R.string.error_no_dom_entity, Toast.LENGTH_LONG).show();
                }

                if(row.select("a[title*=poids]").size() > 0) {
                    Element target = row.selectFirst("td");
                    if(target != null) {
                        resweight = target.ownText();
                        Log.v(MainActivity.APP_TAG,"=====>>>>>  Find a weight: " + resweight);
                    }
                    else
                        Toast.makeText(InfoPokemon.this,R.string.error_no_dom_entity, Toast.LENGTH_LONG).show();
                }

            }


            Elements elems = tableinfo.select("a[title*=type]");
            ArrayList<String> types = new ArrayList<>();
            for (Element e: elems) {
                if(!e.attr("title").equalsIgnoreCase("Type")) {
                    String rawtype = e.attr("title");
                    String type = rawtype.replace(" (type)","");
                    types.add(type);
                    Log.v(MainActivity.APP_TAG,"\tFind type: " +type);
                }
            }
            restype = types.stream().collect(Collectors.joining(" - "));
        } catch (IOException e) {
            Log.e(MainActivity.APP_TAG,"Error during connection...",e);
            // e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // Inutile ici, cf doc
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) { // S'exĂ©cute sur le ThreadUI aprĂšs doInBackground
        super.onPostExecute(aVoid);
        // ATTENTION, il faut adapter le code ci-dessous avec vos controles graphiques.
        InfoPokemon.this.pokemon_name.setText(resname);
        InfoPokemon.this.pokemon_type.setText(restype);
        InfoPokemon.this.pokemon_size.setText(ressize);
        InfoPokemon.this.pokemon_weight.setText(resweight);
        Toast.makeText(InfoPokemon.this, R.string.end_request, Toast.LENGTH_SHORT).show();

        // c'est ici que vous devrez ajouter l'Ă©criture de votre fichier en FIN de sujet!!!
    }
}
