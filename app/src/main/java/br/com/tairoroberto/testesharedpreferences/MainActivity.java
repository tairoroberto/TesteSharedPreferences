package br.com.tairoroberto.testesharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    private static final String PREF_NAME = "MainActivityPreferences";
    private int count1;
    private int count2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        count1 = sharedPreferences.getInt("count_1",0);
        Log.i("Script","MainActivity COUNT 1: "+count1);

        //SharedPreferences Listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.i("Script",key + " Updated");
            }
        });

        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences2 = getPreferences(MODE_PRIVATE);
        count2 = sharedPreferences2.getInt("count_2",0);
        Log.i("Script","MainActivity COUNT 2: "+count2);

    }

    @Override
    protected void onStop() {
        super.onStop();
        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        count1 = sharedPreferences.getInt("count_1",0);
        //Editor to change the value of count1 and count2
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count_1",count1 + 1);
        editor.commit();

        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences2 = getPreferences(MODE_PRIVATE);
        count2 = sharedPreferences2.getInt("count_2",0);
        editor.putInt("count_2",count2 + 1);
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.i("Script",key + " Destroy");
            }
        });
    }

    public void callSecondActivity(View view){
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}
