package com.example.englishdictionary;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewHisFav extends Activity {

	TextView ref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_his_fav);
		ref=(TextView)findViewById(R.id.View);
		Intent a=getIntent();
		Bundle b=a.getExtras();
		String word=b.getString("word");
		String meaning=b.getString("meaning");
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
	    actionBar.setTitle(word);
	    String s=actionBar.getTitle().toString();
	    ref.setText(meaning);
	}

	
}
