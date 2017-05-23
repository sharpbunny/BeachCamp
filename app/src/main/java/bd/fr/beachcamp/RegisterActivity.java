package bd.fr.beachcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button validerBtn, annulerBtn;
    EditText pseudoBox, nomBox, prenomBox, emailBox, emailConfBox, passwordBox, passwordConfBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        annulerBtn = (Button)findViewById(R.id.annulerBtn);
        validerBtn = (Button)findViewById(R.id.validerBtn);

        pseudoBox = (EditText)findViewById(R.id.pseudoBox);
        nomBox = (EditText)findViewById(R.id.nomBox);
        prenomBox = (EditText)findViewById(R.id.prenomBox);
        emailBox = (EditText)findViewById(R.id.emailBox);
        emailConfBox = (EditText)findViewById(R.id.emailConfBox);

        passwordBox = (EditText)findViewById(R.id.passwordBox);
        passwordConfBox = (EditText)findViewById(R.id.passwordConfBox);

        annulerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        validerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordBox.getText().toString().equals(passwordConfBox.getText().toString()) && passwordBox.getText().length() >0 && passwordConfBox.getText().length()>0) {
                    Toast.makeText(getApplicationContext(),
                            "mdp OK",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "mdp NOK",Toast.LENGTH_SHORT).show();
                }

                if (emailBox.getText().toString().equals(emailConfBox.getText().toString()) && emailBox.getText().length() >0 && emailConfBox.getText().length()>0){
                    Toast.makeText(getApplicationContext(),
                            "email OK",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "email NOK",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
