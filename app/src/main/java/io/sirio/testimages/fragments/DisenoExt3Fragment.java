package io.sirio.testimages.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.sirio.testimages.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisenoExt3Fragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.imgViewerExt)
    ImageView img;

    public static DisenoExt3Fragment newInstance() {
        DisenoExt3Fragment f = new DisenoExt3Fragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_diseno_ext1, container, false);
        unbinder = ButterKnife.bind(this, v);
        Glide.with(getContext()).load(R.drawable.exterior_2).into(img);

        return v;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
