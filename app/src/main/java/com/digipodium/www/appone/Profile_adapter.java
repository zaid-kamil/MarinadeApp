package com.digipodium.www.appone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Profile_adapter extends RecyclerView.Adapter<Profile_holder>{

    private List<Profile_recycle_model> itm_list;

    public Profile_adapter(List<Profile_recycle_model> list){
        itm_list = list;
    }

    @Override
    public Profile_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = ((LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.recycle_model, parent, false);
        return new Profile_holder(row);
    }

    @Override
    public void onBindViewHolder(Profile_holder holder, int position) {
        final Profile_recycle_model model = itm_list.get(position);
        holder.textView.setText(model.text);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //what happens after clicking on layout of items of recyclerview in profile activity.
            }
        });
    }

    @Override
    public int getItemCount() {
        return itm_list.size();
    }
}
