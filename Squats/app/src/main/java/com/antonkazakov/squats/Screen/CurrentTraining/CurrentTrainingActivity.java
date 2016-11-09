package com.antonkazakov.squats.Screen.CurrentTraining;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.antonkazakov.squats.DialogController;
import com.antonkazakov.squats.R;
import com.antonkazakov.squats.Screen.Base.BaseActivity;
import com.antonkazakov.squats.Screen.Trainings.TrainingsFragment;
import com.antonkazakov.squats.di.components.AppComponent;

import butterknife.BindView;

public class CurrentTrainingActivity extends BaseActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);



    }

    @Override
    protected void injectDependencies(AppComponent component) {

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return CurrentTrainingFragment.newInstance(getIntent().getIntExtra("trainId",0));
                case 1:
                    return new TrainingsFragment();
                default:return null;
            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_train);
                case 1:
                    return getString(R.string.tab_stats);
            }
            return null;
        }
    }
}
