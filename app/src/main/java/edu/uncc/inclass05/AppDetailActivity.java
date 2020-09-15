package edu.uncc.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.uncc.inclass05.utils.App;

import static edu.uncc.inclass05.AppCategoriesActivity.APP_KEY;

public class AppDetailActivity extends AppCompatActivity {

    App app;
    TextView appName;
    TextView artistName;
    TextView releaseDate;
    ImageView image;
    ListView listview;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        setTitle("App Details");

        appName = findViewById(R.id.appName);
        artistName = findViewById(R.id.artistName);
        releaseDate = findViewById(R.id.releaseDate);
        image = findViewById(R.id.imageView);
        listview = findViewById(R.id.listViewGenres);

        Intent intent = getIntent();
        app = (App) intent.getSerializableExtra(APP_KEY);

        appName.setText(app.name);
        artistName.setText(app.artistName);
        releaseDate.setText(app.releaseDate);

        new GetImageFromUrl().execute(app.artworkUrl100);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, app.getGenres());
        listview.setAdapter(adapter);

    }
    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                int width = 1000;
                float factor = width / (float) bitmap.getWidth();
                bitmap = Bitmap.createScaledBitmap(bitmap, width, (int) (bitmap.getHeight() * factor), true);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return bitmap;
        }
    }
}
/*
Note: I spent 5 hours trying to figure out why my bitmap was coming back null, not realizing there was a difference between the url
and the artworkUrl100
 */