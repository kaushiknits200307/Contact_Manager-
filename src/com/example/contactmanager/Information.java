package com.example.contactmanager;

import java.util.ArrayList;

import DTO.DD;
import Dao.Contact;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Information extends Activity {
	TextView t1,t2,t3,t4,t5;
 String a="",f="",g="",b="",e="";
ImageButton btnSwitch1,btnSwitch2,btnSwitch3,btnSwitch4;
int x=0;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.detail);
	        
	       
	        long id = getIntent().getExtras().getLong("ContactID");
	        btnSwitch1 = (ImageButton) findViewById(R.id.image2);
	        btnSwitch2 = (ImageButton) findViewById(R.id.image3);
	       btnSwitch3 = (ImageButton) findViewById(R.id.image4);
	       btnSwitch4 = (ImageButton) findViewById(R.id.image5);
	        initView();
	        Contact d=new Contact(this);
	        d.openCon();
	        Cursor c = d.getAllContacts();
	       if( c.moveToFirst() && Long.parseLong(c.getString(0)) == (id))
	       {    	 
	    	 //  Toast.makeText(this, c.getString(0), 10000).show();
	    	   Log.e("****************", "7" + c.getString(0));
	    	  t1.setText(" " + c.getString(1));
	          t2.setText(" " + c.getString(2));
	          t3.setText(" " + c.getString(3));
	          t4.setText(" " + c.getString(4));
	          t5.setText(" " + c.getString(5));
	          a=c.getString(1);
	          b=c.getString(2);
	          f=c.getString(3);
	          g=c.getString(4);
	          e=c.getString(5);
	       }
	      /* int pt = Integer.parseInt(c.getString(0));
	       Toast.makeText(this, "delete successfull" + pt, 10000).show();*/
	      /* t1.setText(c.getString(1));
	       t2.setText(c.getString(2));
	       t3.setText(c.getString(3));
	       t4.setText(c.getString(4));
	       t5.setText(c.getString(5));*/
	    /* if( c.moveToFirst() && Long.parseLong(c.getString(0))== id )
	       {
	    	 
	    	  t1.setText(c.getString(1));
	          t2.setText(c.getString(2));
	          t3.setText(c.getString(3));
	          t4.setText(c.getString(4));
	          t5.setText(c.getString(5));
	       }*/
	      else
	      {
	    	  do
	    	   {
	    		  if(Long.parseLong(c.getString(0)) == (id))
	    		  {
	    			  break;
	    		  }
	    	   }
	    	  while(c.moveToNext() );
				// Toast.makeText(this, c.getString(0), 10000).show();
	    	  t1.setText(" " + c.getString(1));
	       t2.setText(" " + c.getString(2));
	       t3.setText(" " + c.getString(3));
	       t4.setText(" " + c.getString(4));
	       t5.setText(" " + c.getString(5));
	       a=c.getString(1);
	          b=c.getString(2);
	          f = c.getString(3);
	          g=c.getString(4);
	          e=c.getString(5);
	       d.closeCon();
	      }
	       btnSwitch1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					/*Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						    Uri.parse("google.navigation:q=an+address+city"));
*/					if (b.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Number empty", 10000).show();
					}
					else
					{
						 String uri = "tel:" + b ;
						 Intent intent = new Intent(android.content.Intent.ACTION_DIAL,Uri.parse(uri));
						// intent.setData(Uri.parse(uri));
						 startActivity(intent);
					}
				}
			});
	       
	       
	       
	       btnSwitch4.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					 
					
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						    Uri.parse("google.navigation:q=an+address+city"));
					if (b.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Address empty", 10000).show();
					}
					else
					{
						Intent intent11 = new Intent(android.content.Intent.ACTION_VIEW,
							    Uri.parse("google.navigation:q = " + g));
						if (intent11.resolveActivity(getPackageManager()) != null) {
							   startActivity(intent11);}
					}
				}
			});
			/*
			 * 
			 *  <ImageButton
			          android:id="@+id/image5"
			          android:layout_width="35dp"
			          android:layout_height="35dp"
			          android:layout_gravity="right"
			          android:layout_toRightOf="@+id/editText1"
			          android:gravity="center"
			          android:padding="2dp"
			          android:scaleType="fitXY"
			           android:layout_marginRight="50dp"
			          android:src="@drawable/address" />
			 */
			
	       
	       
	       
	        btnSwitch2.setOnClickListener(new View.OnClickListener() {
	   				@Override
	   				public void onClick(View v) {
	   				 if(Utility.isNotNull(f) )
	   				 {
                             
	   			        String[] to = f.split(",");
	   					//String[] to = {"busybody@yourcompany.com"};
	   					// String[] cc = {"busybody@yourcompany.com"};
	   			        sendEmail(to, "", "");

	   					 
	   			   }
	   		            // When Email entered is Valid
	   		           /*{
	   		            	Intent emailIntent = new Intent(Intent.ACTION_SEND);
	   		             
	   		             emailIntent.setData(Uri.parse("mailto:"));
	   		             emailIntent.setType("text/plain");
	   		             emailIntent.putExtra(Intent.EXTRA_EMAIL, f);
	   		             emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
	   		             emailIntent.putExtra(Intent.EXTRA_TEXT, "");
	   		             
	   		             try {
	   		                startActivity(Intent.createChooser(emailIntent, "Email"));
	   		             } catch (android.content.ActivityNotFoundException ex) {
	   		                Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
	   		             }
	   		            } */
	   		            // When Email is invalid
	   		         
	   				 else
	   				 {
	   					{
		   		                Toast.makeText(getApplicationContext(), "Please enter  email", Toast.LENGTH_LONG).show();
		   		            }
	   				 }
	   				}


	   			    private void sendEmail(String[] emailAddresses, 
	   			    String subject, String message)
	   			    {
	   			        Intent emailIntent = new Intent(Intent.ACTION_SEND);
	   			        emailIntent.setData(Uri.parse("mailto:"));
	   			        String[] to = emailAddresses;
	   			       // String[] cc = carbonCopies;
	   			        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
	   			      //  emailIntent.putExtra(Intent.EXTRA_CC, cc);
	   			        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
	   			        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
	   			        emailIntent.setType("message/rfc822");
	   			        startActivity(Intent.createChooser(emailIntent, "Email"));
	   			    }    
	   			});
	        btnSwitch3.setOnClickListener(new View.OnClickListener() {
	   				@Override
	   				public void onClick(View v) {
	   					if (b.isEmpty())
						{
							Toast.makeText(getApplicationContext(), "Number empty", 10000).show();
						}
						else
						{
						Intent i = new Intent(android.content.Intent.ACTION_VIEW);
						i.putExtra("address", b);
						i.putExtra("sms_body",e );
						i.setType("vnd.android-dir/mms-sms");
							 startActivity(i);
						}
	   				}
	   			});
		}       

		      
	


	public void save2(View v)
	{
		  long id = getIntent().getExtras().getLong("ContactID");
		Intent in = new Intent(getApplicationContext() , EditContact.class);
		in.putExtra("ContactID", id);
		startActivity(in);
/*//		int id = getIntent().getExtras().getInt("ContactID");
		long id = getIntent().getExtras().getLong("ContactID");
		initView();
		int i=0;
			Contact qd=new Contact(this);
			qd.openCon();
			Cursor c = qd.getAllContacts();
					if( c.moveToFirst()  && Long.parseLong(c.getString(0)) == (id))
		       {
				Long pt = Long.parseLong(c.getString(0));
				String c_name=t1.getText().toString();
				String a1=t2.getText().toString();
				String a2=t3.getText().toString();
				String a3=t4.getText().toString();
				String a4=t5.getText().toString();
				if(qd.updatecc(pt, c_name, a1, a2, a3, a4)>0)//id starts with 0 but in db id starts with 1
					Toast.makeText(this, "update successfull", 10000).show();
				}
		      else
		      {
		    	  while(c.moveToNext())
		    	  {
		    		if(Long.parseLong(c.getString(0)) == (id))
		    			break;
		    	  }
		    	  long pt = Long.parseLong(c.getString(0));
		    	  String c_name=t1.getText().toString();
					String a1=t2.getText().toString();
					String a2=t3.getText().toString();
					String a3=t4.getText().toString();
					String a4=t5.getText().toString();
			//		Toast.makeText(this,c.getString(1), 10000).show();
					if(qd.updatecc(pt,c_name, a1,a2,a3,a4)>0)
					Toast.makeText(this, "update successfull", 10000).show();
		      }
			qd.closeCon();
			finish();*/
			
			/*Intent in=new Intent(this,MainActivity.class);
			startActivity(in);
			*/
	}
	public void delete(View v){
		//int id = getIntent().getExtras().getInt("ContactID");
		long id = getIntent().getExtras().getLong("ContactID");
		initView();
		int i=0;
			Contact qd=new Contact(this);
			qd.openCon();
			Cursor c = qd.getAllContacts();
					if( c.moveToFirst()  && Long.parseLong(c.getString(0)) == (id))
		       {
				Long pt = Long.parseLong(c.getString(0));
				String c_name=t1.getText().toString();
				String a1=t2.getText().toString();
				String a2=t3.getText().toString();
				String a3=t4.getText().toString();
				String a4=t5.getText().toString();
				if(qd.deleteCon(pt))
					Toast.makeText(this, "deleted", 10000).show();
				/*Intent in=new Intent(this,MainActivity.class);
				startActivity(in);*/	
			finish();
				/*if(qd.updatecc(pt, c_name, a1, a2, a3, a4)>0)//id starts with 0 but in db id starts with 1
					Toast.makeText(this, "update successfull", 10000).show();*/
				}
		      else
		      {
		    	  while(c.moveToNext())
		    	  {
		    		if(Long.parseLong(c.getString(0)) == (id))
		    			break;
		    	  }
		    	  /*while(i<id)
		    	  {
		    		  c.moveToNext();
		    		  i++;
		    	  }*/
		    	  Long pt = Long.parseLong(c.getString(0));
		    	  String c_name=t1.getText().toString();
					String a1=t2.getText().toString();
					String a2=t3.getText().toString();
					String a3=t4.getText().toString();
					String a4=t5.getText().toString();
					int k = i+1;
					//Toast.makeText(this,c.getString(1), 10000).show();
					if(qd.deleteCon(pt))
					Toast.makeText(this, "Contact deleted", 10000).show();
		      }
			qd.closeCon();
			/*Intent in=new Intent(this,MainActivity.class);
			startActivity(in);*/	
		finish();
			/*Intent in=new Intent(this,MainActivity.class);
			startActivity(in);
			finish();*/
			//why given below code didn't work ?
		/*initView();
		int id = getIntent().getExtras().getInt("ContactID");
		 Log.e("****************", "5" + id);
		 Contact d=new Contact(this);
	     d.openCon();
		 Cursor c = d.getAllContacts();
		 if( c.moveToFirst() && id==0)
	     {
	  	  t1.setText(c.getString(1));
	        t2.setText(c.getString(2));
	        t3.setText(c.getString(3));
	        t4.setText(c.getString(4));
	        t5.setText(c.getString(5));
	     }
	    else
	    {
	  	  do
	  	   {
	  		   i++;
	  	   }while(c.moveToNext() && i<id);
	     }
		 c.moveToFirst();
		 Log.e("****************", "5" + i);
		 while(i<id )
		 {
			 c.moveToNext();
			 i=i+1;
			// int pt = Integer.parseInt(c.getString(0));
		 }
		 if( c.moveToFirst())
	     {
	  	   do
	  	   {
	  		   i=i+1;
	  	   }while( c.moveToNext() && i<id);
	     }
	       
	        int x= pt + 1;
		 String ptr = c.getString(1);
		String c_name=t1.getText().toString();
		String a1=t2.getText().toString();
		String a2=t3.getText().toString();
		String a3=t4.getText().toString();
		String a4=t5.getText().toString();
		Toast.makeText(this, ptr, 10000).show();
		//Toast.makeText(this, "delete successfull" + pt, 10000).show();
		//if(d.deleteCon(pt))
		//Toast.makeText(this, "delete successfull", 10000).show();
		d.closeCon();
		Intent in=new Intent(this,MainActivity.class);
		startActivity(in);
		finish();*/												
	}
		private void initView()
		{
			t1=(TextView)findViewById(R.id.name2);
			t2=(TextView)findViewById(R.id.phone2);
			t3=(TextView)findViewById(R.id.email2);
			t4=(TextView)findViewById(R.id.street2);
			t5=(TextView)findViewById(R.id.note2);
			}
		@Override
		protected void onResume() {
			super.onResume();
			 long id = getIntent().getExtras().getLong("ContactID");
	        initView();
	        Contact d=new Contact(this);
	        d.openCon();
	        Cursor c = d.getAllContacts();
	       if( c.moveToFirst() && Long.parseLong(c.getString(0)) == (id))
	       {    	 
	    	   Log.e("****************", "7" + c.getString(0));
	    	  t1.setText(" " + c.getString(1));
	          t2.setText(" " + c.getString(2));
	          t3.setText(" " + c.getString(3));
	          t4.setText(" " + c.getString(4));
	          t5.setText(" " + c.getString(5));
	          a=c.getString(1);
	          b=c.getString(2);
	          f=c.getString(3);
	          g=c.getString(4);
	          e=c.getString(5);
	       }
	      /* int pt = Integer.parseInt(c.getString(0));
	       Toast.makeText(this, "delete successfull" + pt, 10000).show();*/
	      /* t1.setText(c.getString(1));
	       t2.setText(c.getString(2));
	       t3.setText(c.getString(3));
	       t4.setText(c.getString(4));
	       t5.setText(c.getString(5));*/
	    /* if( c.moveToFirst() && Long.parseLong(c.getString(0))== id )
	       {
	    	 
	    	  t1.setText(c.getString(1));
	          t2.setText(c.getString(2));
	          t3.setText(c.getString(3));
	          t4.setText(c.getString(4));
	          t5.setText(c.getString(5));
	       }*/
	      else
	      {
	    	  do
	    	   {
	    		  if(Long.parseLong(c.getString(0)) == (id))
	    		  {
	    			  break;
	    		  }
	    	   }
	    	  while(c.moveToNext() );
	    	  t1.setText(" " + c.getString(1));
	       t2.setText(" " + c.getString(2));
	       t3.setText(" " + c.getString(3));
	       t4.setText(" " + c.getString(4));
	       t5.setText(" " + c.getString(5));
	       a=c.getString(1);
	          b=c.getString(2);
	          f=c.getString(3);
	          g=c.getString(4);
	          e=c.getString(5);
	       d.closeCon();
	      }
		       }
		/*@Override
			protected void onPause() {
				super.onPause();
				Intent in=new Intent(this,MainActivity.class);
				startActivity(in);
				finish();
			}*/
	 	
	} 