package database;

/**
 * Created by Bobby Thorne on 10/6/2016.
 */
public class ColorDbSchema {

    public static final class ColorTable {
        public static final String NAME = "colors";

        public static final class Cols {
            public static final String COLORID = "colorID";
            public static final String UUID = "uuid";

            public static final String COLOR = "color";

            public static final String ALPHA = "alpha";
            public static final String RED = "red";
            public static final String GREEN = "green";
            public static final String BLUE = "blue";
        }
    }
}
