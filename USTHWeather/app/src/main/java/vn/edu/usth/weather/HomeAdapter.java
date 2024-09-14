package vn.edu.usth.weather;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HomeAdapter extends FragmentStatePagerAdapter {

    public HomeAdapter( FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
            return WeatherandForecastFragment.newInstance("param1", "param2");
    }
    @Override
    public int getCount() {
        return 3; // Number of pages
    }
}
