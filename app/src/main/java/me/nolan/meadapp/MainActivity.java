package me.nolan.meadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp =  getSharedPreferences("settingsConf", MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        SharedPreferences.Editor spe = sp.edit();
        Log.i("info", sp.toString());
        Date currentTime = Calendar.getInstance().getTime();
        spe.putString("key1", "test1");
        spe.putString("key2", "test2");
        spe.putString("key3", "test3");
        spe.putString("key4", "test4");
        spe.putString("key5", "test5");
        spe.putString("key6", "test6");
        spe.commit();
    }

    public void rollDice(View v) {
        final Handler handler = new Handler();
        final int delay = 200; //milliseconds

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bell);
        final int finalVal = 0;

        handler.postDelayed(new Runnable(){
            int finalVal = 0;
            int rollTime = 0;
            //Die faces
            int[] firstRoll = {1,2,2,3,3,3};
            int[] secondRoll = {0,0,1,2,3,4};
            int[] thirdRoll = {0,1,2,3,4,5};
            int lastRando1 = 1;
            int lastRando2 = 1;
            int lastRando3 = 1;
            public void run(){
                ImageView die1 = (ImageView) findViewById(R.id.die1);
                ImageView die2 = (ImageView) findViewById(R.id.die2);
                ImageView die3 = (ImageView) findViewById(R.id.die3);
                int dieRandom1 = new Random().nextInt(firstRoll.length);
                int dieRandom2 = new Random().nextInt(secondRoll.length);
                int dieRandom3 = new Random().nextInt(thirdRoll.length);
                finalVal = finalVal+dieRandom2;
                finalVal = finalVal+dieRandom3;


                //Prevent similar values
                if(dieRandom1 == lastRando1)
                    dieRandom1 = new Random().nextInt(firstRoll.length);
                if(dieRandom2 == lastRando2)
                    dieRandom2 = new Random().nextInt(secondRoll.length);
                if(dieRandom3 == lastRando3)
                    dieRandom3 = new Random().nextInt(thirdRoll.length);

                if(dieRandom1 == 3) {
                    finalVal += 30;
                    die1.setImageResource(R.drawable.id30);
                }
                if(dieRandom1 == 2) {
                    finalVal += 20;
                    die1.setImageResource(R.drawable.id20);
                }
                if(dieRandom1 == 1) {
                    finalVal += 10;
                    die1.setImageResource(R.drawable.id10);
                }

                if(dieRandom2 == 0)
                    die2.setImageResource(R.drawable.id0);
                if(dieRandom2 == 1)
                    die2.setImageResource(R.drawable.id1);
                if(dieRandom2 == 2)
                    die2.setImageResource(R.drawable.id2);
                if(dieRandom2 == 3)
                    die2.setImageResource(R.drawable.id3);
                if(dieRandom2 == 4)
                    die2.setImageResource(R.drawable.id4);

                if(dieRandom3 == 0)
                    die3.setImageResource(R.drawable.id0);
                if(dieRandom3 == 1)
                    die3.setImageResource(R.drawable.id1);
                if(dieRandom3 == 2)
                    die3.setImageResource(R.drawable.id2);
                if(dieRandom3 == 3)
                    die3.setImageResource(R.drawable.id3);
                if(dieRandom3 == 4)
                    die3.setImageResource(R.drawable.id4);
                if(dieRandom3 == 4)
                    die3.setImageResource(R.drawable.id5);

                //Rolltime = 25 because delay 1/5 second every iteration, therefore 25 roll times = 5 seconds.
                rollTime++;
                lastRando1 = dieRandom1;
                lastRando2 = dieRandom2;
                lastRando3 = dieRandom3;
                Date currentTime = Calendar.getInstance().getTime();
                if(!(rollTime >= 50))
                    //Multiply by roll time to create "slow down" effect
                    handler.postDelayed(this, delay+rollTime*10);
            }
        }, delay);

    }

    public void settingsClicked(View v) {
        setContentView(R.layout.settings_activity);
    }
}