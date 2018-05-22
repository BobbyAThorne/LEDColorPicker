package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.ColorDbSchema.ColorTable;


/**
 * Created by Bobby Thorne on 10/6/2016.
 */
public class ColorBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "ColorPicker.db";

    public ColorBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + ColorTable.NAME + "(" +
                        ColorTable.Cols.COLORID + ", " +
                        ColorTable.Cols.UUID + ", " +
                        ColorTable.Cols.COLOR + ")"
                //PresetTable.Cols.ALPHA +", " +
                //PresetTable.Cols.RED +", " +
                //PresetTable.Cols.GREEN +", " +
                //PresetTable.Cols.BLUE +", " +
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
