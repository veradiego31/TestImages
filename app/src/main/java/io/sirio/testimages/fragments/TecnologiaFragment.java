package io.sirio.testimages.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import io.sirio.testimages.R;
import io.sirio.testimages.adapters.CustomPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TecnologiaFragment extends Fragment {


    private CustomPagerAdapter mCustomPagerAdapter;
    private VerticalViewPager mViewPager;
    int[] mResources = {
            R.drawable.tecnologia_0, R.drawable.tecnologia_1, R.drawable.tecnologia_2,
            R.drawable.tecnologia_3, R.drawable.tecnologia_4
    };

    @BindView(R.id.imgDownPage)
    ImageView imgDownPage;
    @BindView(R.id.imgUpPage) ImageView imgUpPage;

    private Unbinder unbinder;

    public TecnologiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_performance, container, false);
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity(), mResources);
        mViewPager = (VerticalViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        unbinder = ButterKnife.bind(this, v);
        imgUpPage.setVisibility(View.INVISIBLE);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imgUpPage)
    public void onClickUp(View v){
        setPager(mViewPager.getCurrentItem()-1);
    }
    @OnClick(R.id.imgDownPage)
    public void onClickDown(View v){
        setPager(mViewPager.getCurrentItem()+1);
    }

    private void setPager(int position){
        mViewPager.setCurrentItem(position);
        if(position == 0) imgUpPage.setVisibility(View.INVISIBLE);
        else imgUpPage.setVisibility(View.VISIBLE);

        if(position == 4) imgDownPage.setVisibility(View.INVISIBLE);
        else imgDownPage.setVisibility(View.VISIBLE);
    }
}
