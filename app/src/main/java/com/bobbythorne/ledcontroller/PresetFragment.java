package com.bobbythorne.ledcontroller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.bobbythorne.ledcontroller.ColorPickerDialog.OnColorSelectedListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Thorne on 9/13/2016.
 */
public class PresetFragment extends Fragment {

    private static final String ARG_Preset_ID = "Preset_id";

    private Preset mPreset;
    private ColorPick mColor;
    private EditText mTitleField;
    private Spinner mType;
    private Button mAddColor;
    private ArrayList mColors = new ArrayList();

//    private Button mDateButton;
//    private CheckBox mSolvedCheckBox;

    public static PresetFragment newInstance(UUID PresetId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_Preset_ID, PresetId);

        PresetFragment fragment = new PresetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_preset, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete_preset:
                PresetLab.get(getActivity()).deletePreset(mPreset.getId());
                Toast.makeText(getActivity(), R.string.delete_preset, Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID PresetId = (UUID) getArguments().getSerializable(ARG_Preset_ID);
        mPreset = PresetLab.get(getActivity()).getPreset(PresetId);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        PresetLab.get(getActivity()).updatePreset(mPreset);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_preset,
                container,
                false);
        mTitleField = (EditText) v.findViewById(R.id.preset_title);
        mTitleField.setText(mPreset.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start,
                                          int count,
                                          int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start,
                                      int count,
                                      int after) {
                mPreset.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });
        mType = (Spinner) v.findViewById(R.id.type_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(v.getContext(), R.array.type_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mType.setAdapter(adapter);
//        if( !mPreset.getType().equals(null)){
//            Log.v("item", mPreset.getType());
//
//            int pos =adapter.getPosition( mPreset.getType() );
//            mType.setSelection( pos );
//        }
        try {
            int pos = adapter.getPosition(mPreset.getType());
            mType.setSelection(pos);
        } catch (Exception e) {

        }
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Log.v("item", (String) parent.getItemAtPosition(position));
                mPreset.setType((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        mAddColor = (Button) v.findViewById(R.id.btn_add_color);

        mAddColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialogDemo();

            }
        });

        return v;
    }



    /**
     * Example of using Color Picker in Alert Dialog.
     */
    private void showColorPickerDialogDemo() {

        int initialColor = Color.WHITE;

        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this.getActivity(), initialColor, new OnColorSelectedListener() {

            @Override
            public void onColorSelected(int color) {
                mColors.add(color);
                //new ColorPick(mPreset.getId()).setColor(color);
                showToast(color);

/*stopped here need a way to present the colors after selected and also save them*/
            }

        });
        colorPickerDialog.show();

    }

    /**
     * Displays Toast with RGB values of given color.
     *
     * @param color the color
     */
    private void showToast(int color) {
        String rgbString = "R: " + Color.red(color) + " B: " + Color.blue(color) + " G: " + Color.green(color);
        //rgbString = color+"";
        Toast.makeText(this.getActivity(), rgbString, Toast.LENGTH_SHORT).show();
    }


}
