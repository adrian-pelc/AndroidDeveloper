package com.adrian.androiddeveloper.views.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.adrian.androiddeveloper.R;
import com.adrian.androiddeveloper.utils.Setting;
import com.adrian.androiddeveloper.views.fragments.RootFragment;
import com.adrian.androiddeveloper.views.fragments.TutorialsListFragment;
import com.adrian.androiddeveloper.views.utils.IActivityAccess;

/** Fragment tutorial
 * Główna aktywność której głównym zadaniem jest hostowanie fragmentu z listą.
 * Aby w widoku tej aktywności mogły być wyświetlane fragmenty, aktywność ta musi implementować
 * interfejs IActivityAccess którą zawiera bazowy fragment. Dzięki temu fragment może dostarczać
 * wiadomości do aktywności
 *
 * */
public class MainActivity extends Activity implements IActivityAccess {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sprawdzam czy aktywność używa layout-u, który ma odpowiedni kontener aby wyświetlić fragment
        if(findViewById(R.id.fragment_container) != null){
            //należy jeszcze sprawdzić czy aktywność nie jest odtwarzana. Jeżeli jest nie ma potrzeby
            //ponowne tworzenie fragmentu.
            if(savedInstanceState != null){
                return;
            }
            createInitialFragment();
        }
    }

    private void createInitialFragment(){
        TutorialsListFragment listFragment = new TutorialsListFragment();
        //mogę przekazać pewne argumenty do fragmentu za pomocą metody 'setArguments'.
        //Mają one postać obiektu Bundle
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putInt(Setting.KEY_SETUP_INITIAL_FRAGMENT,1);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,listFragment,Setting.TAG_INITIAL_FRAGMENT);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Fragments tutorial
     * Za pomocą metod interfejsu IActivityAccess mam dostęp do informacji z fragmentu
     * Może się zdarzyć że widok będzie posiadał statyczny fragment. Wtedy na podstawie wiadomości
     * z fragmentu zawierającego np. listę mogę znaleźć drugi fragment za pomocą metody 'FragmentManagera'
     * 'findFragmentById' i wykonać odpowiednią operację.
     *
     * Jeżeli w widoku nie będzie statycznego widoku należy utworzyć fragment który będzie wyświetlał odpowiednie
     * informacje i przeprowadzić transakcje. Tak będzie w moim przypadku.
     *
     * !!! Za pomocą metody 'addToBackStack' umożliwiam użytkownikowi wrócenie do poprzedniego fragmentu. Fragment w takim
     * przypadku zostaje zatrzymany (Paused state). Jeżeli tego nie zrobię fragment zostaje zniszczony.
     *
     * */
    @Override
    public void fragmentAttached(String fragmentName) {
        Log.i(this.getClass().getSimpleName(), "Fragment which is attached to window: " + fragmentName);
    }
}
