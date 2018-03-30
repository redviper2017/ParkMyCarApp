package tanzeer.parkmycar;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private TextView btnAlreadyHaveAccount;
    private Button signUp;
    private EditText userName, email, phone, password, registrationNo;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseUsers;

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        btnAlreadyHaveAccount = findViewById(R.id.txtBtn_alreadyHaveAccount);
        signUp=findViewById(R.id.btn_signupFromSignUpPage);
        userName=findViewById(R.id.txt_usernameDuringRegistration);
        email=findViewById(R.id.txt_emailDuringRegistration);
        phone=findViewById(R.id.txt_phoneDuringRegistration);
        password=findViewById(R.id.txt_passwordDuringRegistration);
        registrationNo=findViewById(R.id.txt_registrationNoDuringRegistration);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseUsers= database.getReference();
        btnAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String us = userName.getText().toString().trim();
                final String em = email.getText().toString().trim();
                final String pho = phone.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                final String reg = registrationNo.getText().toString().trim();
                if (TextUtils.isEmpty(us)){
                    Toast.makeText(getApplicationContext(),"Enter username !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(em)){
                    Toast.makeText(getApplicationContext(),"Enter email address !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Enter password !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length()<6){
                    Toast.makeText(getApplicationContext(),"Password is too short, enter minimum 6 characters",Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(pho)){
                    Toast.makeText(getApplicationContext(),"Enter phone number !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(reg)){
                    Toast.makeText(getApplicationContext(),"Enter vehicle registration number !",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(em,pass)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUp.this, "Successfully Signed Up",Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()){
                                    Toast.makeText(SignUp.this,"Authentication failed."+task.getException(),Toast.LENGTH_SHORT).show();

                                }else{
                                    startActivity(new Intent(SignUp.this,Main.class));
                                    String id = databaseUsers.push().getKey();
                                    Users user = new Users(us,em,pho,pass,reg);
                                    Log.d(TAG,"username:" +user.getUserName());
                                    databaseUsers.child("users").child(id).setValue(user);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
