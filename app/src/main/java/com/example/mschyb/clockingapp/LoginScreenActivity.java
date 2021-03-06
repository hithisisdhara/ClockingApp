package com.example.mschyb.clockingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginScreenActivity extends AppCompatActivity {

    public String login, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);



        if(SaveSharedPreference.getUserName(LoginScreenActivity.this).length() == 0)
        {}
        else
        {
            // SaveSharedPreference.removeUserName(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
        }

        Button loginButton = (Button) findViewById(R.id.loginButton);
        //set the onClick listener for the button
        loginButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   TextView loginError = (TextView) findViewById(R.id.loginErrorBox);
                   loginError.setText("Signing in...");

                   EditText loginET = (EditText) findViewById(R.id.editLogin);
                   EditText passwordET = (EditText) findViewById(R.id.editPassword);

                   login = loginET.getText().toString();
                   password = passwordET.getText().toString();

                   if (new Utilities().checkCredentials(login, password)) {
                       //NEED TO SET SHAREDPREFERENCES FOR USER'S NAME AND USER ID SOMEWHERE DURING LOGIN
                       // AFTER RETRIEVING THEM FROM THE DATABASE
                       loginError.setText("Success!");
                       Intent intent = new Intent(LoginScreenActivity.this, HomeScreenActivity.class);
                       startActivity(intent);
                   } else {
                       loginError.setText("Login or password is incorrect, please re-enter");
                   }
               }
           }
        );//end loginButton.setOnClickListener
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
