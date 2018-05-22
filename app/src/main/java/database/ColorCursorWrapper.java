package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bobbythorne.ledcontroller.ColorPick;

import java.util.UUID;

import database.ColorDbSchema.ColorTable;


/**
 * Created by Bobby Thorne on 10/6/2016.
 */
public class ColorCursorWrapper extends CursorWrapper {
    public ColorCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public ColorPick getColorPick() {
        String uuidString = getString(getColumnIndex(ColorTable.Cols.UUID));
        String colorUuidString = getString(getColumnIndex(ColorTable.Cols.COLORID));
        int pick = getInt(getColumnIndex(ColorTable.Cols.COLOR));
//        int alpha = getInt(getColumnIndex(PresetTable.Cols.ALPHA));
//        int red = getInt(getColumnIndex(PresetTable.Cols.RED));
//        int green = getInt(getColumnIndex(PresetTable.Cols.GREEN));
//        int blue = getInt(getColumnIndex(PresetTable.Cols.BLUE));

        ColorPick color = new ColorPick(UUID.fromString(colorUuidString), UUID.fromString(uuidString));
        color.setColor(pick);

        return color;
    }
}
