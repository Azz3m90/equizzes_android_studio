package com.e_quizzes.azzam.e_multiquizzes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class Admin extends AppCompatActivity {

    String un;
    String userID;
    TextView username;
    Button delQuiz;
Button addQuiz;
Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        username=(TextView)findViewById(R.id.tv_Welcomedmin) ;
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.tv_Clock_Admin);
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
        addQuiz=(Button)findViewById(R.id.btn_Add_Quizz);
        update=(Button)findViewById(R.id.btn_update_quizz);
        Bundle recivedData=getIntent().getExtras();


        un=recivedData.getString("username");
        userID=recivedData.getString("userID");

        username.setText("Welcome Admin :"+un);
        int st=recivedData.getInt("ql_status");


    }// end of onCreate
    public void logout(View view){

        Intent logoutintent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(logoutintent);

        finish();
    }
    public void AddQuiz(View view){

        Intent logoutintent = new Intent(getApplicationContext(), Add_Quiz.class);

        startActivity(logoutintent);


    }
    public void DelQuiz(View view){

        Intent delQuizz = new Intent(getApplicationContext(), Delete_Quiz.class);

        startActivity(delQuizz);


    }
    public void updateQuiz(View view){

        Intent delQuizz = new Intent(getApplicationContext(), UpdateQuizz.class);

        startActivity(delQuizz);


    }
    public void showTheResult(View view){

        Intent delQuizz = new Intent(getApplicationContext(), Results.class);

        startActivity(delQuizz);


    }
}
