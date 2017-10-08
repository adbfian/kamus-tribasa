package com.bafika.adbfian.tribasaapp.Tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bafika.adbfian.tribasaapp.IndonesiaFragment;
import com.bafika.adbfian.tribasaapp.KromoFragment;
import com.bafika.adbfian.tribasaapp.NgokoFragment;
import com.bafika.adbfian.tribasaapp.R;

/**
 * Created by adbfian on 24/09/2016.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] titles = {"NGOKO", "KROMO", "INDONESIA"};
    int[] icons = new int[]{ R.drawable.ic_play_circle_filled_black_24dp, R.drawable.ic_play_circle_filled_black_24dp, R.drawable.ic_play_circle_filled_black_24dp};
    private int heightIcon;

    public FragmentAdapter(FragmentManager fm, Context c) {
        super(fm);

        context = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon=(int) (24*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment_page = null;

        if (position == 0) { fragment_page = new NgokoFragment();}
        else if (position == 1) { fragment_page = new KromoFragment();}
        else if (position == 2) { fragment_page = new IndonesiaFragment();}

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment_page.setArguments(bundle);
        return fragment_page;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ( titles[position]);
    }
}
