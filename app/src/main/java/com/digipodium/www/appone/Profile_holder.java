package com.digipodium.www.appone;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Profile_holder extends RecyclerView.ViewHolder{

    TextView textView;
    View v;

    public Profile_holder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.profile_button);
        v = itemView.findViewById(R.id.Container);
    }
}
