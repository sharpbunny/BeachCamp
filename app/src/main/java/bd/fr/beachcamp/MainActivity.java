package bd.fr.beachcamp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.graphics.Color;

import android.util.Log;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1, b2;
    EditText login, password;

    TextView tentative;
    int compteur = 3;

    final String EXTRA_LOGIN = "user_login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);

        b1 = (Button) findViewById(R.id.loginBtn);
        b2 = (Button) findViewById(R.id.createBtn);

        tentative = (TextView) findViewById(R.id.tentative);
        tentative.setVisibility(View.INVISIBLE);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login != null) {
                    login.setText("");
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent selection = new Intent(MainActivity.this, SelectionActivity.class);
                    selection.putExtra(EXTRA_LOGIN, login.getText().toString());
                    startActivity(selection);
                } else {
                    Toast.makeText(getApplicationContext(), "login/Mdp incorrect", Toast.LENGTH_SHORT).show();

                    tentative.setVisibility(View.VISIBLE);
                    tentative.setBackgroundColor(Color.RED);
                    compteur--;
                    tentative.setText("Nombre essai restant " + Integer.toString(compteur));

                    if (compteur == 0) {

                        b1.setEnabled(false);
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);

            }
        });
    }
}




