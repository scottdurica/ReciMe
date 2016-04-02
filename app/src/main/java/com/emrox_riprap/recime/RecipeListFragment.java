package com.emrox_riprap.recime;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.emrox_riprap.recime.data.DbContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipeListFragment extends ListFragment implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final int RECIPES_LOADER = 1;
    private SimpleCursorAdapter mAdapter;

    public RecipeListFragment() {
    }
    public static RecipeListFragment newInstance(Bundle bundle){
        RecipeListFragment fragment = new RecipeListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(RECIPES_LOADER,null,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_recipe_list, container, false);
//        mCoordinatorLayout = (CoordinatorLayout)rootView.findViewById(R.id.log_list_coord_layout);
//        int [] listItemViewIds = {R.id.tv_recipe_name, R.id.iv_recipe_image, R.id.tv_recipe_time, R.id.tv_recipe_description,};
        int [] listItemViewIds = {R.id.tv_recipe_name, R.id.tv_recipe_description,};
        String [] colNames = {DbContract.Recipes.COLUMN_NAME,DbContract.Recipes.COLUMN_SUMMARY};
        mAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item_recipe,
                null,
                colNames,
                listItemViewIds,
                0 );
        setListAdapter(mAdapter);


//        mFab = (FloatingActionButton)rootView.findViewById(R.id.fab_add_record);
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //go  to new fragment
//                //              addTestData();
//                //              insertIntoCal();
//                DoWhatFragment fragment= new DoWhatFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.container,fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//
//            }
//        });

        return rootView;    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(
                getActivity(),
                DbContract.Recipes.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ((SimpleCursorAdapter)this.getListAdapter()).swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SimpleCursorAdapter)this.getListAdapter()).swapCursor(null);

    }
}
