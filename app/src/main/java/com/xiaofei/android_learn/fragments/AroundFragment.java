package com.xiaofei.android_learn.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaofei.android_learn.R;
import com.xiaofei.android_learn.base.BaseFragment;

/**
 * Created by asus on 2016/8/27.
 */
public class AroundFragment extends BaseFragment implements View.OnClickListener {

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activiry_fragment, container, false);
        TextView tv = view.findViewById(R.id.content);
        tv.setText(this.getClass().getCanonicalName());
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
