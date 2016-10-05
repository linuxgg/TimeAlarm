package timealarm.linuxgg.com.timealarm.fragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

            AlarmHistoryAdapter alarmsAdapter = new AlarmHistoryAdapter(getActivity(), c, true);

            alarmlistList.setAdapter(alarmsAdapter);
            alarmlistList.setEmptyView(rootView.findViewById(R.id.list_empty));
        } catch (Exception e) {
            e.printStackTrace();
        }


        alarmlistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Snackbar.make(rootView, "TODO:: will open detail page", Snackbar.LENGTH_LONG).show();
            }
        });


        alarmlistList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                c.moveToPosition(position);
                new AlertDialog.Builder(getActivity())
                        .setTitle("Warning!")
                        .setMessage("will del this item?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final int recordID = c.getInt(c.getColumnIndexOrThrow(TableAlarmHistory.ID));
                                Uri delRecord = Uri.parse(TableAlarmHistory.URI + "/" + recordID);
                                int delCount = getActivity().getContentResolver().delete(delRecord, null, null);
                                String delMessage = "Fail, please try again.";
                                if (delCount != 0) {
                                    delMessage = "Success!";
                                }
                                Snackbar.make(rootView, delMessage, Snackbar.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();


                return true;
            }
        });

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
