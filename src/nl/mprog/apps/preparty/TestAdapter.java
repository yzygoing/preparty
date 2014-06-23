package nl.mprog.apps.preparty;

import java.io.IOException;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestAdapter 
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataHelper mDbHelper;

    public TestAdapter(Context context) 
    {
        this.mContext = context;
        mDbHelper = new DataHelper(mContext);
    }

    public TestAdapter createDatabase() throws SQLException 
    {
        try 
        {
            mDbHelper.createDataBase();
        } 
        catch (IOException mIOException) 
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public TestAdapter open() throws SQLException 
    {
        try 
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } 
        catch (SQLException mSQLException) 
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() 
    {
        mDbHelper.close();
    }
    
 
     // Get a list of all festival names that are in the database for autocomplete
    public String[] getFestivalNames()
    {
    	// Get festival titles from database
    	String sql = "SELECT title FROM festivaltable";
    	open();
        Cursor mCur = mDb.rawQuery(sql, null);
    	String[] festivals = new String[mCur.getCount()];
    	int i = 0;
    	if (mCur.moveToFirst())
    	{
    		do
    		{
    			festivals[i] = mCur.getString(mCur.getColumnIndex("title"));
    			i++;
    		}
    		while (mCur.moveToNext());
    	}
    	return festivals;
    }
    
   
     // create festival object from data from the database
    public Festival getFestival(String name) 
    {
    	Festival f = new Festival();
    	// TODO: check if name from input autocomplete is in database
    	String sql = "SELECT title, date, time, location, genre FROM festivaltable WHERE title = '" + name + "'";
    	open();
        Cursor mCur = mDb.rawQuery(sql, null);
        mCur.moveToFirst();
        // load elements from db festivals
        f.name = mCur.getString(mCur.getColumnIndex("title"));
        f.date = mCur.getString(mCur.getColumnIndex("date"));
        f.time = mCur.getString(mCur.getColumnIndex("time"));
        f.location = mCur.getString(mCur.getColumnIndex("location"));
        f.genre = mCur.getString(mCur.getColumnIndex("genre"));
        return f;
    }

     public String[] getTestData()
     {
         try
         {
        	 // get festivalnames from db (for autocomplete drop down list)
             String sql ="SELECT * FROM festivaltable";
             open();
             Cursor mCur = mDb.rawQuery(sql, null);
             String[] festivalData = new String[mCur.getCount()];
             int i = 0;
             if (mCur.moveToFirst())
             {
            	    do 
            	    {
                        String data = mCur.getString(mCur.getColumnIndex("title"));
                        festivalData[i] = data;
                        i++;
            	    }
            	    
            	    while (mCur.moveToNext());
            	    // check festivalname
            	    Log.d("TEST", festivalData[2].toString());
            }
             mCur.close();
             close();
             return festivalData;
         }
         catch (SQLException mSQLException) 
         {
             Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
}