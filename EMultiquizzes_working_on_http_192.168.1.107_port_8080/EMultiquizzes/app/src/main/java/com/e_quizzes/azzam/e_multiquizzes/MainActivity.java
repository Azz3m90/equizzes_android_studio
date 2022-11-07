package com.e_quizzes.azzam.e_multiquizzes;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.String;

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
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText name, password;
    String Name, Password,UserName;
    Context ctx=this;
    String NAME="";
    String state="";
    String user_id="";
    String isAdmin="";

    ArrayList<String> quizzArray = new ArrayList<String>();
    ArrayList<String> idArray = new ArrayList<String>();
    ArrayList<String> stateOfQuiz = new ArrayList<String>();
    ArrayList<String> numberOfUsers = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.et_userLogin);
        password = (EditText) findViewById(R.id.et_passLogin);



    }


    public void main_login(View v){
        Name = name.getText().toString();
        Password = password.getText().toString();

        BackGround b = new BackGround();
        b.execute(Name, Password);


    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.107:8080/login.php");
                String urlParams = "username=" + name + "&password=" + password;

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


                if (status.equals("success")) {

                    JSONObject re = jArray.getJSONObject(1);
                    NAME = re.getString("username");
                    user_id = re.getString("id");
                    state = response.getString("status");
                    isAdmin = re.getString("isAdmin");


                    if ((jArray.length() > 2)) {


                        for (int i = 2; i < jArray.length(); i++) {
                            if(jArray.getJSONObject(i).has("quiztype")){
                                quizzArray.add(jArray.getJSONObject(i).getString("quiztype"));
                                idArray.add(jArray.getJSONObject(i).getString("id"));
                            }
                            else{
                                stateOfQuiz.add(jArray.getJSONObject(i).getString("state"));
                                numberOfUsers.add(jArray.getJSONObject(i).getString("numberOfUsers"));



                            }



                        }


                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Error : wrong username or password , please retry again", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }


            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }


            if (state.equals("success")) {
                if (isAdmin.equals("False")) {
                    Toast.makeText(getApplicationContext(), "Welcome  :"+NAME+"Panel", Toast.LENGTH_SHORT).show();
                    Intent x = new Intent(getApplicationContext(), quizzes_list.class);


                    x.putExtra("username", NAME);
                    x.putExtra("userID", user_id);
                    if (quizzArray.size() > 0) {
                        x.putExtra("quizz_list", quizzArray);
                        x.putExtra("stateOfQuizz", stateOfQuiz);
                        x.putExtra("numberOfTheUsers", numberOfUsers);
                        x.putExtra("id_list", idArray);

                        x.putExtra("ql_status", 1);

                    }
                    startActivity(x);

                } else {

                    Toast.makeText(getApplicationContext(), "Admin :"+NAME+" Data", Toast.LENGTH_SHORT).show();
                    Intent data = new Intent(getApplicationContext(), Admin.class);
                    data.putExtra("username", NAME);
                    data.putExtra("userID", user_id);

                    startActivity(data);
                }


            }


        }

    }

    public void  goReg(View view){
        Intent Register=new Intent(getBaseContext(),register.class);
        startActivity(Register);
    }
}


