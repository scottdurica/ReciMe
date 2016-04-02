package com.emrox_riprap.recime.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by scott on 11/30/2015.
 */
public class RecipeProvider extends ContentProvider {

    private DbHelper mOpenHelper;

    private static final int RECIPES = 100;
    private static final int RECIPE_ID = 101;
    private static final int INGREDIENTS = 102;
    private static final int INGREDIENT_ID = 103;
    private static final int CATEGORIES = 104;
    private static final int CATEGORY_ID = 105;

    private static final UriMatcher sUriMatcher = getsUriMatcher();


    private static UriMatcher getsUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_RECIPES,RECIPES);
        uriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_RECIPES + "/#", RECIPE_ID);
        uriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_INGREDIENTS, INGREDIENTS);
        uriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_INGREDIENTS + "/#", INGREDIENT_ID);
        uriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_CATEGORIES,CATEGORIES);
        uriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_CATEGORIES + "/#", CATEGORY_ID);
        return uriMatcher;
    }
    @Override
    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor = null;
        switch (sUriMatcher.match(uri)){
            case RECIPES: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        DbContract.Recipes.RECIPES_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case RECIPE_ID: {
                getSingleItem(uri, projection,sortOrder,RECIPE_ID);
                break;
            }
            case INGREDIENTS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                       DbContract.Items.ITEMS_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case INGREDIENT_ID: {
                getSingleItem(uri,projection,sortOrder, INGREDIENT_ID);
                break;
            }
            case CATEGORIES: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        DbContract.Categories.CATEGORIES_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case CATEGORY_ID: {
                getSingleItem(uri,projection,sortOrder,CATEGORY_ID);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);


        }
        return retCursor;
    }

    private Cursor getSingleItem (Uri uri, String[] projection, String sortOrder, int requester){
        switch (requester){
            case RECIPE_ID: {
                String id = DbContract.Recipes.getIdFromUri(uri);
                return mOpenHelper.getReadableDatabase().query(
                        DbContract.Recipes.RECIPES_TABLE,
                        projection,
                        "WHERE id = ?",
                        new String[]{id},
                        null,
                        null,
                        sortOrder

                );
            }
            case INGREDIENT_ID: {
                String id = DbContract.Items.getIdFromUri(uri);
                return mOpenHelper.getReadableDatabase().query(
                        DbContract.Items.ITEMS_TABLE,
                        projection,
                        "WHERE id = ?",
                        new String[]{id},
                        null,
                        null,
                        sortOrder

                );
            }
            case CATEGORY_ID: {
                String id = DbContract.Categories.getIdFromUri(uri);
                return mOpenHelper.getReadableDatabase().query(
                        DbContract.Categories.CATEGORIES_TABLE,
                        projection,
                        "WHERE id = ?",
                        new String[]{id},
                        null,
                        null,
                        sortOrder

                );
            }
            default:
                throw new UnsupportedOperationException("Couldn't retreive data from " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)){
            case RECIPES:
                return DbContract.Recipes.CONTENT_TYPE;
            case RECIPE_ID:
                return DbContract.Recipes.CONTENT_ITEM_TYPE;
            case INGREDIENTS:
                return DbContract.Items.CONTENT_TYPE;
            case INGREDIENT_ID:
                return DbContract.Items.CONTENT_ITEM_TYPE;
            case CATEGORIES:
                return DbContract.Categories.CONTENT_TYPE;
            case CATEGORY_ID:
                return DbContract.Categories.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri = null;
        switch (match){
            case RECIPES: {
                long _id = db.insert(DbContract.Recipes.RECIPES_TABLE,null,values);
                if (_id > 0){
                    returnUri = DbContract.Recipes.buildUri(_id);
                    Toast.makeText(getContext(), "Inserted into Recipes DB", Toast.LENGTH_SHORT).show();

                }
                else
                    throw new android.database.SQLException("Failed to insert data into " + uri);
                break;
            }
            case INGREDIENTS: {
                long _id = db.insert(DbContract.Items.ITEMS_TABLE,null,values);
                if (_id > 0){
                    returnUri = DbContract.Items.buildUri(_id);
                    Toast.makeText(getContext(), "Inserted into Items DB", Toast.LENGTH_SHORT).show();

                }
                else
                    throw new android.database.SQLException("Failed to insert data into " + uri);
                break;
            }
            case CATEGORIES: {
                long _id = db.insert(DbContract.Categories.CATEGORIES_TABLE,null,values);
                if (_id > 0){
                    returnUri = DbContract.Categories.buildUri(_id);
                    Toast.makeText(getContext(), "Inserted into Items DB", Toast.LENGTH_SHORT).show();

                }
                else
                    throw new android.database.SQLException("Failed to insert data into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown  uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int success=0;

        switch (match){
            case RECIPES: {
                success = db.delete(DbContract.Recipes.RECIPES_TABLE,
                        selection,
                        selectionArgs
                );
                break;
            }
            case INGREDIENTS: {
                success = db.delete(DbContract.Items.ITEMS_TABLE,
                        selection,
                        selectionArgs
                );
                break;
            }
            case CATEGORIES: {
                success = db.delete(DbContract.Categories.CATEGORIES_TABLE,
                        selection,
                        selectionArgs
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (success>0){
            Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT).show();
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return success;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
