package nl.mprog.apps.preparty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class FestivalList
{
	ArrayList<Festival> festivals;
	static SharedPreferences festivalPrefs;
	
	Context context;
	
	TestAdapter mDbHelper;
	
	// CONSTRUJTOR!@@@!!!
	public FestivalList(Context context, TestAdapter dbHelper) 
	{
		festivals = new ArrayList<Festival>();
		
		this.mDbHelper = dbHelper;         
		this.context = context;

		// load scores
		festivalPrefs = context.getSharedPreferences("festivallist", Context.MODE_PRIVATE);

		String festivalIdsString = festivalPrefs.getString("festivallist", "");
		// split scores for arraylist
		String[] ids = festivalIdsString.split(",");
		for (int i = 0; i < ids.length; i++)
		{
			if (ids[i].length() > 0) {
				Log.d("PREPARTY", "Getting festival with ID "+ Integer.parseInt(ids[i]));
				Festival f = mDbHelper.getFestival(Integer.parseInt(ids[i]));
				festivals.add(f);
			}
		}
		
	}

	public static void addFestival(Festival festival)
	{
		SharedPreferences.Editor festivalsEdit = festivalPrefs.edit();

		// Store date + score
		String previousList = festivalPrefs.getString("festivallist", "");
		String newList = "";
		if (previousList.length() == 0) {
			newList = "" + festival.id;
		} else {
			newList = previousList + ","+festival.id;
		}
		festivalsEdit.putString("festivallist", newList);
		festivalsEdit.commit();
	}
}