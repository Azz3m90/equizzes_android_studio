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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class Delete_Quiz extends AppCompatActivity {
    Button delQuiz;
    EditText quizName;

    String quizNam="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__quiz);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.tv_clockdel);
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

        delQuiz=(Button)findViewById(R.id.btn_del_quizz);
        quizName=(EditText)findViewById(R.id.ed_quiz_del);




    }//end of onCreate
    public void backToDashBoard(View view){
        Intent dashBoardintent = new Intent(getApplicationContext(), Admin.class);
        startActivity(dashBoardintent);

        finish();
    }
    public void logout(View view){

        Intent logoutintent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(logoutintent);

        finish();
    }
    public void delTheQuiz(View view){
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
                URL url = new URL("http://192.168.1.107:8080/delQuizz.php");
                String urlParams = "quizName=" + quizName;

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
            if (s.equals("")) {
                s = "Quiz inserted successfully.";
            }
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();

        }
    }
}
