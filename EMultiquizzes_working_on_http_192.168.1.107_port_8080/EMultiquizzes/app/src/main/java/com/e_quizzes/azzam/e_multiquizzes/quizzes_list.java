package com.e_quizzes.azzam.e_multiquizzes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class quizzes_list extends AppCompatActivity {

    ArrayList<String> questionArray = new ArrayList<String>();
    ArrayList<String> questionIDArray = new ArrayList<String>();
    ArrayList<String> options = new ArrayList<String>();
    ArrayList<String> optionsID = new ArrayList<String>();
    ArrayList<String> marks = new ArrayList<String>();

    ArrayList<String> nOfChoices = new ArrayList<String>();
    String state="";
    String userID;
    String quizID="";
    String un="";
    int numberOfChoices=0;
    int numberOfQuestions=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes_list);


        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.tv_time);
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


        TextView username=(TextView)findViewById(R.id.tv_logState);
        TextView numberOfQuiz=(TextView)findViewById(R.id.tv_quizList);





        Bundle recivedData=getIntent().getExtras();


        un=recivedData.getString("username");
        userID=recivedData.getString("userID");

        username.setText("Welcome :"+un);
        int st=recivedData.getInt("ql_status");


        if(st==1){
            ArrayList<String> quizzlistArray = (ArrayList<String>) recivedData.getStringArrayList("quizz_list");
            ArrayList<String> idlistArray = (ArrayList<String>) recivedData.getStringArrayList("id_list");
            ArrayList<String> stateOftheQuiz = (ArrayList<String>) recivedData.getStringArrayList("stateOfQuizz");
            ArrayList<String> numberOfTheUsers = (ArrayList<String>) recivedData.getStringArrayList("numberOfTheUsers");
            int j=quizzlistArray.size();
            final String[] getParamQuizz=new String[j];
            final String[] getParamID=new String[j];
            final String[] getParamState=new String[j];
            final String[] getParamNumbers=new String[j];
            for(int i=0;i<quizzlistArray.size();i++){
                getParamQuizz[i]= quizzlistArray.get(i).toString();
                getParamID[i]= idlistArray.get(i).toString();
                getParamState[i]=stateOftheQuiz.get(i).toString();
                getParamNumbers[i]=numberOfTheUsers.get(i).toString();
            }

            final int index=getParamQuizz.length;
            String quizNumber="there :("+String.valueOf(index)+")  quizzes";





            numberOfQuiz.setText(quizNumber);

            final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);

            // create the layout params that will be used to define how your
            // button will be displayed
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //Create four
            for( j=0;j<getParamQuizz.length;j++)
            {
                // Create LinearLayout
                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                // Create TextView
                TextView quizz = new TextView(this);
                quizz.setText(" Quizz :"+(j+1)+"    ");
                ll.addView(quizz);

                // Create TextView
                TextView quizzid = new TextView(this);
                quizzid.setText("Quizz ID:"+getParamID[j]+"     ");
                ll.addView(quizzid);

                // Create Button
                final Button btn = new Button(this);
                // Give button an ID
                btn.setId(Integer.parseInt(getParamID[j]));
                btn.setText(getParamQuizz[j]);
                // set the layoutParams on the button
                btn.setLayoutParams(params);

                final int ind = j;
                final boolean quizState=getParamState[j].toString().equals("True");

                // Set click listener for button
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Log.i("TAG", "index :" + ind);
                        if(quizState){
                            BackGround b = new BackGround();

                            b.execute(getParamID[ind].toString());

                            Toast.makeText(getApplicationContext(),
                                    " ("+getParamQuizz[ind]+")Quizz has been Clicked  ",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "You have made "+getParamQuizz[ind]+" before  ",
                                    Toast.LENGTH_LONG).show();
                        }



                    }
                });

                //Add button to LinearLayout
                ll.addView(btn);
                //Add button to LinearLayout defined in XML
                lm.addView(ll);
                // Create TextView
                TextView numberOfTheUser = new TextView(this);
                numberOfTheUser.setText("number of the users:"+getParamNumbers[j]+"     ");
                ll.addView(numberOfTheUser);
            }



        }
        else{
            Toast.makeText(getApplicationContext(),"there is no quizzez now",Toast.LENGTH_LONG).show();

        }


    }
    //end of oncreate class
    public void logout(View view){

        Intent logoutintent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(logoutintent);

        finish();
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String quizID = params[0];


            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.107:8080/question.php");
                String urlParams = "quizID=" + quizID ;

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
            String ss = s.substring(6);


            try {

                JSONArray jArray = (JSONArray) new JSONTokener(ss).nextValue();
                // once you get the array, you may check items like
                JSONObject response = jArray.getJSONObject(0);
                String status = response.getString("status");


                state = response.getString("status");
                if (status.equals("success")) {
                    if (jArray.length() > 1) {
                        quizID=jArray.getJSONObject(1).getString("quiztid");


                        for(int jINDEX=1;jINDEX<jArray.length();jINDEX++){
                            if(jArray.getJSONObject(jINDEX).has("quiztid")){
                                numberOfQuestions=numberOfQuestions+1;

                                questionArray.add(jArray.getJSONObject(jINDEX).getString("Questions"));
                                questionIDArray.add(jArray.getJSONObject(jINDEX).getString("id"));

                              if(numberOfChoices!=0) {
                                  nOfChoices.add(Integer.toString(numberOfChoices));

                                  numberOfChoices = 0;
                              }



                            }else
                            {
                                options.add(jArray.getJSONObject(jINDEX).getString("choices"));
                                marks.add(jArray.getJSONObject(jINDEX).getString("mark"));
                                optionsID.add(jArray.getJSONObject(jINDEX).getString("id"));

                                numberOfChoices=numberOfChoices+1;

                            }}
                            if(numberOfChoices!=0) {
                                nOfChoices.add(Integer.toString(numberOfChoices));

                                numberOfChoices = 0;
                            }





                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "it seems there is a problem .....", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }





            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }


            Intent data = new Intent(getApplicationContext(), start_quizzes.class);


            if (questionArray.size() > 0) {
                data.putExtra("quizID", quizID);
                data.putExtra("username", un);
                data.putExtra("userID", userID);

                data.putExtra("question_list", questionArray);
                data.putExtra("questionID", questionIDArray);
                data.putExtra("optionID", optionsID);


                data.putExtra("choices", options);
                data.putExtra("marks", marks);



                data.putExtra("number_of_choices",nOfChoices);
                data.putExtra("q_status", 1);

                data.putExtra("numberOfQuestions",numberOfQuestions);

                startActivity(data);


            }
            else{

              Intent back=new Intent(getApplicationContext(),MainActivity.class);

                startActivity(back);
                finish();
                Toast.makeText(getApplicationContext(),"It seems no questions for that quizz",Toast.LENGTH_LONG).show();

        } }

    }


}
