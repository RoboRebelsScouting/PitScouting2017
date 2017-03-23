package com.example.elmhursts20.pitscoutingapp2017;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by saelm on 2/15/2017
 */

public class InfoStorage {
    public enum Frame {
        kitbot,
        steel,
        wood,
        aluminum
    }
    public enum Rope {
        velcro,
        nylon,
        paracord,
        first
    }
    public enum Shooting {
        high,
        low,
        none,
        both
    }
    public enum Ball {
        ground,
        hopper,
        neither,
        both
    }
    public enum Gear {
        ground,
        player,
        neither,
        both
    }

    public String scout;
    public int team;
    private String event;
    public boolean climb;
    public Frame frame;
    public Shooting shooting;
    public Ball ball;
    public Gear gear;
    public Rope ropeMaterial;
    public int robotWeight;
    public int ballCapacity;
    public boolean tallFrame;
    public String email;

    public boolean photoIsSent;

    public InfoStorage() {
        scout = "";
        team = 0;
        event = "";
        frame = null;
        photoIsSent = false;
        email = "";
    }

    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), albumName);
        if (!file.mkdirs()) {
            Log.e("ERROR", "Directory NOT Created");
        }
        return file;
    }

    public void setHeader(String event, String scout, int team) {
        this.event = event;
        this.scout = scout;
        this.team = team;
    }

    public void csvCreate(Activity theActivity) {
        String fileName= this.event + "-" + this.team + "-" + this.scout + "-pit.csv";

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        File directory = getAlbumStorageDir("/FRC2017");
        File file = new File(directory,fileName);
        File photo = new File(mediaStorageDir.getPath() + File.separator +
                "Bot_" + Main.infoStorage.team + ".jpg");
            try {
            if(photo.exists()) {
                System.out.println("photo file name is: " + Uri.fromFile(photo));
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photo));
                theActivity.startActivityForResult(intent, 0);
            } else {
                photoIsSent = true;
            }

            FileWriter writer = new FileWriter(file);
            writer.write(this.event + "," + this.team + "," + this.scout + "," + this.ballCapacity + ","
                    + this.tallFrame + "," + this.shooting.toString() + "," + this.gear.toString()
                    + "," + this.ball.toString() + "," + this.ropeMaterial + "," + this.frame.toString() +
                    "," + this.climb + "," + this.robotWeight + "," + this.email);
            writer.close();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            theActivity.startActivityForResult(intent,0);
        } catch (IOException e) {
            Log.e("ERROR","File NOT Created",e);
        }
    }
}