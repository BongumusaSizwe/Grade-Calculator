package com.example.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Scorpions on 2019/04/19.
  This class is for background activities such.
 */

public class Backgroundworker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;//to show login result
    Backgroundworker (Context ctx){
        context=ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        //here we want to perfom our post operation then validate email and password
        String type= params[0];
        String login_url = "http://lamp.ms.wits.ac.za/~s1682836/login.php";
        String register_url = "http://lamp.ms.wits.ac.za/~s1682836/register.php";
        String MarkAdder = "http://lamp.ms.wits.ac.za/~s1682836/mark_adder.php";
        String CourseAdder = "http://lamp.ms.wits.ac.za/~s1682836/Course_Adder.php";
        String course_reloader = "http://lamp.ms.wits.ac.za/~s1682836/Course_reloader.php";

        if(type.equals("login")){
            //connect to the login.php
            try {
                String student_id= params[1];
                String password= params[2];

                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=  (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                outputStream.close();
                //reading response from the post request

                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                //reading our response and reading it one by one
                String result="";
                String line="";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //now we return the result we got
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(type.equals("register")){
            //connect to the register.php
            try {
                String email= params[1];
                String password= params[2];
                String name_surname= params[3];
                String student_number= params[4];
                String faculty= params[5];
                String Year_of_study= params[6];

                URL url=new URL(register_url);
                HttpURLConnection httpURLConnection=  (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                + URLEncoder.encode("name_surname","UTF-8")+"="+URLEncoder.encode(name_surname,"UTF-8")+"&"
                + URLEncoder.encode("student_number","UTF-8")+"="+URLEncoder.encode(student_number,"UTF-8")+"&"
                + URLEncoder.encode("faculty","UTF-8")+"="+URLEncoder.encode(faculty,"UTF-8")+"&"
                + URLEncoder.encode("year","UTF-8")+"="+URLEncoder.encode(Year_of_study,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                outputStream.close();
                //reading response from the post request

                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                //reading our response and reading it one by one
                String result="";
                String line="";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //now we return the result we got
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //adding the marks to the database, which will later retrieve...
        else if(type.equals("add")){
            try {
                String mark = params[1];
                String student_id = params[2];
                String AssessmentType = params[3];
                String Course_Name = params[4];
                URL url = new URL(MarkAdder);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("mark","UTF-8")+"="+URLEncoder.encode(mark,"UTF-8")+"&"
                        +URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8")+"&"
                        +URLEncoder.encode("student_id","UTF-8")+"&"
                        + URLEncoder.encode("ASSESSMENT_TYPE","UTF-8")+"="+URLEncoder.encode(AssessmentType,"UTF-8")+"&"
                        + URLEncoder.encode("COURSE_NAME","UTF-8")+"="+URLEncoder.encode(Course_Name,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                outputStream.close();
                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String result="";
                String line="";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //now we return the result we got
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
       alertDialog=new AlertDialog.Builder(context).create();
       alertDialog.setTitle("Proccess Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("login successful")){
            Intent i =new Intent(context,MyCourses.class);//if login successful go to another activity
            context.startActivity(i);
        }
        else if(result.equals("Registration successful")){
            Intent i =new Intent(context,selected_c.class);//if login successful go to another activity
            context.startActivity(i);
        }

        else {

            alertDialog.setMessage(result);
            alertDialog.show();//shows response of server
        }



    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
