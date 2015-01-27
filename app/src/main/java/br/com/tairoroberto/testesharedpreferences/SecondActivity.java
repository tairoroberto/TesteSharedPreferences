package br.com.tairoroberto.testesharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class SecondActivity extends ActionBarActivity {
    private static final String PREF_NAME = "MainActivityPreferences";
    private int count1;
    private int count2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        count1 = sharedPreferences.getInt("count_1",0);
        Log.i("Script", "SecondActivity COUNT 1: " + count1);


        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences2 = getSharedPreferences("MainActivity",MODE_PRIVATE);
        count2 = sharedPreferences2.getInt("count_2",0);
        Log.i("Script","SecondActivity COUNT 2: "+count2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);

        //Editor to change the value of count1 and count2
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Clear all values in sharedpreference
        editor.clear();
        //Clear only key value
        //editor.remove("count_1");
        SharedPreferences sharedPreferences2 = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences2.edit();
        editor.clear();
        editor.commit();
    }
}
