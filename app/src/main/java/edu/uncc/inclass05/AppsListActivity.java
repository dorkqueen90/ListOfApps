package edu.uncc.inclass05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import edu.uncc.inclass05.utils.App;
import edu.uncc.inclass05.utils.Data;

import static edu.uncc.inclass05.AppCategoriesActivity.APP_KEY;

public class AppsListActivity extends AppCompatActivity {

    ArrayList<String> categories = new ArrayList<>(Data.apps.keySet());
    ArrayList<App> app;
    int appPosition;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);
        //get intent
        if(getIntent() != null && getIntent().getExtras() != null){
            appPosition = getIntent().getIntExtra(APP_KEY, 0);
        }
        app = Data.apps.get(categories.get(appPosition));

        //set activity title based on intent
        String titleName = categories.get(appPosition);
        setTitle(titleName);

        recyclerView = findViewById(R.id.rvItems);
        recyclerView.setHasFixedSize(true);
        layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);

        adapter = new categoryAdapter(app);
        recyclerView.setAdapter(adapter);
    }
}
