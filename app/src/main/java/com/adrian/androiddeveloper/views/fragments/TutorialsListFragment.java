package com.adrian.androiddeveloper.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrian.androiddeveloper.R;
import com.adrian.androiddeveloper.views.utils.FragmentParams;

/**
 * Created by Adrian on 2016-02-25.
 */
public class TutorialsListFragment extends RootFragment {


    /**
     * Trzeci parametr metody 'savedInstanceState' - zawiera informacje o poprzednim
     * stanie obiektu fragmentu, jeżeli fragment ten został ponownie przenesiony na pierwszy plan (resumed)
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_container,container,false);
        return rootView;
    }
}
