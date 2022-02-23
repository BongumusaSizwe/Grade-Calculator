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

public class DBF extends AppCompatActivity {
    EditText LAB1, LAB2, LAB3, LAB4, PROJECT, TEST1,type1;
    String Assessment_Type;
    Button reloader, button1, button2, button3,button4, button7, button8 ,calculate;
    TextView lab1, lab2, lab3, lab4,project, test1,tu,tu4, messageT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbf);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3= (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        calculate = (Button)findViewById(R.id.calculate);
        reloader = (Button) findViewById(R.id.reloader);
        LAB1 = (EditText) findViewById(R.id.TUT_1);
        LAB2 = (EditText) findViewById(R.id.TEST_1);
        LAB3 = (EditText) findViewById(R.id.TUT_2);
        LAB4 = (EditText) findViewById(R.id.LAB_4);
        PROJECT = (EditText) findViewById(R.id.PROJECT);
        TEST1 = (EditText) findViewById(R.id.test1);
        lab1= (TextView) findViewById(R.id.tu1);
        lab2 = (TextView) findViewById(R.id.tu2);
        lab3 = (TextView) findViewById(R.id.tu3);
        lab4 = (TextView) findViewById(R.id.tut4);
        project = (TextView) findViewById(R.id.tu7);
        test1 = (TextView) findViewById(R.id.tu8);
        tu4 = (TextView) findViewById(R.id.tu4);
        messageT = (TextView)findViewById(R.id.message1);
        reloader.performClick();
    }

    public void AssessmentType(View view){

        switch(view.getId()) {
            case R.id.button1:
                Assessment_Type = "LAB_1";
                tu=lab1;
                type1 = LAB1;
                break;

            case R.id.button2:
                Assessment_Type = "LAB_2";
                tu = lab2;
                type1= LAB2;
                break;

            case R.id.button3:
                Assessment_Type = "LAB_3";
                tu = lab3;
                type1= LAB3;
                break;

            case R.id.button4:
                Assessment_Type = "LAB_4";
                tu=lab4;
                type1 = LAB4;
                break;

            case R.id.button8:
                Assessment_Type = "TEST_1";
                tu = test1;
                type1 = TEST1;
                break;
            case R.id.button7:
                Assessment_Type = "PROJECT";
                tu = project;
                type1= PROJECT;
                break;



            default:
                throw new RuntimeException("Unknown button ID");
        }
    }


    public void add_mark(View view){
        String Course_Name = "DBF";
        AssessmentType(view);
        String type = "add";
        String student_id = MainActivity.student_id;
        Double mark;
        if(TextUtils.isEmpty(type1.getText().toString().trim())){
            mark = Double.parseDouble(tu.getText().toString());
        }
        else {
            mark = Double.parseDouble(type1.getText().toString());
        }
        putMark(mark, tu);
        String mark1 = mark.toString();
        @SuppressLint("StaticFieldLeak") Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
            @Override
            protected void onPostExecute(String result) {

            }
        };
        backgroundworker2.execute(type, mark1, student_id, Assessment_Type, Course_Name);
        //Backgroundworker backgroundworker= new Backgroundworker(this);
        //backgroundworker.execute(type, mark1, student_id, Assessment_Type, Course_Name);
    }


    public void putMark(Double num, TextView t){
        String s = Double.toString(num);
        t.setText(s);
    }

    public void getMarks(View view){
        String Course_Name = "DBF";
        Double lab11, lab22, lab33, lab44,Project ,TEST, average;
        lab11= Double.parseDouble(lab1.getText().toString());
        lab22= Double.parseDouble(lab2.getText().toString());
        lab33 = Double.parseDouble(lab3.getText().toString());
        lab44=  Double.parseDouble(lab4.getText().toString());
        Project= Double.parseDouble(project.getText().toString());
        TEST=   Double.parseDouble(test1.getText().toString());
        average = ((lab11+lab22+lab33+lab44)/40)*10+((Project)/100)*20+((TEST)/50)*20;
        average = Double.parseDouble(String.format("%.2f", average));
        String message;
        if(average<50){
            Double n = 50 - average;
            String K = Double.toString(n);
            message = "YOU NEED "+K+"%"+" T0 PASS THE COURSE";
            //Toast.makeText(MC.this,"YOU NEED "+K+"%"+" T0 PASS THE COURSE",Toast.LENGTH_LONG).show();
        }
        else{
            message = "CONGRATULATIONS!!!!YOU DONT NEED ANY EXTRA MARKS, YOU HAVE ALREADY PASSED DATABASE FANDAMENTALS!!!!! ";
            //Toast.makeText(MC.this,"CONGRATS!!!!       YOU HAVE ALREADY PASSED ",Toast.LENGTH_LONG).show();
        }
        messageT.setText(message);
        tu4.setText(Double.toString(average) + "%");

        tu4.setText(Double.toString(average) + "%");
        String type = "average";
        Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
            @Override
            protected void onPostExecute(String result) {
            }
        };
        backgroundworker2.execute(type, student_id,Double.toString(average),Course_Name);
    }

    public void reloader(View view){
        String course_name = "DBF";
        String type = "reload_marks";
        Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
            @Override
            protected void onPostExecute(String result) {
                process(result);
            }
        };
        backgroundworker2.execute(type, student_id, course_name);
    }

    public void process(String result){
        String[] marks = result.split(" ");
        lab1.setText(marks[0]);
        lab2.setText(marks[1]);
        lab3.setText(marks[2]);
        lab4.setText(marks[3]);
        test1.setText(marks[4]);
        project.setText(marks[5]);
        tu4.setText(marks[6] + "%");
    }

    public void update_all(View view){
        if(!TextUtils.isEmpty(LAB1.getText().toString().trim())){
            button1.performClick();
        }
        if(!TextUtils.isEmpty(LAB2.getText().toString().trim())){
            button2.performClick();
        }
        if(!TextUtils.isEmpty(LAB3.getText().toString().trim())){
            button3.performClick();
        }
        if(!TextUtils.isEmpty(LAB4.getText().toString().trim())){
            button4.performClick();

        }
        if(!TextUtils.isEmpty(PROJECT.getText().toString().trim())){
            button7.performClick();

        }
        if(!TextUtils.isEmpty(TEST1.getText().toString().trim())){
            button8.performClick();

        }

        calculate.performClick();
    }

}
