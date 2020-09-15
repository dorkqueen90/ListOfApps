package edu.uncc.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.uncc.inclass05.utils.Data;

 public class AppCategoriesActivity extends AppCompatActivity {

     static String APP_KEY = "APP";
     ListView listView;
     ArrayList<String> categories = new ArrayList<>(Data.apps.keySet());
     ArrayAdapter adapter;

    public final String TAG = "demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_categories);
        //Data.apps.keySet(); //to get the keys which are the app categories.
        Log.d(TAG, "onCreate: " + Data.apps.get("Top Free Apps"));
        setTitle("App Categories");

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, categories);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("demo", "Clicked item " + position + " app: " + categories.get(position));
                //Log.d("demo", "list: " + Data.apps.get(categories.get(position)));

                Intent intent = new Intent(AppCategoriesActivity.this, AppsListActivity.class);
                intent.putExtra(APP_KEY, position);
                startActivity(intent);
            }
        });

    }
}