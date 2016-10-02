package timealarm.linuxgg.com.timealarm.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import timealarm.linuxgg.com.timealarm.R;
import timealarm.linuxgg.com.timealarm.db.TableAlarmHistory;

/**
 * Created by tom on 2016/10/3.<p>
 * Version 1.0 <p>
 * Desc :    <p>
 */
public class AlarmHistoryAdapter extends CursorAdapter {
    public AlarmHistoryAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public AlarmHistoryAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public AlarmHistoryAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.alarmhistory_item_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.recoredId = (TextView) v.findViewById(R.id.alarmlist_list_item_id);
        viewHolder.desc = (TextView) v.findViewById(R.id.alarmlist_list_item_desc);
        viewHolder.start = (TextView) v.findViewById(R.id.alarmlist_list_item_start);
        viewHolder.end = (TextView) v.findViewById(R.id.alarmlist_list_item_end);
        viewHolder.hasAudio = (TextView) v.findViewById(R.id.alarmlist_list_item_has_audio);
        v.setTag(viewHolder);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.recoredId.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.ID)));
        viewHolder.desc.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.DESC)));
        viewHolder.start.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.START_TIME)));
        viewHolder.end.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.END_TIME)));
        viewHolder.hasAudio.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableAlarmHistory.HAS_AUDIO)));

    }

    public class ViewHolder {
        public TextView recoredId;
        public TextView desc;
        public TextView start;
        public TextView end;
        public TextView hasAudio;
    }
}
