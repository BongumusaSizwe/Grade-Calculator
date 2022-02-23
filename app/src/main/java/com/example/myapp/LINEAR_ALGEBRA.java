package com.example.myapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.myapp.MainActivity.student_id;

/**
 * Created by Scorpions on 2019/04/22.
 */

public class LINEAR_ALGEBRA extends AppCompatActivity {
    EditText TUT_1, TEST_1, TUT_2, TUT_TYPE;
    String Assessment_Type;
    TextView tu1, tu2, tu3, tu4, tu, messageT;
    Button reload, button1, button2, button3, calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear__algebra);
        calculate = (Button) findViewById(R.id.calculate);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        reload = (Button) findViewById(R.id.reloader);
        TUT_1 = (EditText) findViewById(R.id.TUT_1);
        TEST_1 = (EditText) findViewById(R.id.TEST_1);
        TUT_2 = (EditText) findViewById(R.id.TUT_2);
        tu1 = (TextView) findViewById(R.id.tu1);
        tu2 = (TextView) findViewById(R.id.tu2);
        tu3 = (TextView) findViewById(R.id.tu3);
        tu4 = (TextView) findViewById(R.id.tu4);
        messageT = (TextView)findViewById(R.id.message1);
        reload.performClick();
    }

    //Part of adding marks to the database
    public void AssessmentType(View view) {

        switch (view.getId()) {
            case R.id.button1:
                Assessment_Type = "TUT_1";
                tu = tu1;
                TUT_TYPE = TUT_1;
                break;

            case R.id.button2:
                Assessment_Type = "TEST_1";
                tu = tu2;
                TUT_TYPE = TEST_1;
                break;

            case R.id.button3:
                Assessment_Type = "TUT_2";
                tu = tu3;
                TUT_TYPE = TUT_2;
                break;

            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    //Adds marks to the database
    public void add_mark(View view) {
        String Course_Name = "LINEAR_ALGEBRA";
        AssessmentType(view);
        String type = "add";
        String student_id = MainActivity.student_id;
        Double mark;
        if(TextUtils.isEmpty(TUT_TYPE.getText().toString().trim())){
            mark = Double.parseDouble(tu.getText().toString());
        }
        else {
            mark = Double.parseDouble(TUT_TYPE.getText().toString());
        }
        putMark(mark, tu);
        String mark1 = mark.toString();
        @SuppressLint("StaticFieldLeak") Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
            @Override
            protected void onPostExecute(String result) {

            }
        };
        backgroundworker2.execute(type, mark1, student_id, Assessment_Type, Course_Name);

    }


    public void putMark(Double num, TextView t) {
        String s = Double.toString(num);
        t.setText(s);
    }

    public void getMarks(View view) {
        Double Tut1, Test, Tut2, average;
        String Course_Name = "LINEAR_ALGEBRA";
        Tut1 = Double.parseDouble(tu1.getText().toString());
        Test = Double.parseDouble(tu2.getText().toString());
        Tut2 = Double.parseDouble(tu3.getText().toString());
        average = (Tut1 + (Test / 60) * (30) + Tut2);
        String message;     //Feedback to the user about their marks
        if(average<50){
            Double n = 50 - average;
            String K = Double.toString(n);
            message = "YOU NEED "+K+"%"+" T0 PASS THE COURSE";
            //Toast.makeText(MC.this,"YOU NEED "+K+"%"+" T0 PASS THE COURSE",Toast.LENGTH_LONG).show();
        }
        else{
            message = "CONGRATULATIONS!!!!       YOU HAVE ALREADY PASSED LINEAR ALGEBRA!!! ";
            //Toast.makeText(MC.this,"CONGRATS!!!!       YOU HAVE ALREADY PASSED MULTIVARIABLE CALCULUS ",Toast.LENGTH_LONG).show();
        }
        messageT.setText(message);
        tu4.setText(Double.toString(average) + "%");
        String type = "average";
        Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
            @Override
            protected void onPostExecute(String result) {
            }
        };
        backgroundworker2.execute(type, student_id, Double.toString(average), Course_Name);

    }


    //This will reload the results from the last use. Will insert zero if its first use.
    public void reloader(View view) {
        String course_name = "LINEAR_ALGEBRA";
        String type = "reload_marks";
        Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
            @Override
            protected void onPostExecute(String result) {
                process(result);
            }
        };
        backgroundworker2.execute(type, student_id, course_name);
    }

    //Processes the string found in the reloader method.
    public void process(String result) {
        String[] marks = result.split(" ");
        tu1.setText(marks[0]);
        tu2.setText(marks[1]);
        tu3.setText(marks[2]);
        tu4.setText(marks[3] + "%");

    }

    //Update the marks after insertion.
    public void update_all(View view){
        if(!TextUtils.isEmpty(TUT_1.getText().toString().trim())){
            button1.performClick();
        }
        if(!TextUtils.isEmpty(TEST_1.getText().toString().trim())){
            button2.performClick();
        }
        if(!TextUtils.isEmpty(TUT_2.getText().toString().trim())){
            button3.performClick();
        }
        calculate.performClick();
    }
}
