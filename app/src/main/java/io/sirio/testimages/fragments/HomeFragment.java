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

        ArrayList<Integer> imagenesHome = new ArrayList<>();

        imagenesHome.add(R.drawable.home01_performance);
        imagenesHome.add(R.drawable.home02_exterior);
        imagenesHome.add(R.drawable.home03_interior);
        imagenesHome.add(R.drawable.home04_confort);
        imagenesHome.add(R.drawable.home05_tecnologia);
        imagenesHome.add(R.drawable.home06_seguridad);
        imagenesHome.add(R.drawable.home07_accesorios);
        imagenesHome.add(R.drawable.home08_ficha);
        imagenesHome.add(R.drawable.home09_tpa);
        imagenesHome.add(R.drawable.home10_manejalo);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_home);
        recyclerView.setHasFixedSize(true);
        homeAdapter = new HomeAdapter(imagenesHome, R.layout.row_image, getContext());
        homeAdapter.SetOnItemClickListener(this);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


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
