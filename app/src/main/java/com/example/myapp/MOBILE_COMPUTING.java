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

public class MOBILE_COMPUTING extends AppCompatActivity {
    EditText LAB1, LAB2, LAB3, LAB4,LAB5,LAB6,QUICK_LAB1,QUICK_LAB2, PROJECT, TEST1,type1;
    String Assessment_Type;
    Button reloader, button1, button2, button3 , button4, button5,
            button6, button7, button8, button9, button10, calculate;
    TextView lab1, lab2, lab3, lab4,lab5,lab6,quick_lab1,quick_lab2,project, test1,tu,tu4, messageT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile__computing);
        reloader = (Button) findViewById(R.id.button12);
        LAB1 = (EditText) findViewById(R.id.LAB_1);
        LAB2 = (EditText) findViewById(R.id.LAB_2);
        LAB3 = (EditText) findViewById(R.id.LAB_3);
        LAB4 = (EditText) findViewById(R.id.LAB_4);
        LAB5=(EditText)findViewById(R.id.LAB_5);
        LAB6=(EditText)findViewById(R.id.LAB_6);
        PROJECT = (EditText) findViewById(R.id.PROJECT);
        QUICK_LAB1=(EditText)findViewById(R.id.QUICK_LAB_1);
        QUICK_LAB2=(EditText)findViewById(R.id.QUICK_LAB_2);
        TEST1 = (EditText) findViewById(R.id.TEST_1);
        lab1= (TextView) findViewById(R.id.lab_1);
        lab2 = (TextView) findViewById(R.id.lab_2);
        lab3 = (TextView) findViewById(R.id.lab_3);
        lab4 = (TextView) findViewById(R.id.lab_4);
        lab5=(TextView) findViewById(R.id.lab_5);
        lab6=(TextView)findViewById(R.id.lab_6);
        quick_lab1=(TextView) findViewById(R.id.quick_lab_1);
        quick_lab2=(TextView)findViewById(R.id.quick_lab_2);
        project = (TextView) findViewById(R.id.project);
        test1 = (TextView) findViewById(R.id.test_1);
        tu4 = (TextView) findViewById(R.id.tu4);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        calculate = (Button) findViewById(R.id.calculate);
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

            case R.id.button5:
                Assessment_Type = "LAB_5";
                tu=lab5;
                type1 = LAB5;
                break;
            case R.id.button6:
                Assessment_Type = "LAB_6";
                tu=lab6;
                type1 = LAB6;
                break;

            case R.id.button7:
                Assessment_Type = "QUICK_LAB1";
                tu=quick_lab1;
                type1 = QUICK_LAB1;
                break;

            case R.id.button8:
                Assessment_Type = "QUICK_LAB2";
                tu=quick_lab2;
                type1 = QUICK_LAB2;
                break;

            case R.id.button9:
                Assessment_Type = "PROJECT";
                tu = project;
                type1 = PROJECT;
                break;

            case R.id.button10:
                Assessment_Type = "TEST_1";
                tu = test1;
                type1= TEST1;
                break;



            default:
                throw new RuntimeException("Unknown button ID");
        }
    }


    public void add_mark(View view){
        String Course_Name = "MOBILE_COMPUTING";
        AssessmentType(view);
        String type = "add";
        String student_id = MainActivity.student_id;
        Double mark = Double.parseDouble(type1.getText().toString());
        putMark(mark, tu);
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
    }


    public void putMark(Double num, TextView t){
        String s = Double.toString(num);
        t.setText(s);
    }

    public void getMarks(View view){
        String Course_Name = "MOBILE_COMPUTING";
        Double lab_1, lab_2, lab_3, lab_4,project_1,lab_5,lab_6,quicklab_1,quicklab_2,test_1, average;
        lab_1= Double.parseDouble(lab1.getText().toString());
        lab_2= Double.parseDouble(lab2.getText().toString());
        lab_3 = Double.parseDouble(lab3.getText().toString());
        lab_4=  Double.parseDouble(lab4.getText().toString());
        lab_5 = Double.parseDouble(lab5.getText().toString());
        lab_6=  Double.parseDouble(lab6.getText().toString());
        quicklab_1=  Double.parseDouble(quick_lab1.getText().toString());
        quicklab_2=  Double.parseDouble(quick_lab2.getText().toString());
        project_1= Double.parseDouble(project.getText().toString());
        test_1=   Double.parseDouble(test1.getText().toString());
        average = ((lab_1+lab_2+lab_3+lab_4+lab_5+lab_6)/60)*10+((project_1)/100)*20+((test_1)/60)*20+((quicklab_1+quicklab_2)/20)*10;
        average = Double.parseDouble(String.format("%.2f", average));
        String message;
        if(average<40){
            Double n = 40 - average;
            String K = Double.toString(n);
            message = "YOU NEED "+K+"%"+" T0 PASS THE COURSE";
            //Toast.makeText(MC.this,"YOU NEED "+K+"%"+" T0 PASS THE COURSE",Toast.LENGTH_LONG).show();
        }
        else{
            message = "CONGRATULATIONS!!!!   YOU DONT NEED ANY EXTRA MARKS, YOU HAVE ALREADY PASSED MOBILE COMPUTING!!!!! ";
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
        String course_name = "MOBILE_COMPUTING";
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
        lab5.setText(marks[4]);
        lab6.setText(marks[5]);
        quick_lab1.setText(marks[6]);
        quick_lab2.setText(marks[7]);
        project.setText(marks[8]);
        test1.setText(marks[9]);
        tu4.setText(marks[10] + "%");
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
        if(!TextUtils.isEmpty(LAB5.getText().toString().trim())){
            button5.performClick();

        }
        if(!TextUtils.isEmpty(LAB6.getText().toString().trim())){
            button6.performClick();

        }
        if(!TextUtils.isEmpty(QUICK_LAB1.getText().toString().trim())){
            button7.performClick();

        }
        if(!TextUtils.isEmpty(QUICK_LAB2.getText().toString().trim())){
            button8.performClick();

        }
        if(!TextUtils.isEmpty(PROJECT.getText().toString().trim())){
            button9.performClick();

        }
        if(!TextUtils.isEmpty(TEST1.getText().toString().trim())){
            button10.performClick();

        }
        calculate.performClick();
    }

}
