package pl.devant.whattoeat.factory;

import pl.devant.whattoeat.HomeFragment;
import pl.devant.whattoeat.MapFragment;
import pl.devant.whattoeat.RandomFragment;

/**
 * Created by thomas on 16.06.18.
 */

public interface FragmentInteractionListener extends RandomFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener {
}
