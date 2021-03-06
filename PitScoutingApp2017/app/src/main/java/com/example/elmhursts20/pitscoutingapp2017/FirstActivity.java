package com.example.elmhursts20.pitscoutingapp2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        if (Main.infoStorage == null) {
            Main.infoStorage = new InfoStorage();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
        TextView eventNameText = (TextView) findViewById(R.id.eventName);
        EditText scoutName = (EditText) findViewById(R.id.scoutName);
        if (!Main.infoStorage.scout.equals("")) {
            scoutName.setText(Main.infoStorage.scout);
        }
        try {
            long currentTimeInMillis = System.currentTimeMillis();

            if ((currentTimeInMillis >= sdf.parse("Feb 17 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Feb 19 2017").getTime() )) {
                eventNameText.setText("Week_0");
            } else if ((currentTimeInMillis >= sdf.parse("Feb 19 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 12 2017").getTime() )) {
                eventNameText.setText("WPI");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 12 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 27 2017").getTime() )) {
                eventNameText.setText("Bryant");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 27 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Apr 9 2017").getTime() )) {
                eventNameText.setText("UNH");
            }  else {
                eventNameText.setText("Worlds");
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    public void nextButton(View view) {
        TextView event = (TextView) findViewById(R.id.eventName);
        EditText scout = (EditText) findViewById(R.id.scoutName);
        EditText team = (EditText) findViewById(R.id.teamNumber);
        if (event.getText().toString().equals("") || scout.getText().toString().equals("") || team.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Type all fields in", Toast.LENGTH_LONG).show();
        } else {
            Main.infoStorage.setHeader(event.getText().toString(), scout.getText().toString(), Integer.parseInt(team.getText().toString()));
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);

        }
    }
}