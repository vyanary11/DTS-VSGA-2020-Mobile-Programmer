package com.pratamatechnocraft.aplikasidatabasesqlite.adapter;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pratamatechnocraft.aplikasidatabasesqlite.R;
import com.pratamatechnocraft.aplikasidatabasesqlite.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater==null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null){
            view = inflater.inflate(R.layout.list_row, null);
        }

        TextView id = view.findViewById(R.id.id);
        TextView name = view.findViewById(R.id.name);
        TextView address = view.findViewById(R.id.address);

        Data data = items.get(i);

        id.setText(data.getId());
        name.setText(data.getName());
        address.setText(data.getAddress());

        return view;
    }
}
