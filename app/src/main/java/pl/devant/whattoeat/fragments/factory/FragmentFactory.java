package pl.devant.whattoeat.fragments.factory;

import android.support.v4.app.Fragment;

import pl.devant.whattoeat.fragments.HomeFragment;
import pl.devant.whattoeat.fragments.MapFragment;
import pl.devant.whattoeat.fragments.RandomFragment;


/**
 * Created by thomas on 09.03.18.
 */

public class FragmentFactory extends AbstractFactory {
    /**
     * implementation of the AbstractFactory pattern is used to create fragments
     */
    @Override
    public Fragment getFragment(int position) {
        String fragment;
        switch (position){
            case 0: fragment = "HomeFragment";
                break;
            case 1: fragment = "RandomFragment";
                break;
            case 2: fragment = "MapFragment";
                break;
                default: fragment = null;
        }

        if(fragment == null){
            return null;
        }

        if(fragment.equalsIgnoreCase("HomeFragment")){
            return new HomeFragment();
        }else if(fragment.equalsIgnoreCase("RandomFragment")){
            return new RandomFragment();
        }else if(fragment.equalsIgnoreCase("MapFragment")){
            return new MapFragment();
        }
        return null;
    }
}
