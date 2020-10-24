package me.nolan.meadapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("logRoll");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rollDice(View v) {
        int finalVal = 0;
        int rollTime = 0;
        //Die faces
        int[] firstRoll = {1, 2, 2, 3, 3, 3};
        int[] secondRoll = {0, 0, 1, 2, 3, 4};
        int[] thirdRoll = {0, 1, 2, 3, 4, 5};
        ImageView die1 = (ImageView) findViewById(R.id.die1);
        ImageView die2 = (ImageView) findViewById(R.id.die2);
        ImageView die3 = (ImageView) findViewById(R.id.die3);
        int dieRandom1 = getRandom(firstRoll);
        int dieRandom2 = getRandom(secondRoll);
        int dieRandom3 = getRandom(thirdRoll);
        finalVal = finalVal + dieRandom2;
        finalVal = finalVal + dieRandom3;

        if (dieRandom1 == 3) {
            finalVal += 30;
            die1.setImageResource(R.drawable.id30);
        }
        if (dieRandom1 == 2) {
            finalVal += 20;
            die1.setImageResource(R.drawable.id20);
        }
        if (dieRandom1 == 1) {
            finalVal += 10;
            die1.setImageResource(R.drawable.id10);
        }

        if (dieRandom2 == 0)
            die2.setImageResource(R.drawable.id0);
        if (dieRandom2 == 1)
            die2.setImageResource(R.drawable.id1);
        if (dieRandom2 == 2)
            die2.setImageResource(R.drawable.id2);
        if (dieRandom2 == 3)
            die2.setImageResource(R.drawable.id3);
        if (dieRandom2 == 4)
            die2.setImageResource(R.drawable.id4);

        if (dieRandom3 == 0)
            die3.setImageResource(R.drawable.id0);
        if (dieRandom3 == 1)
            die3.setImageResource(R.drawable.id1);
        if (dieRandom3 == 2)
            die3.setImageResource(R.drawable.id2);
        if (dieRandom3 == 3)
            die3.setImageResource(R.drawable.id3);
        if (dieRandom3 == 4)
            die3.setImageResource(R.drawable.id4);
        if (dieRandom3 == 4)
            die3.setImageResource(R.drawable.id5);

        Log.i("info", "pressed");

        String k = myRef.push().getKey();
        //Convert to string for simplicity
        myRef.child(k).setValue(""+finalVal);
        Log.i("info", "Die Random 1: " + dieRandom1);
        Log.i("info", "Die Random 2:" + dieRandom2);
        Log.i("info", "Die random 3: " + dieRandom3);
        Log.i("info", "Final: " + finalVal);
    }

    public void settingsClicked(View v) {
        Intent i = new Intent(MainActivity.this, Settings.class);
        startActivity(i);
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}