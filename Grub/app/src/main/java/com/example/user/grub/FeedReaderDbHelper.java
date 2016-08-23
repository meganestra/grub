//package com.example.user.grub;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by user on 21/08/2016.
// */
//public class FeedReaderDbHelper extends SQLiteOpenHelper {
//
//    private static final String TEXT_TYPE = "TEXT";
//    private static final String COMMA_SEP = ",";
//    private static final String SQL_CREATE_ENTRIES =
//            "CREATE TABLE" + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
//                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATE_CONSUMED + TEXT_TYPE + COMMA_SEP +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_QUANTITY + TEXT_TYPE + COMMA_SEP +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_MEASURE + TEXT_TYPE + COMMA_SEP +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_CALORIES + TEXT_TYPE + COMMA_SEP +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_MEAL_TYPE + TEXT_TYPE + COMMA_SEP +
//                    " )";
//
//    private static final String SQL_DELETE_ENTRIES =
//            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;
//
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "FeedReader.db";
//
//    public FeedReaderDbHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQL_CREATE_ENTRIES);
//    }
//
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_ENTRIES);
//        onCreate(db);
//    }
//
//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onUpgrade(db, oldVersion, newVersion);
//    }
//
//}
