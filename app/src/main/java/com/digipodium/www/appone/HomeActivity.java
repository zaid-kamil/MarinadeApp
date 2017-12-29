package com.digipodium.www.appone;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aadira.library.Interfaces.ListCallback;
import com.aadira.library.Main.WooCommerce;
import com.digipodium.www.appone.adapters.UltraViewPagerAdapter;
import com.digipodium.www.appone.fragments.SliderFragment;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.List;

import retrofit.RetrofitError;

public class HomeActivity extends AppCompatActivity {

    private ViewGroup container;
    private ProgressBar pbLoader;
    private WooCommerce wooCommerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this, "work in progress", Toast.LENGTH_SHORT).show();
        UltraViewPager ultraViewPager = (UltraViewPager) findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);

        ultraViewPager.setAdapter(new UltraViewPagerAdapter(new SlideAdapter(getSupportFragmentManager())));
        ultraViewPager.initIndicator();
        //set style of indicators
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(R.color.colorPrimary)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //set the alignment
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //construct built-in indicator, and add it to  UltraViewPager
        ultraViewPager.getIndicator().build();

        //set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        ultraViewPager.setAutoScroll(2000);

        container = findViewById(R.id.container);
        pbLoader = findViewById(R.id.pbLoader);

        wooCommerce = WooCommerce.getInstance();
        startLoadingCategories();

        ImageView ivProfile=findViewById(R.id.ivProfile);
        ImageView ivCart=findViewById(R.id.ivCart);
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,CartActivity.class));
            }
        });
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
            }
        });
    }

    private void startLoadingCategories() {
       /* pbLoader.setVisibility(View.VISIBLE);
        container.setVisibility(View.INVISIBLE);
        wooCommerce.getAllCategories(new ListCallback() {
            @Override
            public void Callback(List<?> list, RetrofitError error) {
            }
        });*/
    }

    private class SlideAdapter extends FragmentStatePagerAdapter {
        private int[] images = {
                R.drawable.slider_1,
                R.drawable.slider_2,
                R.drawable.slider_3
        };

        public SlideAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return SliderFragment.newInstance(images[position]);
        }

        @Override
        public int getCount() {
            return images.length;
        }
    }
}
