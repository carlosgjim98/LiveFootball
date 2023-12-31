package ifp.project.livefootball.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import ifp.project.livefootball.Database.Database;
import ifp.project.livefootball.MainMenu.MainMenuActivity;
import ifp.project.livefootball.R;

public class LogInActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private EditText editText1;
    private TextView textView3;
    private EditText editText2;
    private Button button1;
    private Button button2;
    private Database db;
    private String textContent1;
    private String textContent2;
    protected Intent changeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        textView1 = (TextView) findViewById(R.id.textView1_logIn);
        textView2 = (TextView) findViewById(R.id.textView2_logIn);
        textView3 = (TextView) findViewById(R.id.textView3_logIn);
        editText1 = (EditText) findViewById(R.id.editText1_logIn);
        editText2 = (EditText) findViewById(R.id.editText2_logIn);
        button1 = (Button) findViewById(R.id.button1_logIn);
        button2 = (Button) findViewById(R.id.button2_logIn);
        db= new Database(this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textContent1 = editText1.getText().toString();
                textContent2 = editText2.getText().toString();

                if (textContent1.equals("") || textContent2.equals("")) {
                    Toast toast = Toast.makeText(LogInActivity.this, "El contenido de ambas cajas es obligatorio", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String correctPassword = db.getPass(textContent1);


                    if (correctPassword != null && correctPassword.equals(textContent2)) {
                        // El nombre de usuario y la contraseña son correctos
                        Toast.makeText(LogInActivity.this, "Tienes acceso", Toast.LENGTH_SHORT).show();
                        changeActivity = new Intent(LogInActivity.this, MainMenuActivity.class);
                        finish();
                        startActivity(changeActivity);
                    } else {
                        // Usuario y/o contraseña incorrectos
                        Toast toast = Toast.makeText(LogInActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerTask myTimertask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent changeActivity= new Intent(LogInActivity.this, RegisterActivity.class);
                        finish();
                        startActivity(changeActivity);
                    }
                };

                Timer timer = new Timer();
                timer.schedule(myTimertask, 3000);
            }
        });
    }

}