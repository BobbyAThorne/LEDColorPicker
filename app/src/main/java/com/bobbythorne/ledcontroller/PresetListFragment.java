package com.bobbythorne.ledcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NH229U11 on 9/22/2016.
 */
public class PresetListFragment extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mPresetRecyclerView;
    private PresetAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preset_list, container, false);

        mPresetRecyclerView = (RecyclerView) view.findViewById(R.id.preset_recycler_view);
        mPresetRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (saveInstanceState != null) {
            mSubtitleVisible = saveInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_preset_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_preset:
                Preset Preset = new Preset();
                PresetLab.get(getActivity()).addPreset(Preset);
                Intent intent = PresetPagerActivity.newIntent(getActivity(), Preset.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        PresetLab presetLab = PresetLab.get(getActivity());
        int PresetCount = presetLab.getPresets().size();
        String subtitle = getResources().getQuantityString(R.plurals.subtitle_plural, PresetCount, PresetCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        PresetLab presetLab = PresetLab.get(getActivity());
        List<Preset> Presets = presetLab.getPresets();

        if (mAdapter == null) {
            mAdapter = new PresetAdapter(Presets);
            mPresetRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setPresets(Presets);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class PresetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mSubType;

        private Preset mPreset;

        public PresetHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_preset_title_text_view);
            mSubType = (TextView) itemView.findViewById(R.id.list_item_preset_date_text_view);

        }

        public void bindPreset(Preset Preset) {
            mPreset = Preset;
            mTitleTextView.setText(mPreset.getTitle());
            mSubType.setText(mPreset.getType());

        }

        @Override
        public void onClick(View v) {
            Intent intent = PresetPagerActivity.newIntent(getActivity(), mPreset.getId());
            startActivity(intent);
        }

    }

    private class PresetAdapter extends RecyclerView.Adapter<PresetHolder> {
        private List<Preset> mPresets;

        public PresetAdapter(List<Preset> Presets) {
            mPresets = Presets;
        }

        @Override
        public PresetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_preset, parent, false);
            return new PresetHolder(view);
        }

        @Override
        public void onBindViewHolder(PresetHolder holder, int position) {
            Preset Preset = mPresets.get(position);
            holder.bindPreset(Preset);
        }

        @Override
        public int getItemCount() {
            return mPresets.size();
        }

        public void setPresets(List<Preset> Presets) {
            mPresets = Presets;
        }
    }
}
