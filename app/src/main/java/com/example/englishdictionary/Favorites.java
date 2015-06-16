package com.example.englishdictionary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Favorites extends ListActivity {	
	SQLiteDatabase favo;
	String favWord[]=new String[100];
	String favMean[]=new String[100];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		try{
		String q="select *from fav";
		favo=openOrCreateDatabase("myFavrouit",MODE_WORLD_READABLE,null);
		Cursor c=favo.rawQuery(q,null);
		int i=0;
		int total=c.getCount();
		String arrWord[]=new String[total];
		String arrMean[]=new String[total];
		
		while(c.moveToNext())
		{
			String nword=c.getString(0);
			String nmean=c.getString(1);
			nword=nword.replace("@", "'");
		    nmean=nmean.replace("@", "'");
			nmean=nmean.replace("#", ";");
			arrWord[i]=nword;
			arrMean[i]=nmean;
			i++;
		}
		int count=i;
		String myDicWords[]=new String[count];
		for(int j=0;j<count;j++)
		{
			i=i-1;
			favWord[j]=arrWord[i];
			favMean[j]=arrMean[i];
			myDicWords[j]=arrWord[i];
			
		}
		
		
		ArrayAdapter<String>  ad;
		ad=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,myDicWords);
		setListAdapter(ad);	
		}catch(Exception e)
		{
			Toast.makeText(this,"NO ITEM IN FAVORITE",1000).show();
		}
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) //position gives the value from 0 to n same as array index
	{
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent b= new Intent(Favorites.this,ViewHisFav.class);
		b.putExtra("word",favWord[position]);
		b.putExtra("meaning",favMean[position]);
		startActivity(b);
	}
	
}
