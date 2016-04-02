package com.emrox_riprap.recime;


import android.content.Context;
import android.os.Bundle;
import android.app.ListFragment;

import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.emrox_riprap.recime.models.Ingredient;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link ListFragment} subclass.
 */
public class NRTwoItemsFragment extends ListFragment {

    Spinner mQuantity,mMeasurementTool;
    EditText mIngredient;
    Button mAdd;
    ArrayList<Ingredient> mIngredientList;

    public NRTwoItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIngredientList = new ArrayList<Ingredient>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nrtwo_items, container, false);
        mQuantity = (Spinner)rootView.findViewById(R.id.s_amount);
        mMeasurementTool = (Spinner)rootView.findViewById(R.id.s_measurement);
        mIngredient = (EditText)rootView.findViewById(R.id.et_ingredient_name);

        setListAdapter(new IngredientListAdapter(getActivity(), mIngredientList));
        mAdd = (Button)rootView.findViewById(R.id.b_add);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mAdd.getWindowToken() , 0);

                if (Utilities.validateStringFields(getInputValuesForValidation())){
                    String name = mIngredient.getText().toString();
                    String amount = mQuantity.getSelectedItem().toString() + " " + mMeasurementTool.getSelectedItem().toString();
                    Ingredient item = new Ingredient(name, amount);
                    mIngredientList.add(item);
                    getListView().invalidateViews();
                    mIngredient.setText("");
                    mQuantity.setSelection(0);
                    mMeasurementTool.setSelection(0);

                }else {
                    Snackbar.make(v, "Complete all fields", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }



            }
        });


        return rootView;
    }
    public String[] getInputValuesForValidation(){
        String qualitySpinnerVal;
        String measurementToolVal;
        if (mQuantity.getSelectedItemPosition()==0){
            qualitySpinnerVal = "";
        }else{
            qualitySpinnerVal = mQuantity.getItemAtPosition(mQuantity.getSelectedItemPosition()).toString();
        }
        if (mMeasurementTool.getSelectedItemPosition()==0){
            measurementToolVal = "";
        }else{
            measurementToolVal = mMeasurementTool.getItemAtPosition(mMeasurementTool.getSelectedItemPosition()).toString();
        }
        String[] values = new String[]{mIngredient.getText().toString(),qualitySpinnerVal,measurementToolVal};


        return values;
    }
    private class IngredientListAdapter extends ArrayAdapter<Ingredient>{

        ViewHolder viewHolder;

        public IngredientListAdapter(Context context, List<Ingredient> items) {
            super(context, R.layout.list_item_ingredient, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                //create a view
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_item_ingredient,parent,false);

                viewHolder = new ViewHolder();
                viewHolder.ingredientName = (TextView)convertView.findViewById(R.id.tv_ingredient_name);
                viewHolder.amount = (TextView)convertView.findViewById(R.id.tv_ingredient_amount);
                convertView.setTag(viewHolder);
            }else{
                //Recycle the already created view
                viewHolder = (ViewHolder)convertView.getTag();
            }
            Ingredient ingredient = getItem(position);
            viewHolder.ingredientName.setText(ingredient.name);
            viewHolder.amount.setText(ingredient.amount);


            return convertView;

        }


    }
    private static class ViewHolder {
        TextView ingredientName;
        TextView amount;
    }
}
