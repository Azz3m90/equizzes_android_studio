package com.e_quizzes.azzam.e_multiquizzes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Calendar;

import static android.media.CamcorderProfile.get;
import static java.lang.Integer.parseInt;

public class start_quizzes extends AppCompatActivity {
    ArrayList<String> questionArray = new ArrayList<String>();
    ArrayList<String> questionIDArray = new ArrayList<String>();
    ArrayList<String> OptionsIDArray = new ArrayList<String>();

    ArrayList<String> option = new ArrayList<String>();
    ArrayList<String> mark = new ArrayList<String>();

    ArrayList<String> numberOfChoice = new ArrayList<String>();

    ArrayList<String> grades = new ArrayList<String>(); // ver toooooooooo important
int opIndex=0;
    String userID;
    String quizID = "";
    String subQuizID = "";
    String un = "";
    String grade;
    int totalMark = 0;
    int index = 0;
String QuizID="";
    TextView question;
    TextView overall;
    Button confirmedNext ;
    Button returnToquizzList;
    RadioGroup rbGroup;
    int numberOfQuest=0;
    String answer;
    int indexOfanser=1;
    int tracking=0;
    int numberOfChoices=0;
    String timeString="";
    String dateString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quizzes);

        question=(TextView) findViewById(R.id.tv_question);
        rbGroup=(RadioGroup) findViewById(R.id.rg_choices);
        overall=(TextView)findViewById(R.id.tv_overall_progress2);
       confirmedNext=(Button) findViewById(R.id.btn_confirm2);
       returnToquizzList=(Button)findViewById(R.id.btn_back_question);
        returnToquizzList.setEnabled(true);
        question.setText("Welcome to our quizz , Once You Hit submit button you should take your Exam then you are good to go");

