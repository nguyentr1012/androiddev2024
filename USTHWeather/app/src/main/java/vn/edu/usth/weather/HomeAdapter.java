package vn.edu.usth.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String titles[] = new String[] { "Hanoi", "Paris", "Toulouse" };

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    // Number of pages for a ViewPager
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    // Return title for a page
    public CharSequence getPageTitle(int page) {
        return titles[page];
    }

    @Override
    public Fragment getItem(int page) {
        switch (page) {
            case 0:
                return new WeatherFragment();
            case 1:
                return new ForecastFragment();
            case 2:
                return new WeatherandForecastFragment();
            default:
                return null;
        }
    }
}