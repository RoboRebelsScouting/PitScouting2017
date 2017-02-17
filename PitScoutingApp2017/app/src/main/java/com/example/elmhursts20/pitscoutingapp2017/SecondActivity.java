package com.example.elmhursts20.pitscoutingapp2017;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.R.attr.data;
import static com.example.elmhursts20.pitscoutingapp2017.Main.infoStorage;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void submit(View view) {
        EditText ballCap = (EditText) findViewById(R.id.ballCapacity);
        ToggleButton footprint = (ToggleButton) findViewById(R.id.footPrint);
        ToggleButton shootLow = (ToggleButton) findViewById(R.id.shootLow);
        ToggleButton shootHigh = (ToggleButton) findViewById(R.id.shootHigh);
        RadioButton frameSteel = (RadioButton) findViewById(R.id.steel);
        RadioButton frameAluminum = (RadioButton) findViewById(R.id.aluminum);
        ToggleButton gearPlayer = (ToggleButton) findViewById(R.id.playerGear);
        ToggleButton gearGround = (ToggleButton) findViewById(R.id.groundGear);
        ToggleButton ballPlayer = (ToggleButton) findViewById(R.id.playerBall);
        ToggleButton ballGround = (ToggleButton) findViewById(R.id.groundBall);

        if (ballCap.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Type in the ball capacity", Toast.LENGTH_LONG).show();
        }

        Main.infoStorage.ballCapacity = Integer.parseInt(ballCap.getText().toString());
        Main.infoStorage.tallFrame = footprint.isChecked();
        if (shootLow.isChecked() && shootHigh.isChecked()) {
            Main.infoStorage.shooting = InfoStorage.Shooting.both;
        } else if (shootLow.isChecked()) {
            Main.infoStorage.shooting = InfoStorage.Shooting.low;
        } else if (shootHigh.isChecked()) {
            Main.infoStorage.shooting = InfoStorage.Shooting.high;
        } else {
            Main.infoStorage.shooting = InfoStorage.Shooting.none;
        }
        if (frameSteel.isChecked()) {
            Main.infoStorage.frame = InfoStorage.Frame.steel;
        } else if (frameAluminum.isChecked()) {
            Main.infoStorage.frame = InfoStorage.Frame.aluminum;
        } else {
            Main.infoStorage.frame = InfoStorage.Frame.wood;
        }
        if (ballPlayer.isChecked() && ballGround.isChecked()) {
            Main.infoStorage.ball = InfoStorage.Ball.both;
        } else if (ballPlayer.isChecked()) {
            Main.infoStorage.ball = InfoStorage.Ball.player;
        } else if (ballGround.isChecked()) {
            Main.infoStorage.ball = InfoStorage.Ball.ground;
        } else {
            Main.infoStorage.ball = InfoStorage.Ball.neither;
        }
        if (gearPlayer.isChecked() && gearGround.isChecked()) {
            Main.infoStorage.gear = InfoStorage.Gear.both;
        } else if (gearPlayer.isChecked()) {
            Main.infoStorage.gear = InfoStorage.Gear.player;
        } else if (gearGround.isChecked()) {
            Main.infoStorage.gear = InfoStorage.Gear.ground;
        } else {
            Main.infoStorage.gear = InfoStorage.Gear.neither;
        }
        Main.infoStorage.csvCreate(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }
}
