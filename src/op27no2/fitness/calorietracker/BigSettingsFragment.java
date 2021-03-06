package op27no2.fitness.calorietracker;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

@SuppressLint("NewApi")
public class BigSettingsFragment extends PreferenceFragment{
	  public SharedPreferences prefs2;
	  public SharedPreferences prefs3;
	  public SharedPreferences prefs;
	  public SharedPreferences.Editor editor;
	  public String tdee;
	  public String tdee2;
	  public int basecal;
	  public int basecal2;
	  public int diff;
	  public int calories;
	  public int caloriesweek;
	  public int widgetId;
	  public int initcal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences prefs = getActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        widgetId = prefs.getInt("currentId", 2);

	    editor = getActivity().getSharedPreferences("PREFS"+widgetId, Context.MODE_PRIVATE).edit();
        prefs = getActivity().getSharedPreferences("PREFS"+widgetId, Context.MODE_PRIVATE);

        
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);
    }
    	
    @Override
    public void onResume(){
    	super.onResume();
	    SharedPreferences prefs = getActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        widgetId = prefs.getInt("currentId", 2);
        System.out.println("activityPRefId:"+widgetId);
        prefs = getActivity().getSharedPreferences("PREFS"+widgetId, Context.MODE_PRIVATE);
        editor = getActivity().getSharedPreferences("PREFS"+widgetId, Context.MODE_PRIVATE).edit();
 
        initcal = prefs.getInt("basecal", 2000);
        System.out.println("initcal:"+initcal);
    }
    
    @Override
    public void onPause(){
    	super.onPause();
        RemoteViews bigremoteViews = new RemoteViews(getActivity().getPackageName(), R.layout.bigwidget_layout);
        
        prefs = getActivity().getSharedPreferences("PREFS"+widgetId, Context.MODE_PRIVATE);
        calories = prefs.getInt("calories", -2000);
        caloriesweek = prefs.getInt("caloriesweek", -2000);
        
        String tdee2 = prefs2.getString("tapnum", "2000");
        basecal2 = Integer.parseInt(tdee2);
      
        
        if(basecal2 != initcal){
        System.out.println("NowCAL:"+basecal2);
        System.out.println("PREVCAL:"+initcal);

        diff = initcal - basecal2;
        System.out.println("diff:"+diff);

     /*   int calories = prefs2.getInt("calories", -2000);
        System.out.println("cal1:"+calories);
        int caloriesweek = prefs2.getInt("caloriesweek", -2000);*/
        calories = calories + diff;
        System.out.println("cal2:"+calories);
 		caloriesweek = caloriesweek + diff;
		bigremoteViews.setTextViewText(R.id.update, String.valueOf(calories));
		bigremoteViews.setTextViewText(R.id.updateweek, String.valueOf(caloriesweek));
		bigremoteViews.setTextViewText(R.id.update, String.valueOf(calories));
		bigremoteViews.setTextViewText(R.id.updateweek, String.valueOf(caloriesweek));
		if(calories < 0){
			bigremoteViews.setTextColor(R.id.update, Color.RED);
			bigremoteViews.setTextColor(R.id.update, Color.RED);
		}
		if(calories > 0){
			bigremoteViews.setTextColor(R.id.update, Color.GREEN);
			bigremoteViews.setTextColor(R.id.update, Color.GREEN);
		}
		if(caloriesweek < 0){
			bigremoteViews.setTextColor(R.id.updateweek, Color.RED);
			bigremoteViews.setTextColor(R.id.updateweek, Color.RED);
		}
		if(caloriesweek > 0){
			bigremoteViews.setTextColor(R.id.updateweek, Color.GREEN);
			bigremoteViews.setTextColor(R.id.updateweek, Color.GREEN);
		}
		
		
		editor.putInt("basecal", basecal2);
		editor.putInt("calories",calories);
		editor.putInt("caloriesweek",caloriesweek);
		editor.commit();
		MyBigWidgetProvider.pushWidgetUpdate(getActivity().getApplicationContext(), bigremoteViews, widgetId);
        
        }
        String weekday = prefs2.getString("tapnum2", "1");
		String pound = prefs2.getString("timeout", "Pounds");
		String big = prefs2.getString("vibnum", "100");
		String small = prefs2.getString("soundnum", "10");
		editor.putString("vibnum", big);
		editor.putString("soundnum", small);
        editor.putString("timeout", pound);
		editor.putString("tapnum2", weekday);
		System.out.println("pausepound:"+pound);
		editor.commit();
		
    }
}
