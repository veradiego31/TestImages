package io.sirio.testimages;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.sirio.testimages.adapters.MyAdapterNav;
import io.sirio.testimages.fragments.AboutFragment;
import io.sirio.testimages.fragments.AccesoriosFragment;
import io.sirio.testimages.fragments.ConfortFragment;
import io.sirio.testimages.fragments.DisenoExtFragment;
import io.sirio.testimages.fragments.DisenoIntFragment;
import io.sirio.testimages.fragments.FichaTecnicaFragment;
import io.sirio.testimages.fragments.HomeFragment;
import io.sirio.testimages.fragments.IntentServiceResult;
import io.sirio.testimages.fragments.LegalesFragment;
import io.sirio.testimages.fragments.ManejaloFragment;
import io.sirio.testimages.fragments.PerformanceFragment;
import io.sirio.testimages.fragments.SeguridadFragment;
import io.sirio.testimages.fragments.TPAFragment;
import io.sirio.testimages.fragments.TecnologiaFragment;
import io.sirio.testimages.util.AppConstants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private  MyAdapterNav myAdapterNav;
    private ListView listView;
    private boolean mSlideState = false;
    private DrawerLayout drawer;

    @BindView(R.id.id_action_nav) ImageButton toolbarHome;
    @BindView(R.id.id_action_back) ImageButton toolbarBack;
    @BindView(R.id.id_action_fb) ImageButton toolbarFb;
    @BindView(R.id.id_action_yt) ImageButton toolbarYt;
    @BindView(R.id.id_action_inst) ImageButton toolbarInst;
    @BindView(R.id.id_action_inf) ImageButton toolbarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerListener( new ActionBarDrawerToggle(this,
                drawer, R.drawable.ic_action_hambuger, 0 , 0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mSlideState=false;//is Closed
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSlideState=true;//is Opened
            }
        });

        ButterKnife.bind(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
      //  navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.getRootView();
        listView = (ListView) navView.findViewById(R.id.list_view_inside_nav);

        myAdapterNav = new MyAdapterNav(this);

        listView.setAdapter(myAdapterNav);
        listView.setOnItemClickListener(this);
        setListViewHeightBasedOnChildren(listView);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setFragment(0);
    }

    @OnClick(R.id.id_action_nav)
    public void onClick(){
        clickEventSlide();
    }

    @OnClick(R.id.id_action_back)
    public void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.id_action_fb)
    public void onClickFb(){
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }



    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + AppConstants.FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + AppConstants.FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return AppConstants.FACEBOOK_URL; //normal web url
        }
    }

    @OnClick(R.id.id_action_inst)
    public void onClickInst(){
        Uri uri = Uri.parse("http://instagram.com/_u/"+AppConstants.INSTAGRAM_PAGE_ID);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/"+AppConstants.INSTAGRAM_PAGE_ID)));
        }
    }

    @OnClick(R.id.id_action_yt)
    public void onClickYoutube(){
        Intent intent=null;
        try {
            intent =new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse("http://www.youtube.com/channel/"+AppConstants.YOUTUBE_PAGE_ID));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.youtube.com/channel/"+AppConstants.YOUTUBE_PAGE_ID));
            startActivity(intent);
        }
    }

    @OnClick(R.id.id_action_inf)
    public void onClickInfo(){
            setFragment(12);
    }


    public void clickEventSlide(){
        if(mSlideState){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            drawer.openDrawer(GravityCompat.START);
        }}

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return true;
    }

    public void setFragment(int position){
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new PerformanceFragment();
                break;
            case 2:
                fragment = new DisenoIntFragment();
                break;
            case 3:
                fragment = new DisenoExtFragment();
                break;
            case 4:
                fragment = new ConfortFragment();
                break;
            case 5:
                fragment = new TecnologiaFragment();
                break;
            case 6:
                fragment = new SeguridadFragment();
                break;
            case 7:
                fragment = new AccesoriosFragment();
                break;
            case 8:
                fragment = new FichaTecnicaFragment();
                break;
            case 9:
                fragment = new ManejaloFragment();
                break;
            case 10:
                fragment = new TPAFragment();
                break;
            case 11:
                fragment = new LegalesFragment();
                break;
            case 12:
                fragment = new AboutFragment();
                break;
        }

        if(fragment !=  null){
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        setFragment(position);
        closeDrawer(position);

    }

    private void closeDrawer(int position) {
        listView.setItemChecked(position, true);
        drawer.closeDrawer(GravityCompat.START);

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(IntentServiceResult event) {
        closeDrawer(event.mResult);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

}
