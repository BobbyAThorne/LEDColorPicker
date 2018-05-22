package com.bobbythorne.ledcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by NH229U11 on 9/29/2016.
 */
public class PresetPagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID =
            "com.bobbythorne.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Preset> mPresets;

    public static Intent newIntent(Context packageContext, UUID crimeID) {
        Intent intent = new Intent(packageContext, PresetPagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_preset_pager_view_pager);

        mPresets = PresetLab.get(this).getPresets();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Preset crime = mPresets.get(position);
                return PresetFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mPresets.size();
            }
        });
        for (int i = 0; i < mPresets.size(); i++) {
            if (mPresets.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
