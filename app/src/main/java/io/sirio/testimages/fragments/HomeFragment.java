package io.sirio.testimages.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.sirio.testimages.R;
import io.sirio.testimages.adapters.HomeAdapter;
import io.sirio.testimages.models.Images;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeAdapter.OnItemClickListener {

    private HomeAdapter homeAdapter ;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayList<Images> imagenesHome = new ArrayList<>();

        imagenesHome = setDataImages();

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_home);
        recyclerView.setHasFixedSize(true);
        homeAdapter = new HomeAdapter(imagenesHome, R.layout.row_image, getContext());
        homeAdapter.SetOnItemClickListener(this);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private ArrayList<Images> setDataImages() {
        ArrayList<Images> data = new ArrayList<>();

        Images img1 = new Images();
        img1.setResource(R.drawable.home01_performance);
        img1.setName(getResources().getString(R.string.performance));
        data.add(img1);

        Images img2 = new Images();
        img2.setResource(R.drawable.home02_exterior);
        img2.setName(getResources().getString(R.string.disenho_exterior));
        data.add(img2);

        Images img3 = new Images();
        img3.setResource(R.drawable.home03_interior);
        img3.setName(getResources().getString(R.string.disenho_interior));
        data.add(img3);

        Images img4 = new Images();
        img4.setResource(R.drawable.home04_confort);
        img4.setName(getResources().getString(R.string.confort));
        data.add(img4);

        Images img5 = new Images();
        img5.setResource(R.drawable.home05_tecnologia);
        img5.setName(getResources().getString(R.string.tecnologia));
        data.add(img5);

        Images img6 = new Images();
        img6.setResource(R.drawable.home06_seguridad);
        img6.setName(getResources().getString(R.string.seguridad));
        data.add(img6);

        Images img7 = new Images();
        img7.setResource(R.drawable.home07_accesorios);
        img7.setName(getResources().getString(R.string.accesorios));
        data.add(img7);

        Images img8 = new Images();
        img8.setResource(R.drawable.home08_ficha);
        img8.setName(getResources().getString(R.string.ficha_tecnica));
        data.add(img8);

        Images img9 = new Images();
        img9.setResource(R.drawable.home09_tpa);
        img9.setName(getResources().getString(R.string.t_p_a));
        data.add(img9);

        Images img10 = new Images();
        img10.setResource(R.drawable.home10_manejalo);
        img10.setName(getResources().getString(R.string.manejalo));
        data.add(img10);


        return data;


    }

    @Override
    public void onItemClick(View view, int position) {
        Fragment fragment = null;
        Class fragmentClass = HomeFragment.class;
        int listPosition;
        switch(position) {

            case 0:
                fragmentClass = PerformanceFragment.class;
                listPosition = 1;
                break;
            case 1:
                fragmentClass = DisenoExtFragment.class;
                listPosition =3 ;
                break;
            case 2:
                fragmentClass = DisenoIntFragment.class;
                listPosition =1;
                break;
            case 3:
                fragmentClass = ConfortFragment.class;
                listPosition = 3;
                break;
            case 4:
                fragmentClass = TecnologiaFragment.class;
                listPosition = 4;
                break;
            case 5:
                fragmentClass = SeguridadFragment.class;
                listPosition = 5;
                break;
            case 6:
                fragmentClass = AccesoriosFragment.class;
                listPosition = 6;
                break;
            case 7:
                fragmentClass = FichaTecnicaFragment.class;
                listPosition = 7;
                break;
            case 8:
                fragmentClass = TPAFragment.class;
                listPosition = 9;
                break;
            case 9:
                fragmentClass = ManejaloFragment.class;
                listPosition = 8;
                break;
            case 10:
                fragmentClass = ManejaloFragment.class;
                listPosition = 8;
                break;
            default:
                listPosition = -1;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (listPosition!= -1) EventBus.getDefault().post(new IntentServiceResult(listPosition));

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).addToBackStack(null).commit();

    }


}
