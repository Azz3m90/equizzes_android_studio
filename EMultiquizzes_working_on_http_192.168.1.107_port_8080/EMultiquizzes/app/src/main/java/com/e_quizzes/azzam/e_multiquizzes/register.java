package com.e_quizzes.azzam.e_multiquizzes;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class register extends AppCompatActivity {
    EditText userName,Password,passwordConfirm,telephone;
    String Username,PWD,pwdCofirm,telNumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnLogReturn =(Button)findViewById(R.id.btnLogReturn);
        userName=(EditText)findViewById(R.id.et_username);
        telephone=(EditText)findViewById(R.id.et_tel);
        Password=(EditText)findViewById(R.id.et_pass);
        passwordConfirm=(EditText)findViewById(R.id.et_passValid);
    }
    public void register_register(View v){
        Username = userName.getText().toString();
        PWD = Password.getText().toString();
        telNumb = telephone.getText().toString();
        pwdCofirm=passwordConfirm.getText().toString();
        if(pwdCofirm.equals(PWD)){
            BackGround b = new BackGround();
            b.execute(Username,PWD,telNumb);}
        else{
            Toast.makeText(this,"Password mismatched ", Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,register.class);
            startActivity(i);
        }

    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String tel = params[2];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.107:8080/register.php");
                String urlParams = "username=" + username + "&password=" + password + "&tel=" + tel;

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
    public void loginReturn(View view){

        Intent loginReturned=new Intent(this,MainActivity.class);
        startActivity(loginReturned);

    }

}
