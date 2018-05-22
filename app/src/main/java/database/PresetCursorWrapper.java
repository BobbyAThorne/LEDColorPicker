package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bobbythorne.ledcontroller.Preset;


import java.util.UUID;

import database.PresetDbSchema.PresetTable;

/**
 * Created by Bobby Thorne on 10/6/2016.
 */
public class PresetCursorWrapper extends CursorWrapper {
    public PresetCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Preset getPreset() {
        String uuidString = getString(getColumnIndex(PresetTable.Cols.UUID));
        String title = getString(getColumnIndex(PresetTable.Cols.TITLE));
        String type = getString(getColumnIndex(PresetTable.Cols.TYPE));


        Preset preset = new Preset(UUID.fromString(uuidString));
        preset.setTitle(title);
        preset.setType(type);


        return preset;
    }

}
