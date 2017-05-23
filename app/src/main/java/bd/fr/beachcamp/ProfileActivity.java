package bd.fr.beachcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button fermerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final String loginProfil = "";

        fermerBtn = (Button)findViewById(R.id.fermerBtn);

        fermerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        TextView pseudoProfil = (TextView) findViewById(R.id.pseudoProfil);

        if (intent != null){
            pseudoProfil.setText(intent.getStringExtra(loginProfil));
        }
    }
}
