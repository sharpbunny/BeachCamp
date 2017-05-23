package bd.fr.beachcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity {

    ImageButton profilBtn;
    Button decoBtn;
    final String loginProfil = "";

    TextView loginDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        final String EXTRA_LOGIN = "user_login";

        profilBtn = (ImageButton)findViewById(R.id.profilBtn);
        decoBtn = (Button)findViewById(R.id.decoBtn);


        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profil = new Intent(SelectionActivity.this, ProfileActivity.class);
                profil.putExtra(loginProfil, loginDisplay.getText().toString());
                startActivity(profil);

            }
        });

        Intent intent = getIntent();

        loginDisplay = (TextView) findViewById(R.id.nomBox);

        if (intent != null){
            loginDisplay.setText(intent.getStringExtra(EXTRA_LOGIN));
        }

        decoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deco = new Intent(SelectionActivity.this, MainActivity.class);
                startActivity(deco);

            }
        });


    }
}
