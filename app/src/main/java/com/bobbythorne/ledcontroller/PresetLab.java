package com.bobbythorne.ledcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.PresetBaseHelper;
import database.PresetCursorWrapper;

import static database.PresetDbSchema.PresetTable;


/**
 * Created by nh229u11 on 9/20/2016.
 */
public class PresetLab {
    private static PresetLab sPresetLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PresetLab get(Context context) {
        if (sPresetLab == null) {
            sPresetLab = new PresetLab(context);
        }
        return sPresetLab;
    }

    private PresetLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PresetBaseHelper(mContext).getWritableDatabase();
    }

    public void deletePreset(UUID presetId) {

        String uuidString = presetId.toString();
        mDatabase.delete(PresetTable.NAME, PresetTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void addPreset(Preset c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(PresetTable.NAME, null, values);
    }

    public List<Preset> getPresets() {
        List<Preset> Presets = new ArrayList<>();

        PresetCursorWrapper cursor = queryPresets(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Presets.add(cursor.getPreset());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Presets;
    }

    public void updatePreset(Preset Preset) {
        String uuidString = Preset.getId().toString();
        ContentValues values = getContentValues(Preset);

        mDatabase.update(PresetTable.NAME, values, PresetTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public Preset getPreset(UUID id) {
        PresetCursorWrapper cursor = queryPresets(
                PresetTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPreset();
        } finally {
            cursor.close();
        }
    }

    /**
     * also need to make one for the color table
     *
     * @param Preset
     * @return
     */
    private static ContentValues getContentValues(Preset Preset) {
        ContentValues values = new ContentValues();

        values.put(PresetTable.Cols.UUID, Preset.getId().toString());
        values.put(PresetTable.Cols.TITLE, Preset.getTitle());
        values.put(PresetTable.Cols.TYPE, Preset.getType());

        return values;
    }

    private PresetCursorWrapper queryPresets(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                PresetTable.NAME,
                null,
                whereClause,
                whereArgs, null, null, null
        );
        return new PresetCursorWrapper(cursor);
    }
}
