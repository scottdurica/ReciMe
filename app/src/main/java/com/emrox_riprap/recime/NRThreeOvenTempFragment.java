package com.emrox_riprap.recime;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class NRThreeOvenTempFragment extends Fragment {

    NumberPicker mOvenTempPicker;

    public NRThreeOvenTempFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nrthree_oven_temp, container, false);
        mOvenTempPicker = (NumberPicker)rootView.findViewById(R.id.np_oven_temps);
        String[] values = new String[46];
        int step = 5;
        int minVal = 275;
        int maxVal = 500;
        int index =0;
        for (int i=minVal; i<=maxVal; i +=step){
            values[index]=String.valueOf(i);
            index ++;
        }

        mOvenTempPicker.setMinValue(1);
        mOvenTempPicker.setMaxValue(46);
        mOvenTempPicker.setDisplayedValues(values);

        return rootView;
    }

}
