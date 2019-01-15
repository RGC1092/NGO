package in.evoqis.ngo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import in.evoqis.ngo.Adapter.ActivitiesRecyclerViewAdapter;
import in.evoqis.ngo.Fragments.dummy.DummyContent;
import in.evoqis.ngo.R;
import in.evoqis.ngo.RecentActivities;


public class FragmentActivities extends Fragment {

    private RecyclerView recyclerView;
    private ActivitiesRecyclerViewAdapter adapter;
    private List<RecentActivities> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new ActivitiesRecyclerViewAdapter(list, getContext());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        String date = "Sat 12 January 2019";
        String time = "14:30 – 17:30 GMT";
        String name = "TRUTH WILL Be TOLD";
        String place = "Venue : ORTUS Conferencing and Events Venue,\n   82-96 Grove Lane, London.\n   SE5 8SN,United Kingdom";
        for (int i = 0; i < 10; i++) {
            list.add(new RecentActivities(date, time, name, place));
        }

        recyclerView.setAdapter(adapter);
        return view;
    }


}