package io.sirio.testimages.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.sirio.testimages.fragments.DisenoExt1Fragment;
import io.sirio.testimages.fragments.DisenoExt2Fragment;
import io.sirio.testimages.fragments.DisenoExt3Fragment;
import io.sirio.testimages.fragments.DisenoExt4Fragment;
import io.sirio.testimages.fragments.DisenoExt5Fragment;

/**
 * Created by Produccion on 4/7/2016.
 */

public  class ExteriorAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;

    public ExteriorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return DisenoExt1Fragment.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return DisenoExt2Fragment.newInstance();
            case 2: // Fragment # 1 - This will show SecondFragment
                return DisenoExt3Fragment.newInstance();
            case 3: // Fragment # 1 - This will show SecondFragment
                return DisenoExt4Fragment.newInstance();
            case 4: // Fragment # 1 - This will show SecondFragment
                return DisenoExt5Fragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
