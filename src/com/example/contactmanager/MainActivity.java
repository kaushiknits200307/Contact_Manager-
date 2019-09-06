package com.example.contactmanager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.PrivateKey;
import java.util.ArrayList;
import DTO.DD;
import Dao.Contact;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity{
	  ListView lv;
	  int  progress = 0;
	  String   x1="",x2="",x3="",x4="",x5="",x6="";
	  int hasPhone =9;
	  EditText inputSearch;
	  ArrayAdapter<String> adapter;
	  String vfile;
	  Cursor cursor;
	  static int progress1;
	  //  ProgressBar progressBar;
	    int progressStatus = 10;
	    Handler handler = new Handler();
	  ProgressBar progressBar;
	  FileWriter fw;
	    ArrayList<String> vCard ;
	    static final int READ_BLOCK_SIZE = 100;
	  String number ="123qq";
	  Contact d88=new Contact(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        bar.setTitle(Html.fromHtml("<font color='#000000'>ContactManager </font>"));*/
       // progressBar=(ProgressBar) findViewById(R.id.progressBar1);  
    //    progressBar.setVisibility(View.GONE);
        ListView  lv=(ListView)findViewById(R.id.listView1);
         inputSearch = (EditText) findViewById(R.id.inputSearch);
         Contact d=new Contact(this);
	        d.openCon();
	        Cursor c=d.getAllContacts();
	        ArrayList a=new ArrayList();
	        while(c.moveToNext()){
	        	DD d1=new DD();
	        	d1.setId(c.getString(0));
	        	d1.setName(c.getString(1));
	        	d1.setEmail(c.getString(3));
	        	a.add(d1);
	        }
	        inputSearch.addTextChangedListener(new TextWatcher() {
	   	@Override
	    		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	    			// TODO Auto-generated method stub
	    			adapter.getFilter().filter(arg0);
	    		}
	  		@Override
	    		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	    				int arg3) {
	    			// TODO Auto-generated method stub
	    			   		}
	    		@Override
	    		public void afterTextChanged(Editable arg0) {
	    			// TODO Auto-generated method stub
	    			}
	    	})  ;
	        
	      adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, a);
	     
	        lv.setAdapter(adapter);
	        lv.setOnItemClickListener(new OnItemClickListener() {
	        	@Override
	    		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	    				long arg3) {
	        		DD link = (DD) arg0.getItemAtPosition(position);
	        		Intent openDetails = new Intent(getApplicationContext(), EditContact.class);
	                Bundle b = new Bundle();
	                 long idd = Long.parseLong(link.getId());
	                 /*Toast.makeText(getApplicationContext(),"hello" + idd + "", 1000).show();
	                 Log.e("****************", "5" + idd);
	                b.putString("id", link.getId());
	                b.putString("name", link.getName());
	                b.putString("phone", link.getPhone());
	                b.putString("email", link.getEmail());
	                b.putString("adr", link.getAdr());
	                b.putString("note", link.getNote());
	                openDetails.putExtras(b);
	                startActivity(openDetails); good  code*/
	    			Intent in = new Intent(getApplicationContext() , Information.class);
	    			in.putExtra("ContactID", idd);
	    			startActivity(in);
	    			//finish();
	    		}
	        });
	        d.closeCon();
    }
    protected Object getActivity() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.add_contact:
        	//Addcontact();
        	 final ProgressDialog dialog1 = ProgressDialog.show(
        				this, "Add Contact Loading", "Please wait...", true);
        		    new Thread(new Runnable(){
        			    public void run(){
        				    try {
        				    	
        				    	Addcontact();
        				    	//---simulate doing something lengthy---
        				       Thread.sleep(500);
        				        //---dismiss the dialog---
        						dialog1.dismiss();
        					} catch (InterruptedException e) {					
        					    e.printStackTrace();
        					}		
        				}
        			}).start();
        		    return true;
         case R.id.importcontact:
            	  importFile();
            	  return true;
               
         case R.id.importcontact1:
            	   importContactph();
            	/* Intent i101 = new Intent( getApplicationContext(), MainActivity.class);
                      startActivity(i101);
                      finish();*/
                   return true;
             case R.id.exportcontact:
            	 exportContacts();
            	 //Check for ImportPro
         		 
           return true;
        case R.id.removediplicate:
          //remove duplicate
        	removeDup();
        	
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
       
       
    }
  
	
	
	private void importFile() {
		// TODO Auto-generated method stub
		 new ExecuteTask1().execute(d88);
	}
	private void importContactph() {  
		  new ExecuteTask2().execute(d88); 
	}
	
	private void exportContacts() {
		new ExecuteTask3().execute(d88); 
	}
	private void removeDup() {
		new ExecuteTask4().execute(d88);
	}
	
	 class ExecuteTask4 extends AsyncTask<Contact , Integer, String>  
     {  
		 ProgressDialog progressDialog;
		 @Override
         protected void onPreExecute() {
             ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,
                     "Please Wait!",
                     "Wait while duplicate contacts are being removed");
         }
		 
         @Override  
         protected String doInBackground(Contact... param) { 
         	d88.openCon();
         	Cursor cee = d88.getAllContacts();
              if( d88.deleted()>0)
            d88.closeCon();
             return "";  
         }  
           
         @Override  
         protected void onPostExecute(String result) {
        	 Intent idup = new Intent( getApplicationContext(), MainActivity.class);
     	    startActivity(idup);
     	    finish();
         } 
       
         
     }
	
	 class ExecuteTask3 extends AsyncTask<Contact , Integer, String>  
     {  
		 ProgressDialog progressDialog;
		 
		 @Override
         protected void onPreExecute() {
             ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,
                     "Please Wait!",
                     "Wait while contacts are exporting");
         }
		 
         @Override  
         protected String doInBackground(Contact... param) { 
        	    		  d88.openCon();
        	       	        d88.openCon();
        	       	        Cursor c55=d88.getAllContacts();
        	       	        ArrayList a=new ArrayList();
        	          if(c55.moveToFirst())
        	          {
        	        	  try
        	   			{
        	   	            //---SD Card Storage---
        	   	            File sdCard = Environment.getExternalStorageDirectory();
        	   	            File directory = new File (sdCard.getAbsolutePath() +
        	   	                "/MyFiles");
        	   	            directory.mkdirs();
        	   	            File file = new File(directory, "textfile.txt");
        	   	            FileOutputStream fOut = new FileOutputStream(file);
        		                  OutputStreamWriter osw = new
        	   				OutputStreamWriter(fOut);
        		                  do
        		               	  { 
        		      	        	osw.write(c55.getString(1) + ";");
        		      	        	osw.write(c55.getString(2) + ";");
        		      	        	osw.write(c55.getString(3)+ ";");
        		      	        	osw.write(c55.getString(4) + ";");
        		      	        	osw.write(c55.getString(5) +  ";");
        		      	        	osw.write("Version" +  "\n");
        		               	  } while (c55.moveToNext());
        		                //  osw.write("null");
        	   				osw.flush(); 
        	   				osw.close();
        	   				d88.closeCon();
        	   			}
        	   			catch (IOException ioe)
        	   			{
        	   				ioe.printStackTrace();
        	   			}
        	 	 	  
        	          }
        	 
             return "";  
         }  
           
         @Override  
         protected void onPostExecute(String result) {
        	 Intent i4 = new Intent( getApplicationContext(), MainActivity.class);
     	    startActivity(i4);
     	    finish();
         } 
       
         
     }
	
	 class ExecuteTask1 extends AsyncTask<Contact , Integer, String>  
     {  
		 ProgressDialog progressDialog;
		 
		 @Override
         protected void onPreExecute() {
             ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,
                     "Please Wait!",
                     "Wait while contacts are importing");
         }
		 
         @Override  
         protected String doInBackground(Contact... param) { 
        	    		  d88.openCon();
    		 Cursor c2222=d88.getAllContacts() ;
    		
    		 {
    		  try{      

    			  File sdCard = Environment.getExternalStorageDirectory();
    	            File directory = new File (sdCard.getAbsolutePath() + 
    	                "/MyFiles");
    	            File file = new File(directory, "textfile.txt");
    	            FileInputStream fIn = new FileInputStream(file);
    	            InputStreamReader isr = new InputStreamReader(fIn);
    	            BufferedReader br = new BufferedReader(isr);
    	            String data=br.readLine();
    	           while(data!=null)
    	           {
    	        	 /* String t[]=data.split(",");
    	 			Product p=new Product();
    	 			p.setFirst(t[0]);
    	 			p.setSec(t[1]);
    	 			p.setThird(t[2]);
    	 			p.setFour(t[3]);
    	 			p.setFive(t[4]);
    	 			p.setSix(t[5]);
    	 			df.insert(p);
    	 			data=br.readLine();*/
    	        	   
    	        	  int yy33 = (int) System.currentTimeMillis() + 7;
    	        	 String t[]=data.split(";");
    	        	x1=t[0];
    	        	x2=t[1];
    	        	x3=t[2];
    	        	x4=t[3];
    	        	x5=t[4];
    	        	x6=t[5];
    	        	/*if (t[0].isEmpty()){
    	        		x1=" ";
    	        		//Toast.makeText(getApplicationContext(), x1 , 1000).show();
    	        		}
    	        	else{   x1=t[0];}
    	        	if (t[1].isEmpty()){
    	        		x2=" ";
    	        		Toast.makeText(getApplicationContext(),"ratul" + x2 , 1000).show();
    	        		}
    	        	else{   x2=t[1];}
    	        	if (t[2].isEmpty()){
    	        		x3=" ";
    	        		}
    	        	else{   x3=t[2];}
    	        	if (t[3].isEmpty()){
    	        		x4=" ";
    	        		}
    	        	else{   x4=t[3];}
    	        	if (t[4].isEmpty()){
    	        		x5=" ";
    	        		}
    	        	else{   x5=t[4];}*/
    	  				
    	  				if(d88.insert(yy33,x1,x2,x3,x4,x5)>0);
    	  				data=br.readLine();
    	  			}

    	    		 d88.closeCon();
        }
           catch(Exception e)
           {
           } 
    		  
    		 }
        	 
             return "";  
         }  
           
         @Override  
         protected void onPostExecute(String result) {
         Intent i101 = new Intent( getApplicationContext(), MainActivity.class);
         startActivity(i101);
         finish();
         } 
       
         
     }
	 class ExecuteTask2 extends AsyncTask<Contact , Integer, String>  
     {  
		 ProgressDialog progressDialog;

		 @Override
         protected void onPreExecute() {
             ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,
                     "Please Wait!",
                     "Wait while Contacts are loading from phone book");
         }
		 
         @Override  
         protected String doInBackground(Contact... param) {
        	 Uri allContacts = ContactsContract.Contacts.CONTENT_URI;
        	    Cursor c88;
        	  //  if (android.os.Build.VERSION.SDK_INT <11) {
        	    	//---before Honeycomb---
        	        c88 = managedQuery(allContacts, null, 
        	                null,
        	                null, null);   
        	  //  } else {
        	    	//---Honeycomb and later---
        	       /* CursorLoader cursorLoader = new CursorLoader(
        	        		this, 
        	        		allContacts, 
        	        		null, 
        	               null,
        	                null,null);
        	        c88 = cursorLoader.loadInBackground(); */
        	    //}
        		 
        	  //  Contact d88=new Contact(this);
        	    d88.openCon();
        	    c88.moveToFirst();
        		if (c88.moveToFirst()) 
        		{
        	   do{
        		      	   String contactID = 
        	               c88.getString(c88.getColumnIndex(
        	                   ContactsContract.Contacts._ID));
        		             String contactDisplayName = 
        	              c88.getString(c88.getColumnIndex(
        	                  ContactsContract.Contacts.DISPLAY_NAME));
        		             String hasPhone = c88.getString(c88.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
        		             if ((Integer.parseInt(hasPhone)==1 ))
        		             { 
        	                	 Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
        	                	 null, 
        	                	 ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactID, 
        	                	 null, null); 
        	                	while (phones.moveToNext()) 
        	                	 { 
        	       	             int yy = (int) System.currentTimeMillis();
        	                	String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        	                	if(d88.insert(yy,contactDisplayName,number ,"","","")>0);
        	                	 } 
        	                	 phones.close();
        	                	 } 
        	                  }
        	   while (c88.moveToNext());
        	     }
        		d88.closeCon();
			return number; 
			}
         @Override  
         protected void onPostExecute(String result) {
         Intent i102 = new Intent( getApplicationContext(), MainActivity.class);
         startActivity(i102);
         finish();
         } 
       
     } 
