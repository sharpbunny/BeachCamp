package bd.fr.beachcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    int caracteristiques[] = { 120, 140 , 70 , 50 , 85, 100};
    String caracNom [] = { "force", "endurance" , "agilité" , "altruisme" , "combativité","intelligence" };
    String loginProfil = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        TextView pseudoProfil = (TextView) findViewById(R.id.pseudoProfil);

        if (intent != null){
            pseudoProfil.setText(intent.getStringExtra(loginProfil));
        }
        setupPieChart();
    }

    private void setupPieChart() {

        //Populataing a list of PieEntries

        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < caracteristiques.length ; i++) {
            pieEntries.add( new PieEntry(caracteristiques[i], caracNom[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Données utilisateur");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        // Get the chart

        PieChart chart = (PieChart)findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }
}
