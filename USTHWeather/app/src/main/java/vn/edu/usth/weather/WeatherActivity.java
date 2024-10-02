package vn.edu.usth.weather;

import android.content.Intent;
import android.os.Bundle;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.media.MediaPlayer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.AsyncTask;
public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize ViewPager and TabLayout
        ViewPager viewPager = findViewById(R.id.viewPager);
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //  set tab titles
        tabLayout.getTabAt(0).setText("Hanoi, Vietnam");
        tabLayout.getTabAt(1).setText("Paris, France");
        tabLayout.getTabAt(2).setText("Toulouse, France");



        // Request permissions and play music
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            extractAndPlayMusic();
        }
    }
//practical 12 appbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int toolid = item.getItemId();
        if (toolid == R.id.refresh) {
//            refresh_threadsim();
//            Toast.makeText(this, "Refresh...", Toast.LENGTH_SHORT).show();
            new refresh_async().execute();
            return true;
        } else if (toolid == R.id.setting) {
            startActivity(new Intent(this, PrefActivity.class));
            return true;
        }
        return false;
    }

    //thread for refreshing(prac13)
//    private void refresh_threadsim() {
//        Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
//    final handler = new Handler(Looper.getMainLooper());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    runOnUiThread((new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(WeatherActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
//                        }
//                    }));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }



    //asynctask(prac14)
    private class refresh_async extends AsyncTask<Void,Void,Void>{
        @Override
        //for display toast before starting the background operation
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(WeatherActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
        }
        //for simulate 1 network delay
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        //for display toast after background operation
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(WeatherActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
        }
    }

    //playing music
    private void extractAndPlayMusic() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.wii_sound);
            File musicFile = new File(getExternalFilesDir(null), "music.mp3");
            OutputStream outputStream = new FileOutputStream(musicFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            // Play
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(musicFile.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("start", "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume", "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("pause", "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("stop", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("destroy", "onDestroy called");
    }
}
