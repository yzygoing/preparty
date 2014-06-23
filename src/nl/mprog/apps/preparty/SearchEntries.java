package nl.mprog.apps.preparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;
 
public class SearchEntries extends MainActivity
{
	AutoCompleteTextView search_festival;
    TestAdapter dbAdapter = new TestAdapter(this);
    static final String KEY_ID = "id"; 
    static final String KEY_TITLE = "title";
    static final String KEY_DATE = "date";
    static final String KEY_TIME = "time";
    static final String KEY_GENRE = "genre";
    static final String KEY_LOCATION = "location";
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // Each row in the list stores id, title, date, time, genre, location
        List<HashMap<String,String>> entryList = new ArrayList<HashMap<String,String>>();
//        String[] FESTIVAL_ID = dbAdapter.getTestData(KEY_ID);
//        String[] TITLE = dbAdapter.getTestData(KEY_TITLE);
//        String[] DATE = dbAdapter.getTestData(KEY_DATE);
//        String[] TIME = dbAdapter.getTestData(KEY_TIME);
//        String[] GENRE = dbAdapter.getTestData(KEY_GENRE);
//        String[] LOCATION = dbAdapter.getTestData(KEY_LOCATION);
        
        
//        for(int i=0;i<FESTIVAL_ID.length;i++)
//        {
//            HashMap<String, String> hm = new HashMap<String,String>();
//            hm.put("festivals", TITLE[i]);
//            hm.put("date", DATE[i]);
//            entryList.add(hm);
//        }
 
//        // Keys used in Hashmap
//        String[] from = { "search festivals" };
//        
//        Log.d("TEST", entryList.toString());
// 
//        // Ids of views in listview_layout
//        int[] to = { R.id.actv};
        
        // Instantiating an adapter to store each items
      //  SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), entryList, R.layout.activity_main, from, to);
 
        //CustomAutoCompleteTextView autoComplete = ( CustomAutoCompleteTextView) findViewById(R.id.actv);
 
        /** Setting the adapter to the View */
       // autoComplete.setAdapter(adapter);
        //autoComplete.setThreshold(1);
 
        
//        // Instantiating an adapter to store each items
//        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), entryList, android.R.layout.simple_dropdown_item_1line, from, to);
// 
//        CustomAutoCompleteTextView autoComplete = ( CustomAutoCompleteTextView) findViewById(R.id.actv);
//        
//        autoComplete.addTextChangedListener(this);
// 
//        /** Setting the adapter to the listView */
//        autoComplete.setAdapter(adapter);
//        autoComplete.setThreshold(1);
        
//	        search_festival = (AutoCompleteTextView)findViewById(R.id.actv);
//	        search_festival.addTextChangedListener(this);
//		    ArrayAdapter<String> adapter = new ArrayAdapter<String>
//			(this, android.R.layout.simple_dropdown_item_1line, from);
//		    search_festival.setAdapter(adapter);
//		    search_festival.setThreshold(2);
    }
    
}