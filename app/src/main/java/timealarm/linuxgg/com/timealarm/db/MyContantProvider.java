package timealarm.linuxgg.com.timealarm.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import timealarm.linuxgg.com.timealarm.BuildConfig;

/**
 * Created by tom on 2016/10/2.<p>
 * Version 1.0 <p>
 * Desc :    <p>
 */
public class MyContantProvider extends ContentProvider {
    private static final String TAG = MyContantProvider.class.getSimpleName();


    private static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".my_provider";
    private static final String SCHEME = "content://";
    public static final String URI_PREFIX = SCHEME + AUTHORITY + "/";


    public static final int URI_MULTI_PATH_CODE = 1;
    public static final int URI_SINGLE_PATH_CODE = 2;


    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TableAlarmHistory.TABLE_NAME + "/#", URI_SINGLE_PATH_CODE);
        uriMatcher.addURI(AUTHORITY, TableAlarmHistory.TABLE_NAME, URI_MULTI_PATH_CODE);

    }

    private DBHelper dbHelper = null;
    private ContentResolver resolver = null;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        resolver = context.getContentResolver();
        dbHelper = DBHelper.getInstance(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_MULTI_PATH_CODE:
                return "vnd.android.cursor.dir/vnd.linuxgg.elemental";
            case URI_SINGLE_PATH_CODE:
                return "vnd.android.cursor.item/vnd.linuxgg.elemental";


            default:
                throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String tableName = "";
        switch (uriMatcher.match(uri)) {
            case URI_MULTI_PATH_CODE:
            case URI_SINGLE_PATH_CODE:
                tableName = TableAlarmHistory.TABLE_NAME;
                break;
            default:
                return null;

        }

        long id = sqLiteDatabase.insert(tableName, BaseTable.ID, contentValues);

        Uri newUri = ContentUris.withAppendedId(uri, id);
        resolver.notifyChange(newUri, null);
        return newUri;
    }


    @Override
    public int delete(Uri uri, String selection, String[] strings) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int delAmount = 0;
        String rowId;
        switch (uriMatcher.match(uri)) {
            case URI_SINGLE_PATH_CODE:
                selection = BaseTable.ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "");
                Log.d(TAG, "selection:" + selection);
                delAmount = sqLiteDatabase.delete(TableAlarmHistory.TABLE_NAME, selection, strings);
                break;

            case URI_MULTI_PATH_CODE:
                delAmount = sqLiteDatabase.delete(TableAlarmHistory.TABLE_NAME, selection, strings);
                break;
            default:
                return delAmount;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return delAmount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }


}
