package bd.fr.beachcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity {

    ImageButton profilBtn, multiBtn;
    Button decoBtn;
    // Création du bouton qui permet de faire le lien entre cette activité et celle de la création du multi (oui commentaires en français).
    Button CreateMultiBtn;
    final String loginProfil = "";

    TextView loginDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        final String EXTRA_LOGIN = "user_login";

        profilBtn = (ImageButton)findViewById(R.id.profilBtn);
        multiBtn = (ImageButton)findViewById(R.id.multiBtn);
        decoBtn = (Button)findViewById(R.id.decoBtn);
        CreateMultiBtn = (Button)findViewById(R.id.AddMultiBtn);

        // Je lui indique un listener pour le lier au clic et l'envoyer vers la bonne vue ( CreateMulti activity).




        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profil = new Intent(SelectionActivity.this, ProfileActivity.class);
                profil.putExtra(loginProfil, loginDisplay.getText().toString());
                startActivity(profil);

            }
        });

        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent multi = new Intent(SelectionActivity.this, MultiActivity.class);
                startActivity(multi);

            }
        });

        CreateMultiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CreateMulti = new Intent(SelectionActivity.this, CreateMulti.class);
                startActivity(CreateMulti);

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



