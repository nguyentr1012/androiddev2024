package vn.edu.usth.weather;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("created", "created called");
        ForecastFragment firstFragment = new ForecastFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, firstFragment).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("start", "onStart called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("resume", "onResume called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("pause", "onPause called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("stop", "onStop called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("destroy", "onDestroy called");
    }
}