package com.bobbythorne.ledcontroller;

import android.support.v4.app.Fragment;

/**
 * Created by NH229U11 on 9/22/2016.
 */
public class PresetListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PresetListFragment();
    }
}
