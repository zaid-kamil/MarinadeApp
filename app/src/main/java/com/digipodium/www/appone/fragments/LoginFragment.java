package com.digipodium.www.appone.fragments;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aadira.library.Main.WooCommerce;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.digipodium.www.appone.HomeActivity;
import com.digipodium.www.appone.R;
import com.digipodium.www.appone.customviews.VerticalTextView;
import com.digipodium.www.appone.utils.Rotate;
import com.digipodium.www.appone.utils.TextSizeTransition;
import com.digipodium.www.appone.utils.TextWatcherAdapter;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import java.util.ArrayList;
import java.util.List;

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends AuthFragment {


    protected List<TextInputEditText> views = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            final WooCommerce wooCommerce = WooCommerce.getInstance();
            VerticalTextView caption = view.findViewById(R.id.caption);
            Button btnGuestSkip = view.findViewById(R.id.btnGuestSkip);
            TextInputLayout emailInput = view.findViewById(R.id.email_input);
            TextInputLayout passInput = view.findViewById(R.id.password_input);
            TextInputEditText emailInputEdit = view.findViewById(R.id.email_input_edit);
            TextInputEditText passwordInputEdit = view.findViewById(R.id.password_input_edit);
            views.add(emailInputEdit);
            views.add(passwordInputEdit);
            caption.setText(getString(R.string.log_in_label));
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_log_in));
            //-------------validation
            AwesomeValidation mav = new AwesomeValidation(TEXT_INPUT_LAYOUT);
            mav.addValidation(emailInput, Patterns.EMAIL_ADDRESS, "email is invalid");
            String regexPassword = "(?=.*[0-9])(?=.*[a-z])(?=\\\\S+$).{8,}";
            mav.addValidation(passInput, regexPassword, "password is invalid");
            //-----------------------
            for (TextInputEditText editText : views) {
                if (editText.getId() == R.id.password_input_edit) {
                    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                    passInput.setTypeface(boldTypeface);
                    editText.addTextChangedListener(new TextWatcherAdapter() {
                        @Override
                        public void afterTextChanged(Editable editable) {
                            passInput.setPasswordVisibilityToggleEnabled(editable.length() > 0);
                        }
                    });
                }
                editText.setOnFocusChangeListener((temp, hasFocus) -> {
                    if (!hasFocus) {
                        boolean isEnabled = editText.getText().length() > 0;
                        editText.setSelected(isEnabled);
                    }
                });

            }
            caption.setTopDown(true);
            caption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (caption.isTopDown()) {
                        if (mav.validate()) {
                            String password = passwordInputEdit.getText().toString();
                            String email = emailInputEdit.getText().toString();
                            Toast.makeText(getContext(), email + "" + password, Toast.LENGTH_SHORT).show();
                        }

                    } else if (caption.isVerticalText()) {
                        unfold();
                    }
                }
            });
            btnGuestSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goHome();
                }
            });
        }

    }

    @Override
    public int authLayout() {
        return R.layout.fragment_login;
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fold() {
        lock = false;
        Rotate transition = new Rotate();
        transition.setEndAngle(-90f);
        transition.addTarget(caption);
        TransitionSet set = new TransitionSet();
        set.setDuration(getResources().getInteger(R.integer.duration));
        ChangeBounds changeBounds = new ChangeBounds();
        set.addTransition(changeBounds);
        set.addTransition(transition);
        TextSizeTransition sizeTransition = new TextSizeTransition();
        sizeTransition.addTarget(caption);
        set.addTransition(sizeTransition);
        set.setOrdering(TransitionSet.ORDERING_TOGETHER);
        final float padding = getResources().getDimension(R.dimen.folded_label_padding) / 2;
        set.addListener(new Transition.TransitionListenerAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                caption.setTranslationX(-padding);
                caption.setRotation(0);
                caption.setVerticalText(true);
                caption.requestLayout();

            }
        });
        TransitionManager.beginDelayedTransition(parent, set);
        caption.setTextSize(TypedValue.COMPLEX_UNIT_PX, caption.getTextSize() / 2);
        caption.setTextColor(Color.WHITE);
        ConstraintLayout.LayoutParams params = getParams();
        params.leftToLeft = ConstraintLayout.LayoutParams.UNSET;
        params.verticalBias = 0.5f;
        caption.setLayoutParams(params);
        caption.setTranslationX(caption.getWidth() / 8 - padding);
        caption.setTopDown(false);

    }

    @Override
    public void clearFocus() {
        for (View view : views) view.clearFocus();
    }


    public void goHome() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        try {
            getActivity().finish();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "some error occurred", Toast.LENGTH_SHORT).show();
        }
    }
}