//////////////
///////////////


        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeonly = new SimpleDateFormat("hh:mm");
         dateString = sdf.format(date);
         timeString = timeonly.format(date);




 ///////////////

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.tv_timeAndDate);
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












        Bundle recivedData = getIntent().getExtras();
        int st = recivedData.getInt("q_status");
        un = recivedData.getString("username");
        userID = recivedData.getString("userID");
        QuizID=recivedData.getString("quizID");


        if (st == 1) {
            ArrayList<String> question_listArray = (ArrayList<String>) recivedData.getStringArrayList("question_list");
            ArrayList<String> options = (ArrayList<String>) recivedData.getStringArrayList("choices");
            ArrayList<String> marks = (ArrayList<String>) recivedData.getStringArrayList("marks");
            ArrayList<String> opID = (ArrayList<String>) recivedData.getStringArrayList("optionID");


            ArrayList<String> questionIDArray = (ArrayList<String>) recivedData.getStringArrayList("questionID");
            ArrayList<String> nOChoicesArray = (ArrayList<String>) recivedData.getStringArrayList("number_of_choices");

            final int index = 0;
            int numberOfQuestions = recivedData.getInt("numberOfQuestions");
            numberOfQuest=numberOfQuestions;

            final String[] getParamQuestion = new String[question_listArray.size()+1];

            final String[] getParamquestionID = new String[question_listArray.size()+1];
            final String[] getParamNOChoices = new String[question_listArray.size()+1];

            getParamQuestion[0] = "be prepared the quizz qill begin after hitting submitting button";
            getParamNOChoices[0]="number of Choices";
            questionArray.add("be prepared the quizz qill begin after hitting submitting button");
            numberOfChoice.add("number of Choices");
            option.add("option for the question");
            mark.add("0");

            // final String[] getParamMark=new String[question_listArray.size()];
            //final String[] getParamChoices;
            for (int i = 1; i <=question_listArray.size(); i++) {
                getParamQuestion[i] = question_listArray.get(i-1).toString();
                getParamNOChoices[i]=nOChoicesArray.get(i-1).toString();
                questionArray.add(question_listArray.get(i-1).toString());
                numberOfChoice.add(nOChoicesArray.get(i-1).toString());








                getParamquestionID[i] = questionIDArray.get(i-1).toString();
                //  getParamMark[i]=marksArray.get(i).toString();

            }

            questionArray.add("Question finished click submit to get you answer");
           int temp=opID.size();

for(int i=1;i<=options.size();i++){
    option.add(options.get(i-1).toString());
    mark.add(marks.get(i-1).toString());

}




        }

    }//end_of onCreate

    public void logout(View view){

        Intent logoutintent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(logoutintent);

        finish();
    }
    public void returnToQuizList(View view){

        Intent logoutintent = new Intent(getBaseContext(), quizzes_list.class);
        startActivity(logoutintent);

        finish();
    }

    public void returnToQuizzList(){
        Intent returnQuizzList=new Intent(getBaseContext(),quizzes_list.class);
        startActivity(returnQuizzList);

    }
    public void onClick(View view){
        returnToquizzList.setEnabled(false);
        index++;
        if(index<=numberOfQuest){

            if(index<numberOfQuest){ confirmedNext.setText("Next");    }
            else{ confirmedNext.setText("Submit");  }

            if (index == 0) {question.setText( questionArray.get(index).toString());
            }
            else{ question.setText("question (" + (index) + ")  : " + questionArray.get(index).toString());
                String overall_Progress=Integer.toString(index)+"/"+Integer.toString(numberOfQuest);
                overall.setText(overall_Progress);  }

            LinearLayout linearLayout = findViewById(R.id.ll_questions);

            rbGroup.removeAllViews();
     if((index!=0)) {
    confirmedNext.setEnabled(false);
    // Create RadioButton Dynamicall
        numberOfChoices=Integer.parseInt(numberOfChoice.get(index).toString()) ;

    for (int i=1; i <= numberOfChoices; i++) {
        tracking++;

        RadioButton radioButton1 = new RadioButton(getApplicationContext());
        radioButton1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        radioButton1.setText(option.get(tracking).toString());

        radioButton1.setId(tracking + 1);


        rbGroup.addView(radioButton1);

    }
    rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final RadioGroup rbGroup, int checkedId) {


            RadioButton radioButton = rbGroup.findViewById(checkedId);
            checkedId = rbGroup.indexOfChild(radioButton);
            confirmedNext.setEnabled(true);

            grade = radioButton.getText().toString();
            answer = mark.get(option.indexOf(grade));

            if (checkedId != -1) {
                confirmedNext.setEnabled(true);


                grade = radioButton.getText().toString();
                answer = mark.get(option.indexOf(grade));

            } else {


                confirmedNext.setEnabled(false);

            }
        }
    });



}



            if (rbGroup.getCheckedRadioButtonId() != -1) {
                confirmedNext.setEnabled(false);
                grades.add(answer);
            }

        }
        else{
            grades.add(answer);
           for(int i=0;i<grades.size();i++){
               totalMark=totalMark+Integer.parseInt(grades.get(i));

           }
            confirmedNext.setEnabled(false);
            returnToquizzList.setEnabled(true);
            Toast.makeText(getApplicationContext(),"Quizz finished",Toast.LENGTH_LONG).show();
            rbGroup.removeAllViews();

            if(totalMark<60){
                String message="our best regrads you failed in  the Exam ......  :-(\n "+ "Your result is : ( "+Integer.toString(totalMark)+" )";
                question.setText(message);

            }else if(totalMark<80) {
                String message="God job you passed in  the Exam ...... :-U\n "+ "Your result is : ( "+Integer.toString(totalMark)+" )";
                question.setText(message);


            }else if(totalMark<95){
                String message="Greate work !!!  ..... :-|\n "+ "Your result is : ( "+Integer.toString(totalMark)+" )";
                question.setText(message);


            }else if(totalMark<99){
                String message="Excellent work !!! you were brillient  ...... :)\n "+ "Your result is : ( "+Integer.toString(totalMark)+" )";
                question.setText(message);
            }else{
                String message=" all the answers is true you must be a teacher ......  :-0\n "+ "Your result is : ( "+Integer.toString(totalMark)+" )";
                question.setText(message);
            }
String temp=Integer.toString(totalMark);
            BackGround b = new BackGround();
            b.execute(userID,QuizID,temp,dateString,timeString);



        }


    }


    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String userID = params[0];
            String QuizID = params[1];
            String TotalMark = params[2];
            String QDate=params[3];
            String Time=params[4];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.107:8080/posting_the_resulting.php");
                String urlParams = "userid=" + userID + "&quizid=" + QuizID + "&totalMarks=" + TotalMark+"&QDate="+QDate+"&Time="+Time;

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
                s = "Data saved successfully.";
            }
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();

        }
    }


}