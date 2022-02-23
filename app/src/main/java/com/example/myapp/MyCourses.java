package com.example.myapp;
import java.util.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.example.myapp.MainActivity.student_id;

public class MyCourses extends AppCompatActivity {
    TextView welcome, mc, la, cam, phy2, chem,coms_mc, tam, dbf;
    Button reloader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);
        welcome = (TextView) findViewById(R.id.Welcome);
        welcome.setText("Welcome user:" + student_id);
        mc = (TextView) findViewById(R.id.text1);
        la = (TextView) findViewById(R.id.text2);
        cam = (TextView) findViewById(R.id.tex3);
        chem = (TextView) findViewById(R.id.tex4);
        phy2= (TextView) findViewById(R.id.tex5);
        coms_mc = (TextView) findViewById(R.id.tex6);
        tam = (TextView) findViewById(R.id.tex7);
        dbf = (TextView) findViewById(R.id.tex8);
        reloader =(Button) findViewById(R.id.reloader);
        reloader.performClick();
    }

    public void reload(View view){
        String type = "reload";

        Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
            @Override
            protected void onPostExecute(String result) {
                process(result);
            }
        };
        backgroundworker2.execute(type, student_id);

    }

    public void process(String output){

        try {
            //Map<String, Character> courses =new HashMap<String, Character>();
            if(output.charAt(1) == '1'){
                mc.setVisibility(View.VISIBLE);
            }
            if(output.charAt(2) == '1'){
                la.setVisibility(View.VISIBLE);
            }
            if(output.charAt(3) == '1'){
                cam.setVisibility(View.VISIBLE);
            }
            if(output.charAt(4) == '1'){
                chem.setVisibility(View.VISIBLE);
            }
            if(output.charAt(5) == '1'){
                phy2.setVisibility(View.VISIBLE);
            }
            if(output.charAt(6) == '1'){
                coms_mc.setVisibility(View.VISIBLE);
            }
            if(output.charAt(7) == '1'){
                tam.setVisibility(View.VISIBLE);
            }
            if(output.charAt(8) == '1'){
                dbf.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void math2007(View view) {

        startActivity(new Intent(this,MC.class));
    }

    public void math2019(View view) {

        startActivity(new Intent(this,LINEAR_ALGEBRA.class));
    }

    public void DBF(View view) {

        startActivity(new Intent(this,DBF.class));
    }
    public void MOBILE_COMPUTING(View view){
        startActivity(
                new Intent(
                        this,MOBILE_COMPUTING.class)

        );
    }



    public void logout(View view){
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    public void AddCourses(View view){
        startActivity(new Intent(this,selected_c.class));
    }

    public void Remove_Courses(View view){
        startActivity(new Intent(this, Remove_Courses.class));
    }

}