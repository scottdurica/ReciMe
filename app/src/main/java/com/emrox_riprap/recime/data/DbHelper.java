package com.emrox_riprap.recime.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by scott on 11/30/2015.
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Recime.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    private static final String CREATE_RECIPES_TABLE = "CREATE TABLE " + DbContract.Recipes.RECIPES_TABLE +
            " (" +
            DbContract.Recipes._ID + " INTEGER PRIMARY KEY, " +
            DbContract.Recipes.COLUMN_NAME + " TEXT, " +
            DbContract.Recipes.COLUMN_DIRECTIONS + " TEXT, " +
            DbContract.Recipes.COLUMN_COOK_TIME + " INTEGER, " +
            DbContract.Recipes.COLUMN_CATEGORY + " INTEGER, " +
            DbContract.Recipes.COLUMN_OVEN_TEMP + " INTEGER, " +
            DbContract.Recipes.COLUMN_SUMMARY + " TEXT "
            +")";
    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + DbContract.Items.ITEMS_TABLE +
            " (" +
            DbContract.Items._ID + " INTEGER PRIMARY KEY, " +
            DbContract.Items.COLUMN_NAME + " TEXT, " +
            DbContract.Items.COLUMN_RECIPE_ID + " TEXT, " +
            DbContract.Items.COLUMN_QUANTITY + " TEXT "
            +")";
    private static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + DbContract.Categories.CATEGORIES_TABLE +
            " (" +
            DbContract.Categories._ID + " INTEGER PRIMARY KEY, " +
            DbContract.Categories.COLUMN_NAME + " TEXT "
            +")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.Recipes.RECIPES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.Items.ITEMS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.Categories.CATEGORIES_TABLE);

    }
}
