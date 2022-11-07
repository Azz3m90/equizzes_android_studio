package com.e_quizzes.azzam.e_multiquizzes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class show_the_users extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_the_users);


TextView numberOfUsers=(TextView)findViewById(R.id.tv_number);



        Bundle recivedData = getIntent().getExtras();
        int st = recivedData.getInt("status");
        int nummberOfUser=0;
        nummberOfUser=recivedData.getInt("numberOfUsers");
        numberOfUsers.setText("The number of user of that quiz is :"+Integer.toString(nummberOfUser));
        if (st == 1) {
            ArrayList<String> userID = (ArrayList<String>) recivedData.getStringArrayList("uID");
            ArrayList<String> QuizID = (ArrayList<String>) recivedData.getStringArrayList("Qid");
            ArrayList<String> TotalMarks = (ArrayList<String>) recivedData.getStringArrayList("total");
            ArrayList<String> dateOfExam = (ArrayList<String>) recivedData.getStringArrayList("dateOfExam");
            ArrayList<String> timeOfExam = (ArrayList<String>) recivedData.getStringArrayList("timeOfExam");
            ArrayList<String> userName = (ArrayList<String>) recivedData.getStringArrayList("userName");




            final String[] getParamUserID = new String[userID.size()];

            final String[] getParamQuizID = new String[userID.size()];
            final String[] getParamTotalMarks = new String[userID.size()];
            final String[] getParamDate = new String[userID.size()];

            final String[] getParamTime = new String[userID.size()];
            final String[] getParamUserName = new String[userID.size()];


            for (int i = 0; i <userID.size(); i++) {
                getParamDate[i]=dateOfExam.get(i).toString();
                getParamUserID[i]=userID.get(i).toString();
                getParamQuizID[i]=QuizID.get(i).toString();
                getParamTotalMarks[i]=TotalMarks.get(i).toString();
                getParamTime[i]=timeOfExam.get(i).toString();
                getParamUserName[i]=userName.get(i).toString();

            }



            final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);

            // create the layout params that will be used to define how your
            // button will be displayed
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //Create four
            for( int j=0;j<getParamDate.length;j++)
            {
                // Create LinearLayout
                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                // Create TextView
                TextView username = new TextView(this);
                username.setText("   "+getParamUserName[j]+"            ");
                ll.addView(username);

                // Create TextView
                TextView quizz = new TextView(this);
                quizz.setText("   "+getParamQuizID[j]+"            ");
                ll.addView(quizz);

                // Create TextView
                TextView totlMark = new TextView(this);
                totlMark.setText("   "+getParamTotalMarks[j]+"            ");
                ll.addView(totlMark);


                // Create TextView
                TextView quizzid = new TextView(this);
                quizzid.setText("   "+getParamDate[j]+"            ");
                ll.addView(quizzid);

                lm.addView(ll);


            }



 }



    }//end od on create
}
