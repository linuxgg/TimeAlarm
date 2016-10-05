package timealarm.linuxgg.com.timealarm.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import timealarm.linuxgg.com.timealarm.R;
import timealarm.linuxgg.com.timealarm.db.TableAlarmHistory;

/**
 * Created by tom on 2016/10/3.<p>
 * Version 1.0 <p>
 * Desc :    <p>
 */
public class AlarmHistoryAdapter2 extends BaseAdapter {
    private final String TAG = AlarmHistoryAdapter2.class.getSimpleName();
    private Cursor c;
    private Context context;

    public AlarmHistoryAdapter2(Context context, Cursor c, boolean autoRequery) {

        Log.d(TAG, "in AlarmHistoryAdapter");
        this.c = c;
        this.context = context;
        Log.d(TAG, "in AlarmHistoryAdapter c::" + c.getCount());
    }

    @Override
    public int getCount() {
        return c.getCount();
    }

    @Override
    public Object getItem(int position) {
        if (c != null) {
            return c.moveToPosition(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (c != null) {
            if (c.moveToPosition(position)) {
                return c.getLong(c.getColumnIndexOrThrow(TableAlarmHistory.ID));
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        c.moveToPosition(position);
        View view;
        if (convertView == null) {
            view = newView(context, c, parent);
        } else {
            view = convertView;
        }
        bindView(view, context, c);
        return view;
    }


    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        Log.d(TAG, "adapter create view");

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.alarmhistory_item_layout, viewGroup, false);
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


    public void bindView(View view, Context context, Cursor cursor) {
        Log.d(TAG, "adapter bindView view");
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
