package iitropar.aarohan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "aarohan.db";

    // chat Room table
    public static final String TABLE_FIXTURES = "fixtureTable";
    public static final String COLUMN_FIXTURE_PK = "fixturePK";
    public static final String COLUMN_FIXTURE_TEAMA = "teamA";
    public static final String COLUMN_FIXTURE_TEAMB = "teamB";
    public static final String COLUMN_FIXTURE_VENUE = "venue";
    public static final String COLUMN_FIXTURE_TIME = "time";
    public static final String COLUMN_FIXTURE_DAY= "day";
    public static final String COLUMN_FIXTURE_TYPE = "type";
    



    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryChat = "CREATE TABLE " + TABLE_FIXTURES + "(" + COLUMN_FIXTURE_TEAMA + " TEXT,"
                + COLUMN_FIXTURE_PK + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIXTURE_VENUE + " TEXT,"
                + COLUMN_FIXTURE_TIME + " TEXT,"
                + COLUMN_FIXTURE_DAY+ " INTEGER,"
                + COLUMN_FIXTURE_TEAMB + " TEXT,"
                + COLUMN_FIXTURE_TYPE+ " INTEGER"
                + ");";
        db.execSQL(queryChat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIXTURES);
        onCreate(db);
    }


    // insert functions


    public long insertTableEvents(Event event) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_FIXTURE_TEAMA, event.getTeamA());
        initialValues.put(COLUMN_FIXTURE_VENUE, event.getVenue());
        initialValues.put(COLUMN_FIXTURE_TIME, event.getTime());
        initialValues.put(COLUMN_FIXTURE_DAY, event.getDay());
        initialValues.put(COLUMN_FIXTURE_TEAMB, event.getTeamB());
        initialValues.put(COLUMN_FIXTURE_TYPE, event.getType());



        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_FIXTURES, null, initialValues);
    }




    public void deleteEvent(int pK){
        String query = "DELETE FROM " +  TABLE_FIXTURES + " WHERE " + COLUMN_FIXTURE_PK + " = " + pK;
        getWritableDatabase().execSQL(query);

    }
    public void clearDatabase(){
        String query = "DELETE FROM " +  TABLE_FIXTURES ;
        getWritableDatabase().execSQL(query);
    }

    public ArrayList<Event> getData( ) {
        ArrayList<Event> dbstring = new ArrayList<Event>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FIXTURES ;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Event event = new Event();
            if (c.getString(c.getColumnIndex("fixturePK")) != null) {

                event.setPk(c.getInt(c.getColumnIndex("fixturePK")));
            }

            if (c.getString(c.getColumnIndex("teamA")) != null) {
                event.setTeamA(c.getString(c.getColumnIndex("teamA")));
            }
            if (c.getString(c.getColumnIndex("teamB")) != null) {
                event.setTeamB(c.getString(c.getColumnIndex("teamB")));
            }
            if (c.getString(c.getColumnIndex("venue")) != null) {
                event.setVenue(c.getString(c.getColumnIndex("venue")));
            }
            if (c.getString(c.getColumnIndex("time")) != null) {
                event.setTime(c.getString(c.getColumnIndex("time")));
            }
            if (c.getString(c.getColumnIndex("type")) != null) {
                event.setType(c.getInt(c.getColumnIndex("type")));
            }
            if (c.getString(c.getColumnIndex("day")) != null) {
                event.setDay(c.getInt(c.getColumnIndex("day")));
            }

            dbstring.add(event);
            c.moveToNext();
        }
        db.close(); c.close();
        return dbstring ;
    }


    public ArrayList<Event> getDataDayType(int day , int type) {
        ArrayList<Event> dbstring = new ArrayList<Event>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FIXTURES  + " WHERE " + COLUMN_FIXTURE_DAY + " = " + day + " AND " + COLUMN_FIXTURE_TYPE + " = "  + type ;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Event event = new Event();
            if (c.getString(c.getColumnIndex("fixturePK")) != null) {

                event.setPk(c.getInt(c.getColumnIndex("fixturePK")));
            }

            if (c.getString(c.getColumnIndex("teamA")) != null) {
                event.setTeamA(c.getString(c.getColumnIndex("teamA")));
            }
            if (c.getString(c.getColumnIndex("teamB")) != null) {
                event.setTeamB(c.getString(c.getColumnIndex("teamB")));
            }
            if (c.getString(c.getColumnIndex("venue")) != null) {
                event.setVenue(c.getString(c.getColumnIndex("venue")));
            }
            if (c.getString(c.getColumnIndex("time")) != null) {
                event.setTime(c.getString(c.getColumnIndex("time")));
            }
            if (c.getString(c.getColumnIndex("type")) != null) {
                event.setType(c.getInt(c.getColumnIndex("type")));
            }
            if (c.getString(c.getColumnIndex("day")) != null) {
                event.setDay(c.getInt(c.getColumnIndex("day")));
            }

            dbstring.add(event);
            c.moveToNext();
        }
        db.close(); c.close();
        return dbstring ;
    }

    public ArrayList<Event> getDataDay(int day) {
        ArrayList<Event> dbstring = new ArrayList<Event>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FIXTURES  + " WHERE " + COLUMN_FIXTURE_DAY + " = " + day;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Event event = new Event();
            if (c.getString(c.getColumnIndex("fixturePK")) != null) {

                event.setPk(c.getInt(c.getColumnIndex("fixturePK")));
            }

            if (c.getString(c.getColumnIndex("teamA")) != null) {
                event.setTeamA(c.getString(c.getColumnIndex("teamA")));
            }
            if (c.getString(c.getColumnIndex("teamB")) != null) {
                event.setTeamB(c.getString(c.getColumnIndex("teamB")));
            }
            if (c.getString(c.getColumnIndex("venue")) != null) {
                event.setVenue(c.getString(c.getColumnIndex("venue")));
            }
            if (c.getString(c.getColumnIndex("time")) != null) {
                event.setTime(c.getString(c.getColumnIndex("time")));
            }
            if (c.getString(c.getColumnIndex("type")) != null) {
                event.setType(c.getInt(c.getColumnIndex("type")));
            }
            if (c.getString(c.getColumnIndex("day")) != null) {
                event.setDay(c.getInt(c.getColumnIndex("day")));
            }

            dbstring.add(event);
            c.moveToNext();
        }
        db.close(); c.close();
        return dbstring ;
    }




}

