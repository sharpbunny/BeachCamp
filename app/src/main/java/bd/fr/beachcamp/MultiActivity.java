package bd.fr.beachcamp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class MultiActivity extends AppCompatActivity {

    public MapView mapView;
    public Spinner MySpinner;
    public TextView MyMultiName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView = (MapView)findViewById(R.id.mapView);
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

                Ville Palavas = new Ville("Palavas", 43.5333, 39333){

                };

                Ville Carnon = new Ville("Carnon", 43.547, 3.9788){

                };

                Ville Perols = new Ville("Pérols", 43.5667, 3.95);

                // When user clicks the map, animate to new camera location

                LatLngBounds latLngBounds = new LatLngBounds.Builder()
                        .include(new LatLng(43.5333, 39333))
                        .include(new LatLng(43.5667, 3.95))
                        .include(new LatLng(43.547, 39788))
                        .build();

                mapboxMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50));


                        MySpinner = (Spinner) findViewById(R.id.MultiSpinner);

                        final String MyTextToShow = String.valueOf(MySpinner.getSelectedItem());
                        final TextView MyMultiName = (TextView) findViewById(R.id.MultiSelected);
                        MyMultiName.setText(MyTextToShow);

                        // Example of the elements included in the spinner
                        List<String> categories = new ArrayList<String>();
                        categories.add(Palavas.NomDeVille);
                        categories.add(Carnon.NomDeVille);
                        categories.add(Perols.NomDeVille);

                        // Creating an adaptator to read the spinner

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MultiActivity.this, android.R.layout.simple_spinner_item, categories);

                        // Drop down list with radio button on it

                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        // This is how we link the dataAdapter to the spinner
                        MySpinner.setAdapter(dataAdapter);
                        MySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                MyMultiName.setText(MyTextToShow);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }


                            public void onItemSelected(AdapterView joph) {

                            }
                        });
                    }
                });
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
                     class Ville {

                        String NomDeVille;
                        double Latitude;
                        double Longitude;


                        public Ville() {
                            NomDeVille = "";
                            Latitude = 0;
                            Longitude = 0;
                        }

                        public Ville(String NomVille, double Latt, double Longt) {
                            NomDeVille = NomVille;
                            Latitude = Latt;
                            Longitude = Longt;
                        }
                    }
    }














