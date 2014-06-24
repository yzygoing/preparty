package nl.mprog.apps.preparty;

import java.util.ArrayList;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.net.Uri;

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
	TextView url;
	
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
		url = (TextView) findViewById(R.id.textView7);
		
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
		date.setText(festival.date);
		genre.setText(festival.genre);
		time.setText(festival.time);
		location.setText(festival.location);
		
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
				startActivity(weatherActivity);
			}
		});
		
		// test: make button url clickable and link to website
		url.setOnClickListener(new OnClickListener() 
		{
			Intent internetIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.awakeningsfestival.nl"));		
			
			@Override
			public void onClick(View v) 
			{
				internetIntent.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));
				internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(internetIntent);
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
				Toast.makeText(getApplicationContext(), festival.name + " added to your list", Toast.LENGTH_LONG).show();
			

			}
		});
	
	}
	
	
	
}

