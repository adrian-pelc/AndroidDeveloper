package com.adrian.androiddeveloper.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrian.androiddeveloper.R;
import com.adrian.androiddeveloper.views.utils.IActivityAccess;

/**
 * Created by Adrian on 2016-02-25.
 *
 * Ten fragment jest bazą dla pozostałych fragmentów.
 * Tylko wtedy gdy aktywność znajduje się w stanie 'Resumed', cykl życia fragmentu może zmieniać się niezależnie.
 *
 */
public abstract class RootFragment extends Fragment {

    public IActivityAccess mActivityAccess;

    /**
     * Metoda ta jest wywoływana kiedy fragment zostaje powiązany z aktywnością
     * */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //za pomocą implementacji w tej metodzie upewniam się że
        //hostująca aktywność na pewno implemntuje odpowiedni interfejs potrzebny do komunikcji
        try{
            mActivityAccess = (IActivityAccess)activity;
            mActivityAccess.fragmentAttached(getClass().getSimpleName());
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    /**
     * Metoda ta jest wywoływana kiedy sterowanie zostaje zwrócone z metody onCreate aktywności
     * */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
