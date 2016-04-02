package com.emrox_riprap.recime;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class NRFourCookTimeFragment extends Fragment {

    NumberPicker mCookTimePicker;

    public NRFourCookTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nrfour_cook_time, container, false);
        mCookTimePicker = (NumberPicker)rootView.findViewById(R.id.np_cook_time);
        String[] values = new String[]{"00:20","00:25","00:30","00:35","00:40","00:45","00:50","00:55",
                "01:00","01:05","01:10","01:15","01:20","01:25","01:30","01:35","01:40","01:45","01:50","01:55",
                "02:00","02:05","02:10","02:15","02:20","02:25","02:30","02:35","02:40","02:45","02:50","02:55",
                "03:00","03:05","03:10","03:15","03:20","03:25","03:30","03:35","03:40","03:45","03:50","03:55","04:00"};


        mCookTimePicker.setMinValue(1);
        mCookTimePicker.setMaxValue(45);
        mCookTimePicker.setDisplayedValues(values);

        return rootView;
    }

}
