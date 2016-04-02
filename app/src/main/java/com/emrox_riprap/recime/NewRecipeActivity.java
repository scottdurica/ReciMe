package com.emrox_riprap.recime;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.emrox_riprap.recime.data.DbContract;
import com.emrox_riprap.recime.models.Ingredient;

import java.util.ArrayList;

public class NewRecipeActivity extends AppCompatActivity {

    ContentValues values;

    static boolean fieldsComplete;
    final static String ARG_NAME = DbContract.Recipes.COLUMN_NAME;
    final static String ARG_SUMMARY = DbContract.Recipes.COLUMN_SUMMARY;
    final static String ARG_CATEGORY = DbContract.Recipes.COLUMN_CATEGORY;
    static ArrayList<Ingredient> ingredientList;
    final static String ARG_OVEN_TEMP = DbContract.Recipes.COLUMN_OVEN_TEMP;
    final static String ARG_COOK_TIME = DbContract.Recipes.COLUMN_COOK_TIME;
    final static String ARG_DIRECTIONS = DbContract.Recipes.COLUMN_DIRECTIONS;




    public static final String COLUMN_SERVINGS = "yield";
    public static final String COLUMN_SUMMARY = "description";
    public static final String COLUMN_PREP_TIME ="prep_time";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        values = new ContentValues();
        setContentView(R.layout.activity_new_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



            if (savedInstanceState == null){
                getFragmentManager().beginTransaction()
                        .add(R.id.test_container,new NROneNameSummaryCategory())
                        .commit();
            }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment currentFragment = getFragmentManager().findFragmentById(R.id.test_container);
                if (currentFragment instanceof NROneNameSummaryCategory){
                    if (Utilities.validateStringFields(((NROneNameSummaryCategory) currentFragment).getInputValuesForValidation())){
                        values.put(ARG_NAME, ((NROneNameSummaryCategory) currentFragment).mName.getText().toString());
                        values.put(ARG_SUMMARY, ((NROneNameSummaryCategory) currentFragment).mSummary.getText().toString());
                        values.put(ARG_CATEGORY, ((NROneNameSummaryCategory) currentFragment)
                                .mSpinner.getItemAtPosition(((NROneNameSummaryCategory) currentFragment).mSpinner.getSelectedItemPosition()).toString());
                        NRTwoItemsFragment fragment = new NRTwoItemsFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.test_container,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                    else{
                        Snackbar.make(view, "Complete all fields", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }else if(currentFragment instanceof NRTwoItemsFragment){
                    if (((NRTwoItemsFragment) currentFragment).mIngredientList.size()==0){
                        Snackbar.make(view, "No ingredients = NO RECIPE!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else{
                        ingredientList = ((NRTwoItemsFragment) currentFragment).mIngredientList;

                        NRThreeOvenTempFragment fragment = new NRThreeOvenTempFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.test_container,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }else if(currentFragment instanceof NRThreeOvenTempFragment){
                    values.put(ARG_OVEN_TEMP, ((NRThreeOvenTempFragment)currentFragment).mOvenTempPicker.getValue());
                    NRFourCookTimeFragment fragment = new NRFourCookTimeFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.test_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else if(currentFragment instanceof NRFourCookTimeFragment){
                    values.put(ARG_COOK_TIME,((NRFourCookTimeFragment)currentFragment).mCookTimePicker.getValue());
                    NRFiveDirectionsFragment fragment = new NRFiveDirectionsFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.test_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else if (currentFragment instanceof NRFiveDirectionsFragment){
                    //save the recipe to the database, returnning the id in order to
                    //save the ingredients associative to the recipe.
                    String desc = ((NRFiveDirectionsFragment)currentFragment).mDirections.getText().toString();
                    if (Utilities.validateStringField(desc)){
                        values.put(ARG_DIRECTIONS,desc);
                        //save to db, return recipe id
                        Uri returnUri = getContentResolver().insert(DbContract.Recipes.CONTENT_URI, values);
//                        int id = Integer.getInteger(DbContract.Recipes.getIdFromUri(returnUri));
                       String id = DbContract.Recipes.getIdFromUri(returnUri);
                        Log.d("Value of recipe id is ",""+id);
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
