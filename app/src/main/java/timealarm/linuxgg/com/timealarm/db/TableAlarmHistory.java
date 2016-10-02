package timealarm.linuxgg.com.timealarm.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import java.util.ArrayList;
import java.util.List;

import timealarm.linuxgg.com.timealarm.models.AlarmItem;

/**
 * Created by tom on 2016/10/2.<p>
 * Version 1.0 <p>
 * Desc :    <p>
 */
public class TableAlarmHistory extends BaseTable {
    private static final String TAG = TableAlarmHistory.class.getSimpleName();


    public static final String TABLE_NAME = "alarm_history";


    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String HAS_AUDIO = "has_audio";

    public static final Uri URI = Uri.parse(MyContantProvider.URI_PREFIX + TABLE_NAME);


    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            " (" + ID + " integer primary key autoincrement, " +
            DESC + " text  , " +
            START_TIME + " text not null, " +
            END_TIME + " text not null, " +
            HAS_AUDIO + " text not null);";

    public static final String DROP_TABLE = "if exists " + TABLE_NAME + " drop table;";


    public enum HAS_AUDIO_TYPE {
        YES, NO
    }

    /**
     * create table
     *
     * @param sqLiteDatabase
     */
    public static void createDB(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    /**
     * drop table
     *
     * @param sqLiteDatabase
     */
    public static void dropTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DROP_TABLE);
    }


    public static void insertSingle(Context context, AlarmItem item) {
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(DESC, item.getDESC());
        values.put(START_TIME, item.getSTART_TIME());
        values.put(END_TIME, item.getEND_TIME());
        values.put(HAS_AUDIO, item.getHAS_AUDIO());
        cr.insert(URI, values);
    }

    public static void insertMulti(Context context, List<AlarmItem> items) {
        ContentResolver cr = context.getContentResolver();
        ContentValues[] values = new ContentValues[items.size()];
        for (int i = 0; i < items.size(); i++) {
            ContentValues value = new ContentValues();
            value.put(DESC, items.get(i).getDESC());
            value.put(START_TIME, items.get(i).getSTART_TIME());
            value.put(END_TIME, items.get(i).getEND_TIME());
            value.put(HAS_AUDIO, items.get(i).getHAS_AUDIO());
        }
        cr.bulkInsert(URI, values);
    }

    public static int del(Context context, AlarmItem alarmItem) {
        Uri newUri = Uri.parse(URI + "/" + alarmItem.getID());
        return context.getContentResolver().delete(newUri, null, null);
    }

    public static List<AlarmItem> queryALL(LoaderManager supportLoaderManager, Context context, String selection, String[] args) {

        supportLoaderManager.initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return null;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        });


        CursorLoader cl = new CursorLoader(context);
        cl.loadInBackground();


        List<AlarmItem> alarms = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(URI, null, selection, args, null);

        while (cursor != null && cursor.moveToFirst()) {
            do {
                AlarmItem alarmItem = new AlarmItem();
                alarmItem.setID(cursor.getLong(cursor.getColumnIndexOrThrow(TableAlarmHistory.ID)));
                alarmItem.setHAS_AUDIO(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.HAS_AUDIO)));
                alarmItem.setDESC(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.DESC)));
                alarmItem.setSTART_TIME(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.START_TIME)));
                alarmItem.setEND_TIME(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.END_TIME)));
                alarms.add(alarmItem);

            } while (cursor.moveToNext());
        }
        return alarms;
    }
}
