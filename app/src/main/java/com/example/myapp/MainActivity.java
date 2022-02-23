package com.example.myapp;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Email, Password;
    private Button Login;
    public static String student_id;  //Since the student number is used almost everywhere, we make it static here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();
    }

    private void setupUIViews() {
        Email = (EditText) findViewById(R.id.etemail);
        Password = (EditText) findViewById(R.id.etpass);
      //Login = (Button) findViewById(R.id.blogin);
    }

    public void onLogin(View view) {
        String email= Email.getText().toString();
        String password = Password.getText().toString();
        String type = "login";
        student_id = email;
        Backgroundworker backgroundworker= new Backgroundworker(this);
        backgroundworker.execute(type,email,password);
        Password.setText(null);
    }

    public void forgotPass(View view){
        Toast.makeText(getApplicationContext(),"!!!",Toast.LENGTH_LONG).show();
    }

    public void openReg(View view) {

        startActivity(new Intent(this,Register.class));
    }

}