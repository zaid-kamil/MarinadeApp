package com.digipodium.www.appone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digipodium.www.appone.models.ProfileOption;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.OptionHolder>{
    private List<ProfileOption> optionList;

  
    public ProfileAdapter(List<ProfileOption> optionList) {
        this.optionList = optionList;
    }

    @Override
    public OptionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = ((LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.card_option, parent, false);
        return new OptionHolder(row);
    }

    @Override
    public void onBindViewHolder(OptionHolder holder, int position) {
        ProfileOption model = optionList.get(position);
        holder.tvOption.setText(model.text);
        holder.ivMoveIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //what happens after clicking on layout of items of recyclerview in profile activity.

            }
        });
        holder.container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }
    
    class OptionHolder extends RecyclerView.ViewHolder{

        public TextView tvOption;
        public ImageView ivMoveIn;
        public ViewGroup container;

        public OptionHolder(View itemView) {
            super(itemView);
            tvOption=itemView.findViewById(R.id.tvOption);
            ivMoveIn=itemView.findViewById(R.id.ivMoveIn);
            container=itemView.findViewById(R.id.container);
        }
    }
}
