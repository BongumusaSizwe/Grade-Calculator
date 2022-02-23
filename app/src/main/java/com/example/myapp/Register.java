package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    //Puts restrictions to the password. We don't want user marks get compromised
    private static final Pattern STUDENT_PATTERN=
            Pattern.compile("^" +
                    "(?=.*[0-9])"+
                    "(?=\\S+$)"+
                    ".{7,}"  +
                    "$");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+._!,/`~=])" +
                    "(?=\\S+$)" +
                    ".{8,}" +
                    "$");

    EditText Email,Password,Name_Surname,Student_Number,Faculty,Year_of_study;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Email= (EditText) findViewById(R.id.textView);
        Password= (EditText) findViewById(R.id.editText);
        Name_Surname= (EditText) findViewById(R.id.name);
        Student_Number=(EditText) findViewById(R.id.id);
        Faculty=(EditText) findViewById(R.id.faculty);
        Year_of_study=(EditText) findViewById(R.id.year);
    }
    public boolean validateStudent_Number(){
        String studentNumber = Student_Number.getText().toString().trim();

        if (studentNumber.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please enter a student number",Toast.LENGTH_LONG).show();
            return false;
        } else if (!STUDENT_PATTERN.matcher(studentNumber).matches()) {
            Toast.makeText(getApplicationContext(),"Please enter a valid Student Number",Toast.LENGTH_LONG).show();
            return false;
        } else {

            return true;
        }
    }

    public boolean validateEmail() {
        String emailInput = Email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please enter an email",Toast.LENGTH_LONG).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(getApplicationContext(),"Please enter a valid email",Toast.LENGTH_LONG).show();
            return false;
        } else {

            return true;
        }
    }
    public boolean validatefaculty(){
        String FacultyInput=Faculty.getText().toString();
        if(FacultyInput.equals("SCIENCE")||FacultyInput.equals("HUMANITIES")||FacultyInput.equals("COMMERCE,LAW AND MANAGEMENT")||FacultyInput.equals("ENGINEERING AND THE BUILT ENVIRONMENT")||FacultyInput.equals("HEALTH SCIENCES")){
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"PLEASE USE CAPITAL LETTERS",Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean validatePassword(){
        String passwordInput=Password.getText().toString().trim();
        if(passwordInput.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter a password",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            Toast.makeText(getApplicationContext(),"Password must contain lower letter cases," +
                    "upper letter cases," +
                    "special characters " +
                    "and digits ",Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }

    }


    public void onReg(View view) {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String name_surname = Name_Surname.getText().toString();
        String student_number = Student_Number.getText().toString();
        String faculty = Faculty.getText().toString();
        String year = Year_of_study.getText().toString();
        String type = "register";
        Backgroundworker backgroundworker = new Backgroundworker(this);
        if (validateEmail() && validatePassword() && validateStudent_Number() && validatefaculty()) {
            backgroundworker.execute(type, email, password, name_surname, student_number, faculty, year);
        }
    }



    public void goback(View view) {

        startActivity(new Intent(this,MainActivity.class));
    }

    public void onnext(View view) {

        startActivity(new Intent(this,Courses.class));
    }
}