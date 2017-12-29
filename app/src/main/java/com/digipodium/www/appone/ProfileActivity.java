package com.digipodium.www.appone;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.digipodium.www.appone.models.ProfileOption;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseActivity {

    private List<ProfileOption> profileOptions;
    private RecyclerView rc_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profile_activity_toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this, "work in progress", Toast.LENGTH_SHORT).show();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        profileOptions = new ArrayList<>();
        rc_view = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
        rc_view.setLayoutManager(manager);

        generatelist();

    }

    private void generatelist() {
        profileOptions.add(new ProfileOption("Wishlist"));
        profileOptions.add(new ProfileOption("Orders"));
        profileOptions.add(new ProfileOption("Payment History"));
        profileOptions.add(new ProfileOption("Adresses"));
        profileOptions.add(new ProfileOption("Logout"));
        ProfileAdapter adapter = new ProfileAdapter(profileOptions);
        rc_view.setAdapter(adapter);

    }
}
