package bd.fr.beachcamp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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


import java.util.ArrayList;
import java.util.List;


public class MultiActivity extends AppCompatActivity {

    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.AccessToken));

        setContentView(R.layout.activity_multi);


        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(final MapboxMap mapboxMap){
                //Add your marker here
                mapboxMap.addMarker(new MarkerViewOptions()
                    .position(new LatLng(43.564525, 3.845017))
                    .title("AFPA St Jean de Védas")
                    .snippet("12 Rue Jean Mermoz, 34430 Saint-Jean-de-Védas"));


                // When user clicks the map, animate to new camera location
                mapboxMap.setOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng point) {
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(43.564525, 3.845017)) // Sets the new camera position
                                .zoom(17) // Sets the zoom
                                .bearing(180) // Rotate the camera
                                .tilt(30) // Set the camera tilt
                                .build(); // Creates a CameraPosition from the builder

                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 7000);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}

/*
        // Description of the spinner element

        Spinner Multispinner = (Spinner) findViewById(R.id.MultiSpinner);

        // Add the click listener on the MultiSpinner

        Multispinner.setOnItemSelectedListener(this);

        // Example of the elements included in the spinner

        List<String> categories = new ArrayList<String>();
        categories.add("Palavas");
        categories.add("Le Petit Travers");
        categories.add("Perols");
        categories.add("Villeneuve-les-Maguelones");

        // Creating an adaptator to read the spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down list with radio button on it

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // This is how we link the dataAdapter to the spinner

        Multispinner.setAdapter(dataAdapter);



    public void  onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String elementoftheSpinner = parent.getItemAtPosition(position).toString();

        // How to show the element which is selected

        Toast.makeText(parent.getContext(), "Chose:"+ elementoftheSpinner, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0){

    }
}
*/