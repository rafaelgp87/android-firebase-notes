package notes.com.example.rafaelgp87.cursofirebasenotes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password;
    Button register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userE = email.getText().toString().trim();
                String passE = password.getText().toString().trim();

                if(TextUtils.isEmpty(userE)) {
                    Toast.makeText(getApplicationContext(), "Coloca un correo", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(passE)) {
                    Toast.makeText(getApplicationContext(), "Coloca un password", Toast.LENGTH_SHORT).show();
                }

                auth.createUserWithEmailAndPassword(userE,passE)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Tenemos un problema",
                                            Toast.LENGTH_SHORT).show();

                                    Log.e("Email","***************************");
                                    Log.e("Email", task.getException().toString());
                                    Log.e("Email","***************************");

                                } else {
                                    Toast.makeText(getApplicationContext(), "Se a creado el usuario",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(RegisterActivity.this,UserActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
