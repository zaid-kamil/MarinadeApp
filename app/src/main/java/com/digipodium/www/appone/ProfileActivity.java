package com.digipodium.www.appone;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseActivity {

    private List<Profile_recycle_model> itm_lst;
    private RecyclerView rc_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profile_activity_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        itm_lst = new ArrayList<>();
        rc_view = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
        rc_view.setLayoutManager(manager);

        generatelist();

    }

    private void generatelist() {
        itm_lst.add(new Profile_recycle_model("Wishlist"));
        itm_lst.add(new Profile_recycle_model("Orders"));
        itm_lst.add(new Profile_recycle_model("Payment History"));
        itm_lst.add(new Profile_recycle_model("Adresses"));
        itm_lst.add(new Profile_recycle_model("Logout"));
        Profile_adapter adapter = new Profile_adapter(itm_lst);
        rc_view.setAdapter(adapter);

    }
}
