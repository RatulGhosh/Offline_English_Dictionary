package com.example.englishdictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EDictionary extends Activity {

	TextView ref;
	String word;
	String meaning;
	SQLiteDatabase hist;
	SQLiteDatabase favo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edictionary);
		ref=(TextView)findViewById(R.id.text);
		Intent a=getIntent();
		Bundle b=a.getExtras();
		word=b.getString("word");
		meaning=b.getString("meaning");
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
	    actionBar.setTitle(word);
	    String s=actionBar.getTitle().toString();
	    ref.setText(meaning);
	    word=word.replace("'", "@");
	    word=word.replace(";", "#");
	    word=word.replace(",", "|");
	    meaning=meaning.replace("'", "@");
		meaning=meaning.replace(";", "#");
		meaning=meaning.replace(",", "|");
	    //db starts
	    hist=openOrCreateDatabase("myHistory",MODE_WORLD_READABLE,null);
	    String qr="create table if not exists his(dbword varchar,dbmean varchar);";
	    hist.execSQL(qr);
	    
		String qb="select *from his";
		Cursor c=hist.rawQuery(qb,null);
		boolean flag=false;
		while(c.moveToNext())
		{
			String dataWord=c.getString(0);
			if(dataWord.equals(word));
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			String del="delete from his where dbword='"+word+"';";
			hist.execSQL(del);
		} 
		String inse="insert into his values('"+word+"','"+meaning+"');";
		hist.execSQL(inse);
	}
	

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
	getMenuInflater().inflate(R.menu.edictionary, menu);
	return super.onCreateOptionsMenu(menu);
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	if(item.getItemId()==R.id.f)
	{
			favo=openOrCreateDatabase("myFavrouit",MODE_WORLD_READABLE,null);
			String qr="create table if not exists fav(dbword varchar,dbmean varchar);";
			favo.execSQL(qr);
			String q="select *from fav";
			Cursor c=favo.rawQuery(q,null);
			boolean flag=false;
			while(c.moveToNext())
			{
				String dataWord=c.getString(0);
				if(dataWord.equals(word));
				{
					flag=true;
					break;
				}
			}
			if(flag)
			{
				//delete
				String del="delete from fav where dbword='"+word+"';";
				favo.execSQL(del);
			}
			//insert
			String insert="insert into fav values('"+word+"','"+meaning+"');";
			favo.execSQL(insert);
			Toast.makeText(this,"Added To Favourite",1000).show();
	}
	
	return super.onOptionsItemSelected(item);
}
}


