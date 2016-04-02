package com.emrox_riprap.recime;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class NRFiveDirectionsFragment extends Fragment {

    EditText mDirections;

    public NRFiveDirectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nrfive_directions, container, false);
        mDirections = (EditText)rootView.findViewById(R.id.et_description);


        return rootView;
    }

}