private void Addcontact() {
	
        Intent i = new Intent(MainActivity.this, Addcontacts.class);
        startActivityForResult(i,1);
     // finish();
    }
@Override
protected void onResume() {
	super.onResume();
	ListView  lv=(ListView)findViewById(R.id.listView1);
    inputSearch = (EditText) findViewById(R.id.inputSearch);
    Contact d282=new Contact(this);
       d282.openCon();
       Cursor c282=d282.getAllContacts();
       ArrayList a=new ArrayList();
       while(c282.moveToNext()){
       	DD d1=new DD();
       	d1.setId(c282.getString(0));
       	d1.setName(c282.getString(1));
       	d1.setEmail(c282.getString(3));
       	a.add(d1);
      }
       adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, a);
       lv.setAdapter(adapter);
       }
}
// ***********************************************************************************************
/*private int importContact()
{
	Uri allContacts = ContactsContract.Contacts.CONTENT_URI;
    Cursor c88;
    if (android.os.Build.VERSION.SDK_INT <11) {
    	//---before Honeycomb---
        c88 = managedQuery(allContacts, null, 
                null,
                null, null);   
    } else {
    	//---Honeycomb and later---
        CursorLoader cursorLoader = new CursorLoader(
        		this, 
        		allContacts, 
        		null, 
               null,
                null,null);
        c88 = cursorLoader.loadInBackground(); 
    }
	 
    Contact d88=new Contact(this);
    d88.openCon();
    c88.moveToFirst();
	if (c88.moveToFirst()) 
	{
   do{
	      	   String contactID = 
               c88.getString(c88.getColumnIndex(
                   ContactsContract.Contacts._ID));
	             String contactDisplayName = 
              c88.getString(c88.getColumnIndex(
                  ContactsContract.Contacts.DISPLAY_NAME));
	             String hasPhone = c88.getString(c88.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
	             if ((Integer.parseInt(hasPhone)==1 ))
	             { 
                	 Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                	 null, 
                	 ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactID, 
                	 null, null); 
                	while (phones.moveToNext()) 
                	 { 
       	             int yy = (int) System.currentTimeMillis();
                	String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                	if(d88.insert(yy,contactDisplayName,number ,"","","")>0);
                	 } 
                	 phones.close();
                	 } 
                  }
   while (c88.moveToNext());
     }
	d88.closeCon();
	return 101;
}*/