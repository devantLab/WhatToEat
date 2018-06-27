package pl.devant.whattoeat.fragments.factory;


import android.support.v4.app.Fragment;

/**
 * Created by thomas on 09.03.18.
 */

public abstract class AbstractFactory {

    abstract Fragment getFragment(int position);

}
