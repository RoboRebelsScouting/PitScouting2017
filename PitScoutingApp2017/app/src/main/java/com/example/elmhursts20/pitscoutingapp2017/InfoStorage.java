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
        steel,
        wood,
        aluminum
    }
    public enum Shooting {
        high,
        low,
        none,
        both
    }
    public enum Ball {
        ground,
        player,
        neither,
        both
    }
    public enum Gear {
        ground,
        player,
        neither,
        both
    }

    private String scout;
    public int team;
    private String event;
    public Frame frame;
    public Shooting shooting;
    public Ball ball;
    public Gear gear;
    public boolean customRope;
    public int ballCapacity;
    public boolean tallFrame;

    public InfoStorage() {
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
        String fileName= this.event + "," + this.team + "," + this.scout + ".csv";

        File directory = getAlbumStorageDir("/FRC2017");
        File file = new File(directory,fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(this.event + "," + this.team + "," + this.scout + "," + this.ballCapacity + ","
                    + this.tallFrame + "," + this.shooting.toString() + "," + this.gear.toString()
                    + "," + this.ball.toString() + "," + this.customRope + "," + this.frame.toString() + "\n");
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