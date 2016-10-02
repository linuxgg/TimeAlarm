package timealarm.linuxgg.com.timealarm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import timealarm.linuxgg.com.timealarm.R;

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
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add("item: " + i);
        }
        ArrayAdapter<String> alarmsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);

        alarmlistList.setAdapter(alarmsAdapter);
    }
}
