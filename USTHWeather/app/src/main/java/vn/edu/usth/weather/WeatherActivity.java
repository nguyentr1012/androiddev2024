package vn.edu.usth.weather;

import android.os.Bundle;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import android.util.Log;

import android.media.MediaPlayer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
public class WeatherActivity extends AppCompatActivity {
    //extract and play music part
    private void extractAndPlayMusic() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.wii_sound);
            File musicFile = new File(getExternalFilesDir(null), "music.mp3");
            OutputStream outputStream = new FileOutputStream(musicFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0){
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
        } catch(Exception e){
            e.printStackTrace();
        }
    }
//viewpager part
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ViewPager viewPager = findViewById(R.id.viewPager);
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        extractAndPlayMusic();
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        // Optionally, set tab titles
        tabLayout.getTabAt(0).setText("Hanoi,VietNam");
        tabLayout.getTabAt(1).setText("Paris,France");
        tabLayout.getTabAt(2).setText("Toulouse,France");
    }


    //log part
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