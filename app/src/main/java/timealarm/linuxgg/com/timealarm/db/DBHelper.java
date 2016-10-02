package timealarm.linuxgg.com.timealarm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tom on 2016/10/2.<p>
 * Version 1.0 <p>
 * Desc :    <p>
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "alarms.db";

    private static final int DB_VERSION = 1;

    private static DBHelper instance = null;
    private Context context;


    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        TableAlarmHistory.createDB(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        TableAlarmHistory.dropTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }
}
