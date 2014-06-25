package nl.mprog.apps.preparty;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class WeatherActivity extends FestivalinfoActivity
{
	
	Button backToFestivalinfo;
	double temp;
	double mintemp;
	int clouds;
	ImageView weather;
	Festival festival;
	TextView title1;
	String temperature;
	TextView temperatureView;
	String city;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		
		addListenerOnButton();
		
		// set image weather
		weather = (ImageView)findViewById(R.id.imageView1);
		weather.setImageResource(R.drawable.loading_small);
		
		// init textview
		title1 = (TextView)findViewById(R.id.textView5);
		
		// get the festival name from input and make sure activity gets object (*serializable)
		festival = (Festival) getIntent().getSerializableExtra("festivalObject");
		
		// set festival name
		title1.setText(festival.name);
		
		// get city from festival
		city = festival.location;
		
		getWeather();
		
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
            	festivalinfoActivity.putExtra("festivalObject", festival); 
                startActivity(festivalinfoActivity);
            }
		});	
	}
	
	
	// Initialize World Weather Online example
	public void getWeather()
	{
		Toast.makeText(WeatherActivity.this,"Checking city "+city, Toast.LENGTH_LONG).show();
		
        WeatherLoader task = new WeatherLoader();
        String unit = "metric"; // Celsius
        task.execute("http://api.openweathermap.org/data/2.5/weather?q="+city+"&mode=json&units="+unit);
	}
	

	private class WeatherLoader extends AsyncTask<String, Void, String> 
	{
		// Class that gets string from http get from an URL
		// Source: http://stackoverflow.com/questions/16994777/android-get-html-from-web-page-as-string-with-httpclient-not-working
		
	        @Override
	        protected String doInBackground(String... urls) 
	        {
	            HttpResponse response = null;
	            HttpGet httpGet = null;
	            HttpClient mHttpClient = null;
	            String s = "";

	            try {
		                if(mHttpClient == null)
		                {
		                    mHttpClient = new DefaultHttpClient();
		                }
		                
		                httpGet = new HttpGet(urls[0]);
	
		                response = mHttpClient.execute(httpGet);
		                s = EntityUtils.toString(response.getEntity(), "UTF-8");

	            	}
	            
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	            } 
	            return s;
	        }
	        
	        @Override
	        protected void onPostExecute(String result)
	        {
	            Log.d("PREPARTY", "Succes "+ result);

	            
	            try
	            {
		            JSONObject json = new JSONObject(result);
		            JSONObject mainjson = new JSONObject(json.getString("main"));
		            JSONObject cloudjson = new JSONObject(json.getString("clouds"));
		            // gives temp in celcius
		            temp = mainjson.getDouble("temp");// getString("temp");
		            mintemp = mainjson.getDouble("temp_min");
		            // gives cloudiness in percentage
		            clouds = cloudjson.getInt("all");
		            Log.d("PREPARTY", "Tempreature is "+temp);
	            }
	            catch (Exception e)
	            {
	            	Log.e("PREPARTY", "Error parsin weather json: "+result,e);
	            }
	            
	            // weather images depending on temp, min temp and cloudiness
		        if (temp < 15.0 && temp > 0.0 && clouds > 60)
		        {
		        	weather.setImageResource(R.drawable.stormy);
		        }
		        else if (temp > 15.0 && temp < 30.0 && clouds > 50 && clouds < 80)
		        {
		        	weather.setImageResource(R.drawable.cloudy);
		        }
		        else if (temp > 20.0 && temp < 30.0 && clouds < 40)
		        {
		        	weather.setImageResource(R.drawable.sunny);
		        }
		        else if (temp < -0.0)
		        {
		        	weather.setImageResource(R.drawable.cold);
		        }
		        else if (temp > 30 && clouds < 50)
		        {
		        	weather.setImageResource(R.drawable.hot);
		        }
		        else if (temp < 15.0)
		        {
		        	weather.setImageResource(R.drawable.cold);
		        }
		        else if (temp > 15.0)
		        {
		        	weather.setImageResource(R.drawable.sunny);
		        }
		        
				// convert double to string for temperature in view
				temperature = String.valueOf(temp);
				// set temperature in view
				temperatureView = (TextView)findViewById(R.id.textView3);
				temperatureView.setText("Temperature in "+city + " is " +temperature + " Celsius");
	        }
	}
}

