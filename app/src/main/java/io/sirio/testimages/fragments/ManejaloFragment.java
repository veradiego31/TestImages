package io.sirio.testimages.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.sirio.testimages.R;
import io.sirio.testimages.util.AppConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManejaloFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.imageViewTestDrive)
    ImageView imageView;
    public ManejaloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_manejalo, container, false);
        unbinder = ButterKnife.bind(this, v);

        Glide.with(getContext()).load(R.drawable.manejalo_0).into(imageView);
        return v;
    }

    @OnClick(R.id.bttnTestDrive)
    public void onClick(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(AppConstants.TEST_DRIVE_URL));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
