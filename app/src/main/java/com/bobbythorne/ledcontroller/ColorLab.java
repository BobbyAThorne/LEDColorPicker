package com.bobbythorne.ledcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.ColorBaseHelper;
import database.ColorCursorWrapper;
import database.ColorDbSchema;


/**
 * Created by Bobby Thorne on 11/28/2016.
 */
public class ColorLab {

    private static ColorLab sColorLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ColorLab get(Context context) {
        if (sColorLab == null) {
            sColorLab = new ColorLab(context);
        }
        return sColorLab;
    }

    private ColorLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ColorBaseHelper(mContext).getWritableDatabase();
    }

    public void deleteColor(UUID colorId) {

        String uuidString = colorId.toString();
        mDatabase.delete(ColorDbSchema.ColorTable.NAME, ColorDbSchema.ColorTable.Cols.COLORID + " = ?", new String[]{uuidString});
    }

    public void addColor(ColorPick c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(ColorDbSchema.ColorTable.NAME, null, values);
    }

    public List<ColorPick> getColors() {
        List<ColorPick> Colors = new ArrayList<>();

        ColorCursorWrapper cursor = queryColors(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Colors.add(cursor.getColorPick());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Colors;
    }

    public void updateColor(ColorPick colorPick) {
        String uuidString = colorPick.getColorId().toString();
        ContentValues values = getContentValues(colorPick);

        mDatabase.update(ColorDbSchema.ColorTable.NAME, values, ColorDbSchema.ColorTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public ColorPick getColor(UUID id) {
        ColorCursorWrapper cursor = queryColors(
                ColorDbSchema.ColorTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getColorPick();
        } finally {
            cursor.close();
        }
    }

    /**
     * also need to make one for the color table
     *
     * @param ColorPick
     * @return
     */
    private static ContentValues getContentValues(ColorPick ColorPick) {
        ContentValues values = new ContentValues();

        values.put(ColorDbSchema.ColorTable.Cols.COLORID, ColorPick.getColorId().toString());
        values.put(ColorDbSchema.ColorTable.Cols.UUID, ColorPick.getPresetId().toString());
        values.put(ColorDbSchema.ColorTable.Cols.COLOR, ColorPick.getColor());

        return values;
    }

    private ColorCursorWrapper queryColors(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ColorDbSchema.ColorTable.NAME,
                null,
                whereClause,
                whereArgs, null, null, null
        );
        return new ColorCursorWrapper(cursor);
    }

}
