package com.emrox_riprap.recime.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by scott on 11/30/2015.
 */
public class DbContract {

    public static final String CONTENT_AUTHORITY = "com.emrox_riprap.recime";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPES = "recipes";
    public static final String PATH_INGREDIENTS = "items";
    public static final String PATH_CATEGORIES = "categories";

    public static final class Recipes implements BaseColumns{

        public static final String RECIPES_TABLE = "recipes";

        public static final String COLUMN_NAME = "recipe_name";
        public static final String COLUMN_DIRECTIONS = "directions";
        public static final String COLUMN_COOK_TIME = "cook_time";
        public static final String COLUMN_OVEN_TEMP = "oven_temp";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_SUMMARY = "summary";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;

        public static final Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static String getIdFromUri(Uri uri){return uri.getPathSegments().get(1);}
    }

    public static final class Items implements BaseColumns{

        public static final String ITEMS_TABLE = "ingredients";

        public static final String COLUMN_NAME = "ingredient_name";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENTS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_INGREDIENTS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_INGREDIENTS;

        public static final Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static String getIdFromUri(Uri uri){return uri.getPathSegments().get(1);}
    }
    public static final class Categories implements BaseColumns{

        public static final String CATEGORIES_TABLE = "categories";

        public static final String COLUMN_NAME = "item_name";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORIES).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;

        public static final Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static String getIdFromUri(Uri uri){return uri.getPathSegments().get(1);}
    }
}


