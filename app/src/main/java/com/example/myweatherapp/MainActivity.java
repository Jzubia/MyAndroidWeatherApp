package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button btnRequest;
    EditText latitude;
    EditText longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.currentTemp);
        final TextView cityText = (TextView) findViewById(R.id.currentCity);
        final TextView[] daysOfTheWeek = {(TextView) findViewById(R.id.Today),
                (TextView) findViewById(R.id.Day1), (TextView) findViewById(R.id.Day2),
                (TextView) findViewById(R.id.Day3), (TextView) findViewById(R.id.Day4),
                (TextView) findViewById(R.id.Day5), (TextView) findViewById(R.id.Day6)};
        final TextView[] daysOfTheWeekAvgTemp = {(TextView) findViewById(R.id.TodayAvgTemp),
                (TextView) findViewById(R.id.Day1AvgTemp), (TextView) findViewById(R.id.Day2AvgTemp),
                (TextView) findViewById(R.id.Day3AvgTemp), (TextView) findViewById(R.id.Day4AvgTemp),
                (TextView) findViewById(R.id.Day5AvgTemp), (TextView) findViewById(R.id.Day6AvgTemp)};

        final TextView[] hoursOfTheDay = {(TextView) findViewById(R.id.Now), (TextView) findViewById(R.id.hour1),
                (TextView) findViewById(R.id.hour2), (TextView) findViewById(R.id.hour3), (TextView) findViewById(R.id.hour4),
                (TextView) findViewById(R.id.hour5), (TextView) findViewById(R.id.hour6), (TextView) findViewById(R.id.hour7),
                (TextView) findViewById(R.id.hour8), (TextView) findViewById(R.id.hour9), (TextView) findViewById(R.id.hour10),
                (TextView) findViewById(R.id.hour11), (TextView) findViewById(R.id.hour12), (TextView) findViewById(R.id.hour13),
                (TextView) findViewById(R.id.hour14), (TextView) findViewById(R.id.hour15), (TextView) findViewById(R.id.hour16),
                (TextView) findViewById(R.id.hour17), (TextView) findViewById(R.id.hour18), (TextView) findViewById(R.id.hour19),
                (TextView) findViewById(R.id.hour20), (TextView) findViewById(R.id.hour21), (TextView) findViewById(R.id.hour22),
                (TextView) findViewById(R.id.hour23)};

        final TextView[] tempHoursOfTheDay = {(TextView) findViewById(R.id.nowTemp), (TextView) findViewById(R.id.hour1Temp),
                (TextView) findViewById(R.id.hour2Temp), (TextView) findViewById(R.id.hour3Temp), (TextView) findViewById(R.id.hour4Temp),
                (TextView) findViewById(R.id.hour5Temp), (TextView) findViewById(R.id.hour6Temp), (TextView) findViewById(R.id.hour7Temp),
                (TextView) findViewById(R.id.hour8Temp), (TextView) findViewById(R.id.hour9Temp), (TextView) findViewById(R.id.hour10Temp),
                (TextView) findViewById(R.id.hour11Temp), (TextView) findViewById(R.id.hour12Temp), (TextView) findViewById(R.id.hour13Temp),
                (TextView) findViewById(R.id.hour14Temp), (TextView) findViewById(R.id.hour15Temp), (TextView) findViewById(R.id.hour16Temp),
                (TextView) findViewById(R.id.hour17Temp), (TextView) findViewById(R.id.hour18Temp), (TextView) findViewById(R.id.hour19Temp),
                (TextView) findViewById(R.id.hour20Temp), (TextView) findViewById(R.id.hour21Temp), (TextView) findViewById(R.id.hour22Temp),
                (TextView) findViewById(R.id.hour23Temp)};

        final ImageView[] daysOfTheWeekImages = {(ImageView) findViewById(R.id.TodayWeatherImage),
                (ImageView) findViewById(R.id.Day1WeatherImage), (ImageView) findViewById(R.id.Day2WeatherImage),
                (ImageView) findViewById(R.id.Day3WeatherImage), (ImageView) findViewById(R.id.Day4WeatherImage),
                (ImageView) findViewById(R.id.Day5WeatherImage), (ImageView) findViewById(R.id.Day6WeatherImage)};

        final ImageView[] hoursOfTheDayImages = {(ImageView) findViewById(R.id.nowImage), (ImageView) findViewById(R.id.hour1Image),
                (ImageView) findViewById(R.id.hour2Image), (ImageView) findViewById(R.id.hour3Image), (ImageView) findViewById(R.id.hour4Image),
                (ImageView) findViewById(R.id.hour5Image), (ImageView) findViewById(R.id.hour6Image), (ImageView) findViewById(R.id.hour7Image),
                (ImageView) findViewById(R.id.hour8Image), (ImageView) findViewById(R.id.hour9Image), (ImageView) findViewById(R.id.hour10Image),
                (ImageView) findViewById(R.id.hour11Image), (ImageView) findViewById(R.id.hour12Image), (ImageView) findViewById(R.id.hour13Image),
                (ImageView) findViewById(R.id.hour14Image), (ImageView) findViewById(R.id.hour15Image), (ImageView) findViewById(R.id.hour16Image),
                (ImageView) findViewById(R.id.hour17Image), (ImageView) findViewById(R.id.hour18Image), (ImageView) findViewById(R.id.hour19Image),
                (ImageView) findViewById(R.id.hour20Image), (ImageView) findViewById(R.id.hour21Image), (ImageView) findViewById(R.id.hour22Image),
                (ImageView) findViewById(R.id.hour23Image)};

        sendAndRequestResponse(daysOfTheWeek, daysOfTheWeekAvgTemp,
                hoursOfTheDay, tempHoursOfTheDay, daysOfTheWeekImages, hoursOfTheDayImages, textView,
                "30.28", "-97.76", cityText);

        btnRequest = (Button) findViewById(R.id.updateLocationBtn);

        latitude = (EditText)findViewById(R.id.editTextLatitude);
        longitude = (EditText)findViewById(R.id.editTextLongitude);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final String latitudeStr = latitude.getText().toString();
                    final String longitudeStr = longitude.getText().toString();
                    sendAndRequestResponse(daysOfTheWeek, daysOfTheWeekAvgTemp,
                            hoursOfTheDay, tempHoursOfTheDay, daysOfTheWeekImages, hoursOfTheDayImages, textView,
                            latitudeStr, longitudeStr, cityText);
                }
            }
        );
    }

        private void sendAndRequestResponse(TextView[] daysOfTheWeek, TextView[] daysOfTheWeekAvgTemp,
                                            TextView[] hoursOfTheDay, TextView[] tempHoursOfTheDay,
                                            ImageView[] daysOfTheWeekImages, ImageView[] hoursOfTheDayImages,
                                            TextView currentTemp, String latitude, String longitude,
                                            TextView cityText){
            // Instantiate the RequestQueue
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude="
                    + longitude + "&hourly=temperature_2m&temperature_unit=fahrenheit&timezone=America%2FChicago";

//            String url = "https://api.open-meteo.com/v1/forecast?latitude=30.28&longitude=-97.76&hourly=temperature_2m&temperature_unit=fahrenheit&timezone=America%2FChicago";

            // Request a string response from the provided URL
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject JSONResponse = Utility.createJsonObject(response);

                        // Gets the hourly temps for today
                        JSONObject hourlyTemp = JSONResponse.getJSONObject("hourly");
                        JSONArray tempJSONArray = hourlyTemp.getJSONArray("temperature_2m");

                        // Gets today's date
                        String today = LocalDate.now().getDayOfWeek().name().substring(0, 3).toLowerCase();
                        today = today.substring(0, 1).toUpperCase() + today.substring(1, 3);


                        // Gets the current hour
                        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

                        // Gets the daily average temps for the week
                        String[] dailyAverages = Utility.getDailyAverages(tempJSONArray);

                        // Get order of days
                        String[] days = Utility.getDaysOfTheWeekFromToday(today);

                        // Get next 24hrs temps
                        String[] todayHourlyTemps = Utility.getTempHoursOfTheDayFromNow(tempJSONArray, hour);

                        // Gets next 24hrs
                        String[] todayHours = Utility.getHours(hour);

                        // Sets Hourly Forecast
                        for(int i = 0; i < 24; i++)
                        {
                            hoursOfTheDay[i].setText(todayHours[i]);
                            tempHoursOfTheDay[i].setText(todayHourlyTemps[i] + "°F");
                        }

                        // Sets 7Day Forecast
                        for(int i = 0; i < days.length; i++)
                        {
                            daysOfTheWeek[i].setText(days[i]);
                            daysOfTheWeekAvgTemp[i].setText(dailyAverages[i] + "°F");
                        }

                        setImages(dailyAverages, daysOfTheWeekImages);
                        setImages(todayHourlyTemps, hoursOfTheDayImages);

                        currentTemp.setText(todayHourlyTemps[0] + "°F");

                        cityText.setText(getAddress(latitude, longitude));

                        // Creates a toast
                        Context context = getApplicationContext();
                        CharSequence text = "Updated Successfully";

                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();

                        // Display the first 500 characters of the response string
//                        textView.setText(Arrays.toString(todayHourlyTemps));
//                        textView.setText(String.valueOf(hour));
//                        jsonTextView.setText(Arrays.toString(dailyAverages));

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    hoursOfTheDay[0].setText("That didn't work!");
                }
            });

            // Add the request to the RequestQueue
            queue.add(stringRequest);
        }

        private void setImages(String[] data, ImageView[] images)
        {
            for(int i = 0; i < data.length; i++)
            {
                if(Integer.parseInt(data[i]) < 40)
                {
                    images[i].setImageResource(R.drawable.snowy);
                } else if(Integer.parseInt(data[i]) >= 40 && Integer.parseInt(data[i]) < 70)
                {
                    images[i].setImageResource(R.drawable.clouds);
                } else {
                    images[i].setImageResource(R.drawable.sunny);
                }
            }
        }

        public String getAddress(String latitude, String longitude) throws IOException
        {
            String city = "";
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latitude),
                        Double.parseDouble(longitude), 1);
                if(addresses != null && addresses.size() > 0)
                {
                    city = addresses.get(0).getLocality();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return city;
        }

    }
