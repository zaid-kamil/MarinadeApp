package com.digipodium.www.appone.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
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
public class SignUpFragment extends AuthFragment {


    protected List<TextInputEditText> views=new ArrayList<>();
    private VerticalTextView caption;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null){
            view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_sign_up));
            caption = view.findViewById(R.id.caption);

            TextInputEditText emailInputEdit=view.findViewById(R.id.email_input_edit);
            TextInputEditText passwordInputEdit=view.findViewById(R.id.password_input_edit);
            TextInputEditText confirmInputEdit=view.findViewById(R.id.confirm_password_edit);
            TextInputLayout emailInput=view.findViewById(R.id.email_input);
            TextInputLayout passInput=view.findViewById(R.id.password_input);
            TextInputLayout passConfirmInput=view.findViewById(R.id.confirm_password);
            views.add(emailInputEdit);
            views.add(passwordInputEdit);
            views.add(confirmInputEdit  );
            caption.setText(getString(R.string.sign_up_label));

            for(TextInputEditText editText:views){
                if(editText.getId()==R.id.password_input_edit){

                    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                    passInput.setTypeface(boldTypeface);
                    passConfirmInput.setTypeface(boldTypeface);
                    editText.addTextChangedListener(new TextWatcherAdapter(){
                        @Override
                        public void afterTextChanged(Editable editable) {
                            passConfirmInput.setPasswordVisibilityToggleEnabled(editable.length()>0);
                        }
                    });
                }
                editText.setOnFocusChangeListener((temp,hasFocus)->{
                    if(!hasFocus){
                        boolean isEnabled=editText.getText().length()>0;
                        editText.setSelected(isEnabled);
                    }
                });
            }

            //-------------validation
            AwesomeValidation mav = new AwesomeValidation(TEXT_INPUT_LAYOUT);
            mav.addValidation(emailInput, Patterns.EMAIL_ADDRESS, "email is invalid");
            String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
            mav.addValidation(passInput, regexPassword, "password is invalid");
            mav.addValidation(getActivity(),R.id.password_input, R.id.confirm_password, R.string.err_password_confirmation);

            //-----------------------
            caption.setVerticalText(true);
            foldStuff();
            caption.setTranslationX(getTextPadding());
            caption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (caption.isTopDown()) {
                        mav.validate();
                        String password = passwordInputEdit.getText().toString();
                        String email = emailInputEdit.getText().toString();
                        Toast.makeText(getContext(), email + "" + password, Toast.LENGTH_SHORT).show();

                    }else{
                        unfold();
                    }
                }
            });
        }
    }

    @Override
    public int authLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void clearFocus() {
        for(View view:views) view.clearFocus();
    }

    @Override
    public void fold() {
        lock=false;
        Rotate transition = new Rotate();
        transition.setEndAngle(-90f);
        transition.addTarget(caption);
        TransitionSet set=new TransitionSet();
        set.setDuration(getResources().getInteger(R.integer.duration));
        ChangeBounds changeBounds=new ChangeBounds();
        set.addTransition(changeBounds);
        set.addTransition(transition);
        TextSizeTransition sizeTransition=new TextSizeTransition();
        sizeTransition.addTarget(caption);
        set.addTransition(sizeTransition);
        set.setOrdering(TransitionSet.ORDERING_TOGETHER);
        set.addListener(new Transition.TransitionListenerAdapter(){
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                caption.setTranslationX(getTextPadding());
                caption.setRotation(0);
                caption.setVerticalText(true);
                caption.requestLayout();

            }
        });
        TransitionManager.beginDelayedTransition(parent,set);
        foldStuff();
        caption.setTranslationX(-caption.getWidth()/8+getTextPadding());
        caption.setTopDown(false);
    }

    private void foldStuff(){
        caption.setTextSize(TypedValue.COMPLEX_UNIT_PX,caption.getTextSize()/2f);
        caption.setTextColor(Color.WHITE);
        ConstraintLayout.LayoutParams params=getParams();
        params.rightToRight=ConstraintLayout.LayoutParams.UNSET;
        params.verticalBias=0.5f;
        caption.setLayoutParams(params);
    }

    private float getTextPadding(){
        return getResources().getDimension(R.dimen.folded_label_padding)/2.1f;
    }
}
