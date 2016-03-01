package com.adrian.androiddeveloper.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrian.androiddeveloper.R;
import com.adrian.androiddeveloper.views.utils.FragmentParams;
import com.adrian.androiddeveloper.views.utils.IActivityAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 2016-02-25.
 *
 * Ten fragment jest bazą dla pozostałych fragmentów.
 * Tylko wtedy gdy aktywność znajduje się w stanie 'Resumed', cykl życia fragmentu może zmieniać się niezależnie.
 *
 *
 * Klasa jest generyczna ponieważ chce aby zawierała obiekt pochodny od klasy 'FragmentParams' zawierający info
 * o parametrach które będę przekazywał do konkretnych fragmentów!!!
 * Nie jest to do końca konieczne ponieważ przy tworzeniu fragmentu nie należy przekazywać argumentu w konstruktorze
 * i przypisywać go do zmiennej skladowej klasy.
 *
 * Natomiast mogę zastosować podejście użycia metody generycznej przy tworzeniu nowej instancji fragmentu 'newInstance()'
 *
 */
public class RootFragment extends Fragment {

    public IActivityAccess mActivityAccess;
    //protected T mFragmentParams; - próba w przypadku gdy RootFragment byłby klasą generyczną

    /**
     * Poniżej tworze nowy obiekt fragmentu. Ponieważ przy tworzeniu fragmentów nie powinienem przekazywać
     * parametrów za pomocą konstruktora, robię to za pomocą metody 'setArguments' fragmentu. Jako argumenty
     * będą umieszczane obikety pochodne od 'FragmentParams'
     *
     * Metoda ta jest metodą generyczną. Ważne jest aby przed typem zwracanym z metody umieścić 'parametry określające typ'
     * */

    public static <T extends FragmentParams> RootFragment newInstance(T params){
        RootFragment fragment = new RootFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FragmentParams.FRAG_PARAMS_TAG,params);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void getFragmentParams(){
        getArguments().getParcelable(FragmentParams.FRAG_PARAMS_TAG);
    }

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
     * Mogę w niej sprawdzić np. czy dany komponent UI jest w layoucie i na tej podstawie zbudować inny
     * interfejs
     * */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * w tej metodzie mogę zachować stan pewnych zmiennych składowych fragmentu, a następnie
     * odtworzyć je w metodzie 'onCreate()' bądź 'onActivityCreated()'
     * */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
