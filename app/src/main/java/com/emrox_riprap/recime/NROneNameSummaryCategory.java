package com.emrox_riprap.recime;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.emrox_riprap.recime.data.DbContract;


public class NROneNameSummaryCategory extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int CATEGORIES_LOADER = 1;
    private SimpleCursorAdapter mAdapter;

    EditText mName,mSummary;
    Spinner mSpinner;
    ViewGroup theseViews;



    public NROneNameSummaryCategory() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(CATEGORIES_LOADER, null, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nrone_name_summary_category, container, false);
        mSpinner = (Spinner) rootView.findViewById(R.id.s_categories);
        mName = (EditText)rootView.findViewById(R.id.et_nr_name);
        mSummary = (EditText)rootView.findViewById(R.id.et_nr_description);
        int [] listItemViewIds = {android.R.id.text1};
        String [] colNames = {DbContract.Categories.COLUMN_NAME};
        mAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                null,
                colNames,
                listItemViewIds,
                0 );
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(getActivity());
                return false;
            }
        });

        theseViews = container;
        return rootView;

    }
    public String[] getInputValuesForValidation(){
        String spinnerVal;
        if (mSpinner.getSelectedItemPosition()==0){
            spinnerVal = "";
        }else{
            spinnerVal = mSpinner.getItemAtPosition(mSpinner.getSelectedItemPosition()).toString();
        }
        String[] values = new String[]{mName.getText().toString(),mSummary.getText().toString(),spinnerVal};


        return values;
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(
                getActivity(),
                DbContract.Categories.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ((SimpleCursorAdapter)mAdapter).swapCursor(data);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SimpleCursorAdapter)mAdapter).swapCursor(null);

    }

}
