package com.hr.fire.inspection.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hr.fire.inspection.R;

public class CarbonFragment5 extends Fragment {
    View rootView;
    private static CarbonFragment5 fragment5;
    private static String mKey;

    public static CarbonFragment5 newInstance(String key, String value) {
        if (fragment5 == null) {
            fragment5 = new CarbonFragment5();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putString(key, value);
        fragment5.setArguments(args);
        return fragment5;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String keyParameter = (String) getArguments().get(mKey);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_carbon5, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("dong", "onDestroyView: ");
//        boolean mIsFirstLoad = true;
//        boolean mIsPrepare = false;
//        boolean mIsVisible = false;
//        if (rootView != null) {
//            ((ViewGroup) rootView.getParent()).removeView(rootView);
//        }
    }
}
