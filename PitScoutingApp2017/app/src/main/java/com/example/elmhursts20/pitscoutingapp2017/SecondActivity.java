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

public class SecondActivity extends AppCompatActivity {
    public boolean saveFileOnly = false;
    public boolean useBluetoothActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void toPhoto(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }
    public void submit(View view) {
        EditText robotWeight = (EditText) findViewById(R.id.robotWeight);
        EditText ballCap = (EditText) findViewById(R.id.ballCapacity);
        EditText enterEmail = (EditText) findViewById(R.id.enterEmail);
        RadioButton nylon = (RadioButton) findViewById(R.id.nylon);
        RadioButton paracord = (RadioButton) findViewById(R.id.paracord);
        RadioButton first = (RadioButton) findViewById(R.id.first);
        RadioButton velcro = (RadioButton) findViewById(R.id.velcro);
        ToggleButton footprint = (ToggleButton) findViewById(R.id.footPrint);
        ToggleButton shootLow = (ToggleButton) findViewById(R.id.shootLow);
        ToggleButton shootHigh = (ToggleButton) findViewById(R.id.shootHigh);
        RadioButton frameSteel = (RadioButton) findViewById(R.id.steel);
        RadioButton frameAluminum = (RadioButton) findViewById(R.id.aluminum);
        RadioButton frameKitbot = (RadioButton) findViewById(R.id.kitbot);
        ToggleButton gearPlayer = (ToggleButton) findViewById(R.id.playerGear);
        ToggleButton gearGround = (ToggleButton) findViewById(R.id.groundGear);
        ToggleButton ballHopper = (ToggleButton) findViewById(R.id.hopperBall);
        ToggleButton ballGround = (ToggleButton) findViewById(R.id.groundBall);
        ToggleButton canClimb = (ToggleButton) findViewById(R.id.canClimb);

        if (enterEmail.getText().toString().equals("")){
            Main.infoStorage.email = "null";
        } else {
            Main.infoStorage.email = (enterEmail.getText().toString());
        }

        if (robotWeight.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Type in the robot weight", Toast.LENGTH_LONG).show();
        } else if (ballCap.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Type in the ball capacity", Toast.LENGTH_LONG).show();
        } else {

            Main.infoStorage.robotWeight = Integer.parseInt(robotWeight.getText().toString());
            Main.infoStorage.ballCapacity = Integer.parseInt(ballCap.getText().toString());
            Main.infoStorage.tallFrame = footprint.isChecked();
            Main.infoStorage.climb = canClimb.isChecked();
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
            } else if (frameKitbot.isChecked()) {
                Main.infoStorage.frame = InfoStorage.Frame.kitbot;
            } else {
                Main.infoStorage.frame = InfoStorage.Frame.wood;
            }
            if (velcro.isChecked()) {
                Main.infoStorage.ropeMaterial = InfoStorage.Rope.velcro;
            } else if (nylon.isChecked()) {
                Main.infoStorage.ropeMaterial = InfoStorage.Rope.nylon;
            } else if (paracord.isChecked()) {
                Main.infoStorage.ropeMaterial = InfoStorage.Rope.paracord;
            } else if (first.isChecked()){
                Main.infoStorage.ropeMaterial = InfoStorage.Rope.first;
            }
            if (ballHopper.isChecked() && ballGround.isChecked()) {
                Main.infoStorage.ball = InfoStorage.Ball.both;
            } else if (ballHopper.isChecked()) {
                Main.infoStorage.ball = InfoStorage.Ball.hopper;
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
            Main.infoStorage.csvCreate(this, saveFileOnly);
            if (saveFileOnly == true){
                startFirstActivity();
            }
        }
    }

    public void saveFile (View view) {
        saveFileOnly = true;
        this.submit(view);
    }

    public void sendFile (View view) {
        saveFileOnly = false;
        this.submit(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Main.infoStorage.photoIsSent) {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        } else {
            Main.infoStorage.photoIsSent = true;
        }
    }
    public void startFirstActivity() {
        Intent intent = new Intent(this, FirstActivity.class) ;
        startActivity(intent);
    }
}
