package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.PresetDbSchema.PresetTable;


/**
 * Created by Bobby Thorne on 10/6/2016.
 */
public class PresetBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "ColorPicker.db";

    public PresetBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PresetTable.NAME + "(" +
                PresetTable.Cols.UUID + ", " +
                PresetTable.Cols.TITLE + ", " +
                PresetTable.Cols.TYPE + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
