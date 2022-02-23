package com.example.myapp;

/**
 * Created by Scorpions on 2019/05/20.
 */

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.TextView;

        import java.util.ArrayList;

        import static com.example.myapp.MainActivity.student_id;

public class Remove_Courses  extends AppCompatActivity {
    private CheckBox p, m11, m12, m13, c1, c2, cam11;
    private TextView text;
    private Button xp;
    ArrayList<String> arr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__courses);

        // text = (TextView) findViewById(R.id.textView2);
        p = (CheckBox) findViewById(R.id.c1);
        m11 = (CheckBox) findViewById(R.id.checkBox2);
        m12 = (CheckBox) findViewById(R.id.checkBox4);
        m13 = (CheckBox) findViewById(R.id.checkBox5);
        c1 = (CheckBox) findViewById(R.id.checkBox6);
        c2 = (CheckBox) findViewById(R.id.checkBox7);
        cam11 = (CheckBox) findViewById(R.id.checkBox8);


    }


    //Short cut back home after adding courses.
    public void goHome(View view){
        startActivity(new Intent(this,MyCourses.class));
    }

    //this removes your courses in the database;
    public void remove_courses(View view){
        String type = "Remove_Courses";

        if (p.isChecked()) {
            arr.add("PHY2");
        }
        if (m12.isChecked()) {
            arr.add("LINEAR_ALGEBRA");
        }

        if(c1.isChecked()){
            arr.add("MOBILE_COMPUTING");

        }
        if(c2.isChecked()){
            arr.add("DBF");

        }
        if(cam11.isChecked()){
            arr.add("CAM2007");
        }
        if(m13.isChecked()){
            arr.add("TAM");

        }
        if(m11.isChecked()){
            arr.add("MULTIVARIABLE_CALCULUS");
        }


        for(int i = 0;i<arr.size();++i){
            String course = new String(arr.get(i));

            Backgroundworker2 backgroundworker2 = new Backgroundworker2(this) {
                @Override
                protected void onPostExecute(String result) {
                    //we do nothing
                }
            };
            backgroundworker2.execute(type,course, student_id);

        }
        String size = Integer.toString(arr.size());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Number of courses added");
        builder1.setMessage(size + " COURSES REMOVED");
        builder1.show();

        //clearing our list just for convenience
        arr.clear();
    }

    //This will go to the updated screen if the user clicks back.
    public void onBackPressed(){
        startActivity(new Intent(this,MyCourses.class));
    }
}

