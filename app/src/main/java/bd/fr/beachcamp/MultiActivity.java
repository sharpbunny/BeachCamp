package bd.fr.beachcamp;

import android.support.v7.app.AppCompatActivity;
package edmt.dev.androidmapbox;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MultiActivity extends AppCompatActivity implements OnItemSelectedListener{
    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoia2Fycm8iLCJhIjoiY2ozMDIzbnF2MDAwbDJxczFhOHpoOXFrNiJ9.uuwYhFQgooY47_dIaLCBsA");
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
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
    }


    public void  onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String elementoftheSpinner = parent.getItemAtPosition(position).toString();

        // How to show the element which is selected

        Toast.makeText(parent.getContext(), "Chose:"+ elementoftheSpinner, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0){

    }
}
