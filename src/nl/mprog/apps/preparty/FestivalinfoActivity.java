package nl.mprog.apps.preparty;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;


public class FestivalinfoActivity extends MainActivity
{
	// declare Buttons
	Button backToMain;
	Button checkWeather;
	Button addfestival;
	
	// declare TextView
	TextView title;
	TextView date;
	TextView genre;
	TextView time;
	TextView location;
	TextView watchtrailer;
	RelativeLayout festivalinfo_layout;
	
	// festival that activity is showing
	Festival festival;
	
	// list for festivals
	FestivalList festivalList;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_festivalinfo);
		
		// set views
		title = (TextView) findViewById(R.id.textView1);
		date = (TextView) findViewById(R.id.textView2);
		genre = (TextView) findViewById(R.id.textView5);
		time = (TextView) findViewById(R.id.textView3);
		location = (TextView) findViewById(R.id.textView4);
		
		// set buttons
		backToMain = (Button) findViewById(R.id.button1);
		checkWeather = (Button) findViewById(R.id.button2);
		addfestival = (Button) findViewById(R.id.button3);
		
		// for input
		addListenerOnButton();
	
		// get the festival name from input and make sure activity gets object (*serializable)
		festival = (Festival) getIntent().getSerializableExtra("festivalObject");
		
		// fill all the TextViews with info from the festival
		title.setText(festival.name);
		date.setText("Date: "+festival.date);
		genre.setText("Genre: "+festival.genre);
		time.setText("Time: "+festival.time);
		location.setText("City: "+festival.location);
		
		festivalinfo_layout = (RelativeLayout) findViewById(R.id.background);
		festivalinfo_layout.setBackgroundResource(R.drawable.bg_flower);
		
	}
	
	// make button clickable
	public void addListenerOnButton()
	{	
		// link button to main activity
		backToMain.setOnClickListener(new View.OnClickListener() 
		{
			// start new intent
        	Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        	
            public void onClick(View V) 
            {            	

            	
                // Starting Main Activity
                startActivity(mainActivity);
            }
		});
		
		// link button to weather activity
		checkWeather.setOnClickListener(new View.OnClickListener() 
		{
			Intent weatherActivity = new Intent(getApplicationContext(), WeatherActivity.class);
			
			@Override
			public void onClick(View v) 
			{
				// give the festival to weather activity to display the local eather
				weatherActivity.putExtra("festivalObject", festival); 

				startActivity(weatherActivity);
			}
		});
		
		// link button to festival and add festival to SharedPreferences
		addfestival.setOnClickListener(new View.OnClickListener() 
		{
			//Intent addFestival = new Intent(getApplicationContext(), MainActivity.class);
			
			@Override
			public void onClick(View v) 
			{
				// add festival in shared preferences
				FestivalList.addFestival(festival);
				Toast.makeText(getApplicationContext(), festival.name + " remembered", Toast.LENGTH_LONG).show();
			}
		});
	
	}
	
	
	
}

