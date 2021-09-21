package com.oim.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Bloc de déclaration des éléments de ma base de données
    private static final String DATABASE_NAME = "events.db";
    // numero de version de la bdd
    private static int DB_VERSION = 1;

    // bloc de declaration elements bdd - table "interns"
    static final String TABLE_INTERN = "intern";
    static final String TABLE_INTERN_COLUMN_ID = "id";
    static final String TABLE_INTERN_COLUMN_NAME = "name";
    static final String TABLE_INTERN_COLUMN_INTERN_FIRST_NAME = "first_name";

    // bloc de declaration elements bdd - table "event"
    static final String TABLE_EVENT = "event";
    static final String TABLE_EVENT_COLUMN_ID = "id";
    static final String TABLE_EVENT_COLUMN_EVENT_NAME = "nameEvent";
    static final String TABLE_EVENT_COLUMN_DATE = "date";
    static final String TABLE_EVENT_COLUMN_PLACE = "place";
    static final String TABLE_EVENT_COLUMN_INTERN_ID = "internId";

    // Je déclare mes méthodes d'accès à la BDD (pour ne pas avoir a le retap
    // er sur chaque fonction
    private SQLiteDatabase dbWrite = this.getWritableDatabase();
    private SQLiteDatabase dbRead = this.getReadableDatabase();

    // Constructeur
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // OnCreate : c'est ici que vous créez les tables de votre BDD
    // Creation de la table intern
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creation de la table intern
        db.execSQL("CREATE TABLE " + TABLE_INTERN
                + " ( " + TABLE_INTERN_COLUMN_ID + " integer primary key, "
                + TABLE_INTERN_COLUMN_INTERN_FIRST_NAME + " text , "
                + TABLE_INTERN_COLUMN_NAME + " text )"
        );

        // Creation de la table event
        db.execSQL("CREATE TABLE " + TABLE_EVENT
                + " ( " + TABLE_EVENT_COLUMN_ID + " integer primary key, "
                + TABLE_EVENT_COLUMN_EVENT_NAME + " text , "
                + TABLE_EVENT_COLUMN_DATE + " date ,  "
                + TABLE_EVENT_COLUMN_PLACE + " text , "
                + TABLE_EVENT_COLUMN_INTERN_ID + "integer, "
                + "FOREIGN KEY ("+TABLE_EVENT_COLUMN_INTERN_ID+") REFERENCES  "+TABLE_INTERN+"("+TABLE_INTERN_COLUMN_ID+"));"
        );
    }

    // En cas de changement de version, suppression et re création de la table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERN);
        onCreate(db);
    }
}
