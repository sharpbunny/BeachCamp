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
    Button CreateMultiBtn;

    Double geolist1;
    Double geolist2;

    Ville Palavas = new Ville("Palavas-les-flots", 43.5333, 3.9333);
    Ville Carnon = new Ville("Carnon-Plage", 43.547, 3.9788);
    Ville Perols = new Ville("Perols-Plage", 43.5667, 3.95);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.AccessToken));
        setContentView(R.layout.activity_multi);


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

        final List<Double> geo1 = new ArrayList<Double>();
        geo1.add(Palavas.Latitude);
        geo1.add(Carnon.Latitude);
        geo1.add(Perols.Latitude);

        final List<Double> geo2 = new ArrayList<Double>();
        geo2.add(Palavas.Longitude);
        geo2.add(Carnon.Longitude);
        geo2.add(Perols.Longitude);

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

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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










