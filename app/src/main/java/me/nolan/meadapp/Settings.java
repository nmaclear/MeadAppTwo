package me.nolan.meadapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Map;

public class Settings extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("settingsConf", MODE_PRIVATE);
        setContentView(R.layout.settings_activity);

        ListView lv = (ListView) findViewById(R.id.listMain);

        ArrayList<String> settingsArray = new ArrayList<String>();
        Map<String, ?> prefsMap = sp.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            settingsArray.add(entry.getValue().toString());
        }
        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, settingsArray);

        lv.setAdapter(arrayAdapter);
    }

    public void exit(View v) {
        this.finish();
    }
}