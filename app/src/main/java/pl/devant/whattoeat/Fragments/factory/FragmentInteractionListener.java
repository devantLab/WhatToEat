package pl.devant.whattoeat.Fragments.factory;

import pl.devant.whattoeat.Fragments.HomeFragment;
import pl.devant.whattoeat.Fragments.MapFragment;
import pl.devant.whattoeat.Fragments.RandomFragment;

/**
 * Created by thomas on 16.06.18.
 */

public interface FragmentInteractionListener extends RandomFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener {
}
