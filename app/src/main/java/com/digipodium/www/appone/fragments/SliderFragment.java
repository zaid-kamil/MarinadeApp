package com.digipodium.www.appone.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.digipodium.www.appone.R;

public class SliderFragment extends Fragment {

    private static final String ARG_IMAGE_ID = "imageid";

    private int imgId;


    public SliderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imgId Parameter 1.
     * @return A new instance of fragment SliderFragment.
     */

    public static SliderFragment newInstance(int imgId) {
        SliderFragment fragment = new SliderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_ID, imgId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imgId = getArguments().getInt(ARG_IMAGE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        ImageView imgSlide = view.findViewById(R.id.imgSlide);
        Glide.with(this).load(imgId).into(imgSlide);
        return view;
    }

}
