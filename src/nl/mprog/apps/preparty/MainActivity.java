package nl.mprog.apps.preparty;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

 
public class MainActivity extends Activity implements TextWatcher 
{ 
	AutoCompleteTextView search_festival;
	TextView header_festival;
	TextView myfestivals;
	ImageView preparty_logo;
	public Button festivalinfo; 
	
	public FestivalList festivalList;
	

	// database handler 
	TestAdapter mDbHelper; 
	
    // adapter for auto-complete 
    ArrayAdapter<String> myAdapter;
       
	@Override
	protected void onCreate(Bundle savedInstanceState)  
	{
		Log.d("PREPARTY", getApplicationContext().getDatabasePath("festivals").getAbsolutePath());
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// open festival database
		mDbHelper = new TestAdapter(getApplicationContext());         
		mDbHelper.createDatabase();      
		mDbHelper.open(); 
		 
		// test 
		String[] testdata = mDbHelper.getTestData();
		Log.d("PREPARTY", "getTestData >>"+ testdata.toString());
				

		mDbHelper.close();
		
		// INIT LIST
		festivalList = new FestivalList(getApplicationContext(), mDbHelper);
		
		Log.d("PREPARTY", "This ist he first item in you list: :: " + festivalList.festivals.get(0).name);
		 
		
		// setup the top bar title and show it 
		ActionBar actionBar = getActionBar(); 
		actionBar.setTitle("Pre Party"); 
		actionBar.show();
		 
		// set TextView
		header_festival =(TextView)findViewById(R.id.header_festival);
		
		// set TextView My Festivals
		myfestivals = (TextView)findViewById(R.id.myfestivals);
		String festivalslist = "";
		String recentlyviewed = "Recently viewed: ";
		
		// loop through the festival list to fill the string
		for (Festival f : festivalList.festivals) 
		{
			// newline between festival names
			festivalslist = recentlyviewed + f.name + " \n";
		}
		
		myfestivals.setText(festivalslist);
		
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
            	
            	// check for input festival
            	if (selectedFestivalName.length() < 5)
            	{
            		// toast: warn user for no valid input
            		Toast.makeText(getApplicationContext(),"Festival doesn't exist. Please select a festival from the drop-down list", Toast.LENGTH_LONG).show();
            	}
            	else
            	{
                	
                	// give the festival to the festival activity view
            		Festival selectedFestival = mDbHelper.getFestival(selectedFestivalName);
                	festivalinfoActivity.putExtra("festivalObject", selectedFestival); 
                	            	
                    // starting the new festival activity
                    startActivity(festivalinfoActivity);
                }  
            }
          
		});
		
		// initialize Auto Complete view
		search_festival = (AutoCompleteTextView)findViewById(R.id.myAutoComplete);
		search_festival.addTextChangedListener(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
		(this, android.R.layout.simple_dropdown_item_1line, mDbHelper.getFestivalNames());
		search_festival.setAdapter(adapter);
		
	}
	

    // set TextWatcher
    public void afterTextChanged(Editable s) {	}
    public void beforeTextChanged(CharSequence s, int start, int count,int after){}
    public void onTextChanged(CharSequence s, int start, int before, int count) {}


}
