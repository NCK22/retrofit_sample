package com.applex.matrimony.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.applex.matrimony.Fragment.TabHome;
import com.applex.matrimony.Fragment.TabMyProfile;
import com.applex.matrimony.Fragment.TabPartnerPreferences;
import com.applex.matrimony.Fragment.TabWhoInterested;
import com.applex.matrimony.Fragment.TabWhoShortlisted;
import com.applex.matrimony.Fragment.TabWhoViewed;
import com.applex.matrimony.R;

public class TabParentProfileActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private TabParentProfileActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    String quotes[];
    TextView bottomText;
    Toolbar toolbar;
    TextView toolbar_textView;
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    BottomNavigationView bottmNavView;
    TextView testtv;
    TabLayout tabLayout;



    Intent intent;
    Bundle bundle;
    String tabFlag="home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_parent_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Home");

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tabLayout=(TabLayout)findViewById(R.id.tl_parent);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setTabTextColors(Color.BLACK,Color.WHITE);


        intent=getIntent();

        bundle = new Bundle();
        tabFlag=intent.getStringExtra("tabFlag");


        //   Tabs Activity

        mSectionsPagerAdapter = new TabParentProfileActivity.SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.vp_parent);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_parent);

        mViewPager.setOffscreenPageLimit(0);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.getMenu().clear(); //clear old inflated items.
        navigationView.inflateMenu(R.menu.menu_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        //  setHeader();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        bottmNavView=(BottomNavigationView)findViewById(R.id.bottomNavigationView);
bottmNavView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {



    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.menu_go_home:
             //   toolbar.setTitle(getString(R.string.menu_home));
                startActivity(new Intent(TabParentProfileActivity.this, TabParentProfileActivity.class));
                return true;

            case R.id.menu_go_matches:
             //   toolbar.setTitle(getString(R.string.menu_matches));
                startActivity(new Intent(getApplicationContext(),TabParentMatchesActivity.class).putExtra("tabFlag","matches"));
                return true;
        }
            return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

        Log.e("hascapture",""+hasCapture);
        switch(bottmNavView.getSelectedItemId())
        {
            case R.id.menu_go_home:
                startActivity(new Intent(getApplicationContext(),TabParentMatchesActivity.class).putExtra("tabFlag","home"));
                break;

            case R.id.menu_go_profile:break;
            case R.id.menu_go_matches:
                startActivity(new Intent(getApplicationContext(),TabParentMatchesActivity.class).putExtra("tabFlag","matches"));
                break;
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Log.e("position", String.valueOf(position));
            //Log.e("positi", "" + tabLayout.getSelectedTabPosition());


            switch (position) {


                case 0:
                    Log.e("Tab", "home");
                    TabMyProfile tabMyProfile=new TabMyProfile();
                    return  tabMyProfile;

                case 1:

                        Log.e("Tab", "whoViewed");
                        TabPartnerPreferences tabPartnerPreferences = new TabPartnerPreferences();
                        return tabPartnerPreferences;


                default:
                    return null;

            }


        }



        @Override
        public int getItemPosition(@NonNull Object object) {
            Log.e("positionItem",""+object);
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.

            return 2;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                startActivity(new Intent(getApplicationContext(),TabParentProfileActivity.class).putExtra("tabFlag","home"));
                break;

            case R.id.menu_go_matches:
                startActivity(new Intent(getApplicationContext(),TabParentMatchesActivity.class).putExtra("tabFlag","matches"));
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

