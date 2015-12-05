package com.example.oz_fanalis.mycalendar;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Activity_List extends AppCompatActivity {

    private static final String CATEGORY_SAMPLE = "com.prolificinteractive.materialcalendarview.sample.SAMPLE";
    private Object allSampleActivities;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView list = (ListView) findViewById(R.id.list);
//        list.setLayoutManager(new LinearLayoutManager(this));
        ArrayList listings = new ArrayList();
        listings.add("monday");
        listings.add("tuesday");
        ArrayAdapter adapters = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listings);
        list.setAdapter(adapters);
//        list.setAdapter(new ResolveInfoAdapter(this, getAllSampleActivities()));
    }

    private List<ResolveInfo> getAllSampleActivities() {
        Intent filter = new Intent();
        filter.setAction(Intent.ACTION_RUN);
        filter.addCategory(CATEGORY_SAMPLE);
        return getPackageManager().queryIntentActivities(filter, 0);
    }


    class ResolveInfoAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
    class ResolveInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ResolveInfoViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            //Log Lang sa
        }
    }

}

