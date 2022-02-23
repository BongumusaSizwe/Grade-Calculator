package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Scorpions on 2019/04/21.
 */

public class Courses extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_courses);

        TextView corp= (TextView) findViewById(R.id.fin);
        TextView dbf= (TextView) findViewById(R.id.dbf);
        TextView mcom= (TextView) findViewById(R.id.mcom);
        TextView mcomc= (TextView) findViewById(R.id.mcomc);
        TextView corpc= (TextView) findViewById(R.id.buse);
        TextView dbfc= (TextView) findViewById(R.id.dbfc);
        TextView mc= (TextView) findViewById(R.id.mc);
        TextView mcc= (TextView) findViewById(R.id.mcc);
        TextView lac= (TextView) findViewById(R.id.lac);
        TextView tamc= (TextView) findViewById(R.id.tamc);
        TextView la= (TextView) findViewById(R.id.la);
        TextView tam= (TextView) findViewById(R.id.tam);
    }


    public void math2025(View view) {


    }

    public void math2019(View view) {
        startActivity(new Intent(this,LINEAR_ALGEBRA.class));
    }

    public void math2007(View view) {
        startActivity(new Intent(this,MC.class));
    }

    public void coms2013(View view) {

    }

    public void coms2002(View view) {
    }

    public void buse2000(View view) {
    }

    public void CourseView(View view) {

    }
}
