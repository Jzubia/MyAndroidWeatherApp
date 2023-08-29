package com.example.myweatherapp;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class Utility
{
    public static JSONObject createJsonObject(String response) throws JSONException {
        return new JSONObject(response);
    }

//    public static String[] getTodayHourlyTemp(JSONArray tempJSONArray) throws JSONException {
//        int[] hourlyTemps = new int[24];
//        String[] sHourlyTemps = new String[24];
//        for(int i = 0; i < 24; i++)
//        {
//            hourlyTemps[i] = (int)Math.rint((Double)tempJSONArray.get(i));
//            sHourlyTemps[i] = String.valueOf(hourlyTemps[i]);
//        }
//
//        return sHourlyTemps;
//    }

    public static String[] getDailyAverages(JSONArray tempJSONArray) throws JSONException {
        String[] sDailyAverages = new String[7];
        int count = 0;
        int index = 0;
        double total = 0;
        for(int i = 0; i < tempJSONArray.length(); i++)
        {
            if(count == 24)
            {
                int average = (int)Math.rint(total / 24);
                sDailyAverages[index] = String.valueOf(average);
                // Reset count for next 24 values
                count = 0;
                // Increase index
                index++;
                // Reset total
                total = 0;
            }
            total += (Double)tempJSONArray.get(i);
//            total += (int)Math.rint((Double)tempJSONArray.get(i));
            count++;
        }
        int average = (int)Math.rint(total / 24);
        sDailyAverages[index] = String.valueOf(average);

        return sDailyAverages;
    }

    public static String[] getDaysOfTheWeekFromToday(String today)
    {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[] orderedDays = new String[7];
        orderedDays[0] = "Today";

        int index = Arrays.asList(days).indexOf(today) + 1;
        int secondIndex = 1;

        for(int i = index; i <= days.length; i++)
        {
            if(secondIndex == 7)
            {
                break;
            }
            if(i == days.length)
            {
                i = 0;
            }
            orderedDays[secondIndex] = days[i];
            secondIndex++;
        }

        return orderedDays;
    }

    public static String[] getTempHoursOfTheDayFromNow(JSONArray tempJSONArray, int hour) throws JSONException
    {
        String[] hoursOftTheDay = new String[24];
        int index = 0;
        for(int i = hour; i < tempJSONArray.length() ; i++)
        {
            if(index == 24)
            {
                break;
            }
            hoursOftTheDay[index] = String.valueOf((int)Math.rint((Double)tempJSONArray.get(i)));
            index++;
        }
        return hoursOftTheDay;
    }

    public static String[] getHours(int hour)
    {
        String[] result = new String[24];
        int temp = hour;
        for(int i = 0; i < 24; i++)
        {
            if(temp == 24)
            {
                temp = 0;
            }
            result[i] = String.valueOf(temp);
            temp++;
        }
        return convertHours(result);
    }

    public static String[] convertHours(String[] hoursOfTheDay)
    {
        for(int i = 0; i < hoursOfTheDay.length; i++)
        {
            if(hoursOfTheDay[i].equals("0"))
            {
                hoursOfTheDay[i] = "12AM";
            } else if (hoursOfTheDay[i].equals("1"))
            {
                hoursOfTheDay[i] = "1AM";
            } else if (hoursOfTheDay[i].equals("2"))
            {
                hoursOfTheDay[i] = "2AM";
            } else if (hoursOfTheDay[i].equals("3"))
            {
                hoursOfTheDay[i] = "3AM";
            } else if (hoursOfTheDay[i].equals("4"))
            {
                hoursOfTheDay[i] = "4AM";
            } else if (hoursOfTheDay[i].equals("5"))
            {
                hoursOfTheDay[i] = "5AM";
            } else if (hoursOfTheDay[i].equals("6"))
            {
                hoursOfTheDay[i] = "6AM";
            } else if (hoursOfTheDay[i].equals("7"))
            {
                hoursOfTheDay[i] = "7AM";
            } else if (hoursOfTheDay[i].equals("8"))
            {
                hoursOfTheDay[i] = "8AM";
            } else if (hoursOfTheDay[i].equals("9"))
            {
                hoursOfTheDay[i] = "9AM";
            } else if (hoursOfTheDay[i].equals("10"))
            {
                hoursOfTheDay[i] = "10AM";
            } else if (hoursOfTheDay[i].equals("11"))
            {
                hoursOfTheDay[i] = "11AM";
            } else if (hoursOfTheDay[i].equals("12"))
            {
                hoursOfTheDay[i] = "12PM";
            } else if (hoursOfTheDay[i].equals("13"))
            {
                hoursOfTheDay[i] = "1PM";
            } else if (hoursOfTheDay[i].equals("14"))
            {
                hoursOfTheDay[i] = "2PM";
            } else if (hoursOfTheDay[i].equals("15"))
            {
                hoursOfTheDay[i] = "3PM";
            } else if (hoursOfTheDay[i].equals("16"))
            {
                hoursOfTheDay[i] = "4PM";
            } else if (hoursOfTheDay[i].equals("17"))
            {
                hoursOfTheDay[i] = "5PM";
            } else if (hoursOfTheDay[i].equals("18"))
            {
                hoursOfTheDay[i] = "6PM";
            } else if (hoursOfTheDay[i].equals("19"))
            {
                hoursOfTheDay[i] = "7PM";
            } else if (hoursOfTheDay[i].equals("20"))
            {
                hoursOfTheDay[i] = "8PM";
            } else if (hoursOfTheDay[i].equals("21"))
            {
                hoursOfTheDay[i] = "9PM";
            } else if (hoursOfTheDay[i].equals("22"))
            {
                hoursOfTheDay[i] = "10PM";
            } else if (hoursOfTheDay[i].equals("23"))
            {
                hoursOfTheDay[i] = "11PM";
            }
        }
        return hoursOfTheDay;
    }

}
