package com.hr.fire.inspection.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hr.fire.inspection.R;

public class CarbonFragment2 extends Fragment {
    View rootView;
    private static CarbonFragment2 fragment2;
    private static String mKey;

    public static CarbonFragment2 newInstance(String key, String value) {
        if (fragment2 == null) {
            fragment2 = new CarbonFragment2();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putString(key, value);
        fragment2.setArguments(args);
        return fragment2;
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
            rootView = inflater.inflate(R.layout.fragment_carbon2, container, false);
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

//        if (rootView != null) {
//            ((ViewGroup) rootView.getParent()).removeView(rootView);
//        }
    }
}
