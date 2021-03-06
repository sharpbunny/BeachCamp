package bd.fr.beachcamp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.Mapbox;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.defaultValue;

public class MultiActivity extends AppCompatActivity {

    public MapView mapView;
    public Spinner MySpinner;
    Button CreateMultiBtn;

    Double geolist1;
    Double geolist2;

    String titre;
    String snippet;
    double lat;
    double lng;
    int personnes = 0;
    String heure;
    String date;

    Ville Palavas = new Ville("Palavas-les-flots", 43.5333, 3.9333);
    Ville Carnon = new Ville("Carnon-Plage", 43.547, 3.9788);
    Ville Perols = new Ville("Perols-Plage", 43.5667, 3.95);
    Ville ville;

    LatLngBounds latLngBounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.AccessToken));
        setContentView(R.layout.activity_multi);

        Intent intentMulti = getIntent();
        titre = intentMulti.getStringExtra("TAG_TITLE");
        snippet = intentMulti.getStringExtra("TAG_SNIPPET");
        lat = intentMulti.getDoubleExtra("TAG_LAT", defaultValue);
        lng = intentMulti.getDoubleExtra("TAG_LNG", defaultValue);
        personnes = intentMulti.getIntExtra("TAG_PERSONNES", defaultValue);
        heure = intentMulti.getStringExtra("TAG_HEURE");
        date = intentMulti.getStringExtra("TAG_DATE");

        ville = new Ville(titre,lat,lng);

        /*Toast.makeText(getApplicationContext(), "Titre " + titre, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "lat " + lat, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "lng " + lng, Toast.LENGTH_SHORT).show();*/

        CreateMultiBtn = (Button)findViewById(R.id.AddMultiBtn);

        CreateMultiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CreateMulti = new Intent(MultiActivity.this, CreateMulti.class);
                startActivity(CreateMulti);
            }
        });

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
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

                if (titre != null){
                    CreateMarker(mapboxMap);
                }
                //Zoom auto with all markers
                if (titre == null) {
                    latLngBounds = new LatLngBounds.Builder()
                            .include(new LatLng(43.5333, 3.9333))
                            .include(new LatLng(43.5667, 3.95))
                            .include(new LatLng(43.547, 3.9788))
                            .build();
                }
                else{
                    latLngBounds = new LatLngBounds.Builder()
                            .include(new LatLng(43.5333, 3.9333))
                            .include(new LatLng(43.5667, 3.95))
                            .include(new LatLng(43.547, 3.9788))
                            .include(new LatLng(ville.Latitude, ville.Longitude))
                            .build();
                }

                mapboxMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50));

                // When user clicks the map, animate to new camera location
                mapboxMap.setOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng point) {
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(geolist1, geolist2)) // Sets the new camera position
                                .zoom(17) // Sets the zoom
                                .bearing(180) // Rotate the camera
                                .tilt(30) // Set the camera tilt
                                .build(); // Creates a CameraPosition from the builder

                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 7000);
                    }
                });
            }
        });

        MySpinner = (Spinner) findViewById(R.id.MultiSpinner);
        //MySpinner.onCreate(savedInstanceState);

        // Example of the elements included in the spinner
        List<String> categories = new ArrayList<String>();
        categories.add(Palavas.NomDeVille);
        categories.add(Carnon.NomDeVille);
        categories.add(Perols.NomDeVille);
        if (titre !=null)
            categories.add(ville.NomDeVille);

        final List<Double> geo1 = new ArrayList<Double>();
        geo1.add(Palavas.Latitude);
        geo1.add(Carnon.Latitude);
        geo1.add(Perols.Latitude);
        if (titre !=null)
            geo1.add(ville.Latitude);

        final List<Double> geo2 = new ArrayList<Double>();
        geo2.add(Palavas.Longitude);
        geo2.add(Carnon.Longitude);
        geo2.add(Perols.Longitude);
        if (titre !=null)
            geo2.add(ville.Longitude);

        final List<String> dateList = new ArrayList<String>();
        dateList.add("06/07/2017");
        dateList.add("09/07/2017");
        dateList.add("11/07/2017");
        if (titre !=null)
            dateList.add(date);

        final List<Integer> personnesList = new ArrayList<Integer>();
        personnesList.add(10);
        personnesList.add(17);
        personnesList.add(20);
        if (titre !=null)
            personnesList.add(personnes);

        final List<String> descriptionList = new ArrayList<String>();
        descriptionList.add("Description de l'activité 1");
        descriptionList.add("Description de l'activité 2");
        descriptionList.add("Description de l'activité 3");
        if (titre !=null)
            descriptionList.add(snippet);

        // Creating an adaptator to read the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down list with radio button on it
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // This is how we link the dataAdapter to the spinner
        MySpinner.setAdapter(dataAdapter);

        MySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                geolist1 = geo1.get(position);
                //Toast.makeText(parent.getContext(), "Selected: " + geolist1, Toast.LENGTH_LONG).show();
                geolist2 = geo2.get(position);
                //Toast.makeText(parent.getContext(), "Selected: " + geolist2, Toast.LENGTH_LONG).show();
                TextView dateCreation = (TextView)findViewById(R.id.MultiDate);
                TextView nbPersonnes = (TextView)findViewById(R.id.NumberRegistration);
                TextView description = (TextView)findViewById(R.id.descriptionText);
                dateCreation.setText(dateList.get(position).toString());
                nbPersonnes.setText(String.valueOf(personnesList.get(position).intValue()));
                description.setText(descriptionList.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void CreateMarker (MapboxMap mapboxMap){
        mapboxMap.addMarker(new MarkerViewOptions()
                .position(new LatLng(ville.Latitude, ville.Longitude))
                .title(ville.NomDeVille)
                .snippet(snippet));
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    // Déclaration de la classe Ville et de son constructeur. Afin de récupérer les données dans le spinner pour que l'utilisateur fasse son choix.
    public class Ville {

        String NomDeVille;
        double Latitude;
        double Longitude;


        public Ville() {
            NomDeVille = "";
            Latitude = 0;
            Longitude = 0;
        }
        public Ville(String NomVille, double Latt, double Longt){
            NomDeVille = NomVille;
            Latitude = Latt;
            Longitude = Longt;
        }
    }
}










