package nl.mprog.apps.preparty;

import it.restrung.rest.cache.RequestCache;
import it.restrung.rest.cache.RequestCache.LoadPolicy;
import it.restrung.rest.client.APIDelegate;
import it.restrung.rest.client.ContextAwareAPIDelegate;

import com.fortysevendeg.android.worldweatheronline.api.service.WorldWeatherOnlineApiProvider;
import com.fortysevendeg.android.worldweatheronline.api.service.request.Query;
import com.fortysevendeg.android.worldweatheronline.api.service.response.RequestResponse;
import com.fortysevendeg.android.worldweatheronline.api.service.response.WorldWeatherOnlineResponse;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;
import android.view.TouchDelegate;


public class WeatherActivity extends FestivalinfoActivity
{
	
	Button backToFestivalinfo;
	
	public static final String APIKEY = "58c29ca0cceb1475dfde9e41cb0724370e1186c5";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		
		addListenerOnButton();
		
		this.getWeather(5, "Amsterdam");
	}
				
	public void addListenerOnButton()
	{
		// set button
		backToFestivalinfo = (Button) findViewById(R.id.button1);
		
		// link button to festivalinfo activity
		backToFestivalinfo.setOnClickListener(new View.OnClickListener() 
		{
			// start new intent
        	Intent festivalinfoActivity = new Intent(getApplicationContext(), FestivalinfoActivity.class);
        	
            public void onClick(View V) 
            {
                // Starting festivalinfo activity
                startActivity(festivalinfoActivity);
            }
		});	
	}
	
	// API KEY World Weather Online = 58c29ca0cceb1475dfde9e41cb0724370e1186c5
	
	
	// Initialize World Weather Online example
	public void getWeather(int numberOfDays, String city)
	{
		Toast.makeText(WeatherActivity.this,"Checking city "+city, Toast.LENGTH_LONG).show();

		
		WorldWeatherOnlineApiProvider.getClient().query(new ContextAwareAPIDelegate<WorldWeatherOnlineResponse>(WeatherActivity.this,
		          WorldWeatherOnlineResponse.class, LoadPolicy.NEVER, RequestCache.StoragePolicy.DISABLED) 
		          {
					// TODO: fix error on getCurrentConditionList
				     @Override
				     public void onResults(WorldWeatherOnlineResponse worldWeatherOnlineResponse) 
				     {
				           Toast.makeText(WeatherActivity.this, worldWeatherOnlineResponse.getData().toString(), Toast.LENGTH_LONG).show();
				     }

					@Override
					public void onError(Throwable arg0) 
					{
						Toast.makeText(WeatherActivity.this,"error: "+ arg0.getMessage(), Toast.LENGTH_LONG).show();
						
					}
		           }, APIKEY, numberOfDays, Query.cityCountry(city, "Netherlands"));
	}
	
	
}

