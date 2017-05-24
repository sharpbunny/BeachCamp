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
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.Mapbox;




import static bd.fr.beachcamp.R.id.mapView;

public class CreateMulti extends AppCompatActivity {

    private MapView MapViewCreateMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

                // When user clicks the map, animate to new camera location
                mapboxMap.setOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng point) {
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(43.5333, 3.9333)) // Sets the new camera position
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

