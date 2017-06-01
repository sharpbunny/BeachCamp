package bd.fr.beachcamp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class CreateMulti extends AppCompatActivity {

    private MapView MapViewCreateMulti;
    double lat;
    double lng;
    String titreMulti =  "";
    String descriptionMulti = "";
    int personnesMulti = 0;
    String stringOfDate;
    String stringOfTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.AccessToken));
        setContentView(R.layout.activity_create_multi);

        MapViewCreateMulti = (MapView)findViewById(R.id.MapViewCreateMulti);
        MapViewCreateMulti.onCreate(savedInstanceState);

        MapViewCreateMulti.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(final MapboxMap mapboxMap){
                //Marker on Palavas-les-flots
                mapboxMap.addMarker(new MarkerViewOptions()
                        .position(new LatLng(43.5333, 3.9333))
                        .title("Palavas-les-flots")
                        .snippet("34250 Palavas-les-flots"));

                // Marker on Pérols-plage
                mapboxMap.addMarker(new MarkerViewOptions()
                        .position(new LatLng(43.5667, 3.95))
                        .title("Pérols-plage")
                        .snippet("34470 Pérols-plage"));

                // Marker on Carnon-Plage
                mapboxMap.addMarker(new MarkerViewOptions()
                        .position(new LatLng(43.547, 3.9788))
                        .title("Carnon-Plage")
                        .snippet("34470 Carnon-Plage"));


                //Marker on Grand Motte
                mapboxMap.addMarker(new MarkerViewOptions()
                        .position(new LatLng(43.558124, 4.073781))
                        .title("Plage du Couchant")
                        .snippet("34280 La Grand-Motte"));

                //Marker on Grau du Roi
                mapboxMap.addMarker(new MarkerViewOptions()
                        .position(new LatLng(43.533297, 4.136957))
                        .title("Plage du Grau du Roi")
                        .snippet("30240 Le Grau-du-Roi"));

                //Zoom auto with all markers
                LatLngBounds latLngBounds = new LatLngBounds.Builder()
                        .include(new LatLng(43.5333, 3.9333))
                        .include(new LatLng(43.5667, 3.95))
                        .include(new LatLng(43.547, 3.9788))
                        .include(new LatLng(43.558124, 4.073781))
                        .include(new LatLng(43.533297, 4.136957))
                        .build();

                mapboxMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50));

                CreateMarkerOnMap(mapboxMap);
            }
        });
    }

    //Création d'un marqueur en appuie long
    public void CreateMarkerOnMap(final MapboxMap mapboxMap) {

        mapboxMap.setOnMapLongClickListener(new MapboxMap.OnMapLongClickListener(){
            @Override
            public void onMapLongClick(@NonNull LatLng point) {

                //Description du maruqueur par défaut
                final Marker marker = mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(point))
                        .title("Nouveau multi")
                        .snippet("My destination"));

                //récupération des coordonnées GPS
                lat = point.getLatitude();
                lng = point.getLongitude();

                //Création de la fenetre AlertDialog
                LayoutInflater factory = LayoutInflater.from(CreateMulti.this);
                final View alertDialogView = factory.inflate(R.layout.custom_multi, null);
                //Création de l'AlertDialog
                AlertDialog.Builder adb = new AlertDialog.Builder(CreateMulti.this);

                //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
                adb.setView(alertDialogView);

                //On donne un titre à l'AlertDialog
                adb.setTitle("Création de l'activité en groupe");

                //On modifie l'icône de l'AlertDialog pour le fun ;)
                adb.setIcon(android.R.drawable.ic_dialog_alert);

                //On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
                adb.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
                        EditText titre = (EditText) alertDialogView.findViewById(R.id.EditText1);
                        EditText description = (EditText) alertDialogView.findViewById(R.id.EditText2);
                        EditText nbPersonnes = (EditText) alertDialogView.findViewById(R.id.EditText3);
                        DatePicker dateMulti = (DatePicker)alertDialogView.findViewById(R.id.datePicker);
                        TimePicker timeMulti = (TimePicker) alertDialogView.findViewById(R.id.timePicker);

                        //On affiche dans le marqueur les textes contenus dans les EditText de notre AlertDialog
                        titreMulti = titre.getText().toString();
                        descriptionMulti = description.getText().toString();
                        personnesMulti = Integer.parseInt(nbPersonnes.getText().toString());

                        //récupération de la date et de l'heure
                        int day = dateMulti.getDayOfMonth();
                        int month = dateMulti.getMonth();
                        int year =  dateMulti.getYear();
                        int hour = timeMulti.getCurrentHour();
                        int min = timeMulti.getCurrentMinute();

                        stringOfDate = day + "/" + (month+1) + "/" + year;
                        stringOfTime = hour + ":" + min;

                        marker.setTitle(titreMulti);
                        marker.setSnippet(descriptionMulti);
                        Toast.makeText(getApplicationContext(), "Nb : " + personnesMulti, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Date : " + stringOfDate, Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Heure : " + stringOfTime, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MultiActivity.class);
                        intent.putExtra("TAG_TITLE", titreMulti);
                        intent.putExtra("TAG_SNIPPET", descriptionMulti);
                        intent.putExtra("TAG_LAT", lat);
                        intent.putExtra("TAG_LNG", lng);
                        startActivity(intent);
                    }
                });

                //On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
                adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Lorsque l'on cliquera sur annuler on retourne sur CreateMulti
                        closeContextMenu();
                        //efface le marker par la mm occasion
                        mapboxMap.removeMarker(marker);
                    }
                });
                adb.show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        MapViewCreateMulti.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        MapViewCreateMulti.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        MapViewCreateMulti.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        MapViewCreateMulti.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        MapViewCreateMulti.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MapViewCreateMulti.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MapViewCreateMulti.onSaveInstanceState(outState);
    }
}

