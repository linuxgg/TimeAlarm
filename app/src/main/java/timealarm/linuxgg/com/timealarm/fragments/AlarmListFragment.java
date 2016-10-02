package timealarm.linuxgg.com.timealarm.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();



        Cursor c = null;
        try {
            c = getActivity().getContentResolver().query(TableAlarmHistory.URI, null, null, null, null);
            AlarmHistoryAdapter alarmsAdapter = new AlarmHistoryAdapter(getActivity(), c, true);

            alarmlistList.setAdapter(alarmsAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != c) {
                c.close();
            }
        }


        alarmlistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar.make(view, "TODO: del", Snackbar.LENGTH_LONG)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        })
                        .show();
            }
        });
    }
}
