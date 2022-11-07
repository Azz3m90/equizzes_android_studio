package com.e_quizzes.azzam.e_multiquizzes;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Results extends AppCompatActivity {
    EditText quizName;
    String quizNam="";
    String state="";
    Button show;
    int numberOfUsers=0;
    ArrayList<String> userName = new ArrayList<String>();
    ArrayList<String> userID = new ArrayList<String>();

    ArrayList<String> QuizID = new ArrayList<String>();
    ArrayList<String> TotalMark = new ArrayList<String>();
    ArrayList<String> DateOfQuiz = new ArrayList<String>();
    ArrayList<String> TimeOfTheQuiz = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        show=(Button)findViewById(R.id.button2);


        quizName=(EditText)findViewById(R.id.ed_quiz_showResults);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.tv_clockshow);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();



    }//end of oncreate

    public void logout(View view){

        Intent logoutintent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(logoutintent);

        finish();
    }
    public void backToDashBoard(View view){
        Intent dashBoardintent = new Intent(getApplicationContext(), Admin.class);
        startActivity(dashBoardintent);

        finish();
    }
    public void showTheResults(View view){
        quizNam=quizName.getText().toString();


        BackGround b=new BackGround();
        b.execute(quizNam);

    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String quizName = params[0];


            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.107:8080/show_Results.php");
                String urlParams ="quizName=" + quizName;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err = null;
            String ss = s.substring(3);


            try {


                JSONArray jArray = (JSONArray) new JSONTokener(ss).nextValue();


                JSONObject response = jArray.getJSONObject(jArray.length()-1);
                String status = response.getString("status").toString();
                state=status;


                if (status.equals("success")) {

                    if ((jArray.length() > 2)) {


                        for (int jINDEX = 1; jINDEX < jArray.length()-1; jINDEX++) {
                            if (jArray.getJSONObject(jINDEX).has("TotalMarks")) {
                                numberOfUsers = numberOfUsers + 1;
                                TotalMark.add(jArray.getJSONObject(jINDEX).getString("TotalMarks").toString());
                                QuizID.add(jArray.getJSONObject(jINDEX).getString("quizid").toString());
                                DateOfQuiz.add(jArray.getJSONObject(jINDEX).getString("QDate"));
                                TimeOfTheQuiz.add(jArray.getJSONObject(jINDEX).getString("Time"));


                            } else{
                                userID.add(jArray.getJSONObject(jINDEX).getString("id"));
                                userName.add(jArray.getJSONObject(jINDEX).getString("username"));
                            }

                        }
                    }}
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
if(state.equals("success")){
                Intent result=new Intent(getApplicationContext(),show_the_users.class);


    result.putExtra("userName", userName);
    result.putExtra("total", TotalMark);
    result.putExtra("Qid", QuizID);
    result.putExtra("uID", userID);
    result.putExtra("dateOfExam", DateOfQuiz);
    result.putExtra("timeOfExam", TimeOfTheQuiz);
    result.putExtra("numberOfUsers",numberOfUsers);
    result.putExtra("status",1);

    startActivity(result);







}


        }

    }
}
