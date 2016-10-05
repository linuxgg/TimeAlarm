package timealarm.linuxgg.com.timealarm.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import timealarm.linuxgg.com.timealarm.R;
import timealarm.linuxgg.com.timealarm.adapter.AlarmHistoryAdapter;
import timealarm.linuxgg.com.timealarm.db.TableAlarmHistory;

/**
 * Created by tom on 2016/10/2.<p>
 * Version 1.0 <p>
 * Desc :    <p>
 */
public class AlarmListFragment extends BaseFragment {
    private ListView alarmlistList;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.alarmlist_layout, null);
        alarmlistList = (ListView) rootView.findViewById(R.id.alarmlist_list);
        initDATA();
        return rootView;
    }

    private void initDATA() {
        try {
            c = getActivity().getContentResolver().query(TableAlarmHistory.URI, null, null, null, null);

            if (c != null && c.moveToFirst()) {
                do {
                    Log.d("", c.getString(c.getColumnIndex(TableAlarmHistory.START_TIME)));
                } while (c.moveToNext());
            }

            AlarmHistoryAdapter alarmsAdapter = new AlarmHistoryAdapter(getActivity(), c, true);

            alarmlistList.setAdapter(alarmsAdapter);
//            alarmsAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }


        alarmlistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                c.moveToPosition(i);
                final int recordID = c.getInt(c.getColumnIndexOrThrow(TableAlarmHistory.ID));
                Uri delRecord = Uri.parse(TableAlarmHistory.URI + "/" + recordID);
                getActivity().getContentResolver().delete(delRecord, null, null);

                Snackbar.make(view, "TODO: del " + i, Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
//        alarmsAdapter.notifyDataSetChanged();


    }

    private Cursor c;


    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (null != c) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
