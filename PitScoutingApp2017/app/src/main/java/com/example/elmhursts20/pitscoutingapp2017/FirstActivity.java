package com.example.elmhursts20.pitscoutingapp2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void nextButton(View view) {
        EditText event = (EditText)findViewById(R.id.eventName);
        EditText scout = (EditText)findViewById(R.id.scoutName);
        EditText team = (EditText)findViewById(R.id.teamNumber);
        if (event.getText().toString().equals("") || scout.getText().toString().equals("") || Integer.parseInt(team.getText().toString()) == 0) {
            Toast.makeText(getApplicationContext(), "Type all fields in", Toast.LENGTH_LONG).show();
        } else {
            Main.infoStorage.setHeader(event.getText().toString(), scout.getText().toString(), Integer.parseInt(team.getText().toString()));
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
    }
}