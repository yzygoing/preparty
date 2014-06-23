package nl.mprog.apps.preparty;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ActionBar;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;


 
public class MainActivity extends Activity implements TextWatcher 
{ 
	AutoCompleteTextView search_festival;
	TextView header_festival;
	ImageView preparty_logo;
	public Button festivalinfo;
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// override AutoCompleteTextView
	CustomAutoCompleteView myAutoComplete;
	
	// database handler
	TestAdapter mDbHelper; 
	
    // adapter for auto-complete
    ArrayAdapter<String> myAdapter;
    
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
	//	HashMap<Integer, Festival> festivalMap; 
	
	// test for dropdown list autocomplete
//    private static final String[] FESTIVALS = new String[] 
//	    { 
//	         "Awakenings Festival Day 1", "Melt! 2014", "Into The Woods Festival", "Lost I A Moment Festival", "Burning Man 2014"
//	     }; 
	    
	@Override
	protected void onCreate(Bundle savedInstanceState)  
	{
		Log.d("PREPARTY", getApplicationContext().getDatabasePath("festivals").getAbsolutePath());
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// open festival database in assets/festivaldatabase
		mDbHelper = new TestAdapter(getApplicationContext());         
		mDbHelper.createDatabase();      
		mDbHelper.open();
		
		// test
		String[] testdata = mDbHelper.getTestData();
		Log.d("PREPARTY", "getTestData >>"+ testdata.toString());
		
		// add in auto complete drop down list

		mDbHelper.close();
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		// test for CustomAutoCompleteView (overrule AutoCompleteTextView)
//		   try{
//			   
//			   // instantiate database handler
//	            databaseH = new DataHelper(MainActivity.this);
//	             
//	            // Custom Auto-Complete View is in activity_main.xml
//	            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.myAutoComplete);
//	             
//	            // add the listener so it will tries to suggest while the user types //TODO: change
//	            myAutoComplete.addTextChangedListener(this);
//	             
//	            // set our adapter
//	            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, FESTIVALS);
//	            myAutoComplete.setAdapter(myAdapter);
//	          
//	        } catch (NullPointerException e) {
//	            e.printStackTrace();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
		   
		   ///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// setup the top bar title and show it 
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Pre Party"); 
		actionBar.show();
		 
		// set TextView
		header_festival =(TextView)findViewById(R.id.header_festival);
		
		// set logo ImageView
		preparty_logo = (ImageView) findViewById(R.id.imageView1); 
		
		// set button for test
		festivalinfo = (Button) findViewById(R.id.button1);
		
		// link button to new activity
		festivalinfo.setOnClickListener(new View.OnClickListener() 
		{ 
			// start new intent
        	Intent festivalinfoActivity;
        	
            public void onClick(View V) 
            { 
            	festivalinfoActivity = new Intent(getApplicationContext(), FestivalinfoActivity.class);
            	 
            	// get festival from the database if it exists
            	String selectedFestivalName = search_festival.getText().toString();
            	Festival selectedFestival = mDbHelper.getFestival(selectedFestivalName);
            	festivalinfoActivity.putExtra("festivalObject", selectedFestival); 
            	            	
                // starting the new festival activity
                startActivity(festivalinfoActivity);
            }  
		});
		
		// initialize Auto Complete view
		search_festival = (AutoCompleteTextView)findViewById(R.id.myAutoComplete);
		search_festival.addTextChangedListener(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
		(this, android.R.layout.simple_dropdown_item_1line, mDbHelper.getFestivalNames());
		search_festival.setAdapter(adapter); 
			   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
    // set TextWatcher
    public void afterTextChanged(Editable s) {	}
    public void beforeTextChanged(CharSequence s, int start, int count,int after){}
    public void onTextChanged(CharSequence s, int start, int before, int count) {}


}
