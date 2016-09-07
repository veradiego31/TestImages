package io.sirio.testimages.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.sirio.testimages.R;
import io.sirio.testimages.adapters.CustomPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisenoExt5Fragment extends Fragment {


    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    int[] mResources = {
            R.drawable.exterior_4,
            R.drawable.exterior_4_0,
            R.drawable.exterior_4_1,
            R.drawable.exterior_4_2,
            R.drawable.exterior_4_3,
            R.drawable.exterior_4_4,
            R.drawable.exterior_4_5,
            R.drawable.exterior_4_6,
            R.drawable.exterior_4_7
    };


    private Unbinder unbinder;
    public static DisenoExt5Fragment newInstance() {
        DisenoExt5Fragment f = new DisenoExt5Fragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_diseno_ext2, container, false);
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity(), mResources);
        mViewPager = (ViewPager) v.findViewById(R.id.PagerViewerExt);
        mViewPager.setAdapter(mCustomPagerAdapter);
        unbinder = ButterKnife.bind(this, v);


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
