package com.example.englishdictionary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class Dictionary extends Activity implements OnClickListener{

	Button search;
	String box="";
	String filledBox="";
	InputStream inputStream;
	String wordsmean;
	AutoCompleteTextView ref;
	Thread dicThread ;
	ArrayAdapter<String> ad;
	boolean flag=true;
	SQLiteDatabase hist;
	SQLiteDatabase favo;
	//boolean firsttime=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary);
		search=(Button)findViewById(R.id.bsearch);
		ref = (AutoCompleteTextView)findViewById(R.id.search);
		String onlyWord=readWord();
		String autoWord[]=onlyWord.split("#");
		ad = new ArrayAdapter<String>(Dictionary.this,android.R.layout.simple_expandable_list_item_1,autoWord);
		ref.setAdapter(ad);
		search.setOnClickListener(this);
		}
	public void choiseMean()
	{
		
		filledBox=ref.getText().toString();
		if(filledBox.equals(null)||filledBox==null)
		{
			flag=false;
		}
		else
		{
			flag=true;
			String check=filledBox.toLowerCase();
			char ch=check.charAt(0);
			if(ch=='-')
				ch=check.charAt(1);
			switch (ch)
			{
			case 'a':
			{
				inputStream = getResources().openRawResource(R.raw.a);
				break;
			}
			case 'b':
			{
				inputStream = getResources().openRawResource(R.raw.b);
				break;
			}
			case 'c':
			{
				inputStream = getResources().openRawResource(R.raw.c);
				break;
			}
			case 'd':
			{
				inputStream = getResources().openRawResource(R.raw.d);
				break;	
			}
			case 'e':
			{
				inputStream = getResources().openRawResource(R.raw.e);
				break;
			}
			case 'f':
			{
				inputStream = getResources().openRawResource(R.raw.f);
				break;	
			}
			case 'g':
			{
				inputStream = getResources().openRawResource(R.raw.g);
				break;	
			}
			case 'h':
			{
				inputStream = getResources().openRawResource(R.raw.h);
				break;	
			}
			case 'i':
			{
				inputStream = getResources().openRawResource(R.raw.i);
				break;	
			}
			case 'j':
			{
				inputStream = getResources().openRawResource(R.raw.j);
				break;
			}
			case 'k':
			{
				inputStream = getResources().openRawResource(R.raw.k);
				break;
			}
			case 'l':
			{
				inputStream = getResources().openRawResource(R.raw.l);
				break;
			}
			case 'm':
			{
				inputStream = getResources().openRawResource(R.raw.m);
				break;
			}
			case 'n':
			{
				inputStream = getResources().openRawResource(R.raw.n);
				break;
			}
			case 'o':
			{
				inputStream = getResources().openRawResource(R.raw.o);
				break;
			}
			case 'p':
			{
				inputStream = getResources().openRawResource(R.raw.p);
				break;
			}
			case 'q':
			{
				inputStream = getResources().openRawResource(R.raw.q);
				break;
			}
			case 'r':
			{
				inputStream = getResources().openRawResource(R.raw.r);
				break;
			}
			case 's':
			{
				inputStream = getResources().openRawResource(R.raw.s);
				break;
			}
			case 't':
			{
				inputStream = getResources().openRawResource(R.raw.t);
				break;
			}
			case 'u':
			{
				inputStream = getResources().openRawResource(R.raw.u);
				break;
			}
			case 'v':
			{
				inputStream = getResources().openRawResource(R.raw.v);
				break;
			}
			case 'w':
			{
				inputStream = getResources().openRawResource(R.raw.w);
				break;
			}
			case 'x':
			{
				inputStream = getResources().openRawResource(R.raw.x);
				break;
			}
			case 'y':
			{
				inputStream = getResources().openRawResource(R.raw.y);
				break;
			}
			case 'z':
			{
				inputStream = getResources().openRawResource(R.raw.z);
				break;
			}
			default:
			{
				inputStream = getResources().openRawResource(R.raw.extra);
				break;
			}
			}
			box=filledBox;
		}
	}
	public String readTxt()
	{
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    int i;
	    try {
	    	i = inputStream.read();
	    	while (i != -1)
	    		{
	    			byteArrayOutputStream.write(i);
	    			i = inputStream.read();
	    		}
	    	inputStream.close();
	    	}
	    catch (IOException e) 
	    {
		  Toast.makeText(this,"FILE ERROR" ,1000).show();
	    }
	    return byteArrayOutputStream.toString();
	 }
	public String readWord()
	{
		InputStream readword=getResources().openRawResource(R.raw.onlywords);
	    ByteArrayOutputStream byteArrayOutputStrea = new ByteArrayOutputStream();
	    int i;
	    try {
	    	i = readword.read();
	    	while (i != -1)
	    		{
	    			byteArrayOutputStrea.write(i);
	    			i = readword.read();
	    		}
	    	readword.close();
	    	}
	    catch (IOException e) 
	    {
		  Toast.makeText(this,"FILE ERROR" ,1000).show();
	    }
	    
	    return byteArrayOutputStrea.toString();
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(filledBox.equals(null)||filledBox==null)
		{
			flag=false;
		}
		if(check())
		{
		choiseMean();
		String userword=ref.getText().toString();
		userword=userword.trim();
		{
		String workingarr[]= searchWordMean(userword);
		if(workingarr[0].equals("NO MATCH FOUND"))
		{
			Toast.makeText(this,"NO MATCH FOUND" ,1000).show();
		}
		else
		{
		Intent b= new Intent(Dictionary.this,EDictionary.class);
		b.putExtra("word",workingarr[0]);
		b.putExtra("meaning",workingarr[1]);
		startActivity(b);
		}
		}
		}else
		{
			flag=true;
			Toast.makeText(this,"ENTER THE WORD TO SEARCH" ,1000).show();
		}
	}
	public String [] searchWordMean (String s)
	{
		String required[]=new String[2];
		required[0]="NO MATCH FOUND";
		required[1]="NO MATCH FOUND";
		try{
		int m=0;
		s=s.trim();
		String text=readTxt();
		String arr[]=text.split("\n"); 
		String newarr[]=new String[arr.length];
		for(int i=0;i<arr.length;i++)
			{
			
			String my=arr[i];
			my=my.trim();
				if(my.equals(null)||my.length()<=2||my==null)
					{
					continue;
					}
				else
					{
						newarr[m]=arr[i];
						m++;
					}
			
			}
		String words[]=new String[m];
		String meaning[]=new String[m];
		for(int i=0;i<m;i++)
		{
			 int l=newarr[i].length();
		    for(int j=0;j<l-1;j++)
		    {
		    	if(newarr[i].equals(null)||newarr[i].length()<=2||newarr[i]==null)
				{
				continue;
				}
		    else if(newarr[i].charAt(j)==' '&&newarr[i].charAt(j+1)==' ')
		        {
		            words[i]=newarr[i].substring(0,j);
		            meaning[i]=newarr[i].substring(j+2);
		            break;
		        }
		    }
			
		}
		
		
		for(int i=0;i<m;i++)
		{
			String wordsmean =words[i];
			if(wordsmean==null||wordsmean.equals(null))
				{
					continue;
				}
			else
				{
				wordsmean =words[i];
				wordsmean=wordsmean.trim();
				if(wordsmean.equalsIgnoreCase(s))
				
			{
				required[0]=words[i];
				required[1]=meaning[i];
				break;
			}
				}
		}
		
	}
	catch(Exception e)
	{
		Toast.makeText(this,"Error : " ,1000).show();
	}
		return required;
	}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
	getMenuInflater().inflate(R.menu.dict, menu);
	return super.onCreateOptionsMenu(menu);
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	if(item.getItemId()==R.id.omi2)
	{
		Intent i=new Intent(this,History.class);
		startActivity(i);
	}
	else if(item.getItemId()==R.id.omi1)
	{
		Intent i=new Intent(this,Setting.class);
		startActivity(i);
		
	}
	else if(item.getItemId()==R.id.omi3)
	{
		Intent i=new Intent(this,Favorites.class);
		startActivity(i);
		
	}
	return super.onOptionsItemSelected(item);
}
	@Override
	protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	SharedPreferences s=PreferenceManager.getDefaultSharedPreferences(this);
	String mfav=s.getString("mf","Always Save Favorites");
	String mhis=s.getString("mh","Always Save History");
	if(mfav.equals("Never Save Favorites"))
	{
		favo=openOrCreateDatabase("myFavrouit",MODE_WORLD_READABLE,null);
		String qr="create table if not exists fav(dbword varchar,dbmean varchar);";
		favo.execSQL(qr);
		String del="delete from fav";
		favo.execSQL(del);
	}
	if(mhis.equals("Never Save History"))
	{
		hist=openOrCreateDatabase("myHistory",MODE_WORLD_READABLE,null);
	    String qr="create table if not exists his(dbword varchar,dbmean varchar);";
	    hist.execSQL(qr);
	    String del="delete from his";
	    hist.execSQL(del);
	}
}
	public boolean check()
	{
		filledBox=ref.getText().toString();
		if(filledBox.equals(""))
		{
			flag=false;
		}
		else
		{
			flag=true;
		}
		return flag;
	}

}
