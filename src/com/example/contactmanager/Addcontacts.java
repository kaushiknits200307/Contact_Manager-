package com.example.contactmanager;
import DTO.DD;
import Dao.Contact;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
public class Addcontacts extends Activity {
EditText t1,t2,t3,t4,t5,t7;
int x=0;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.add_contact);
	        
	       // t1.setFocusable(false);
	        initView() ;
	 }
	 private void initView()
		{
			t1=(EditText)findViewById(R.id.name1);
			t2=(EditText)findViewById(R.id.phone1);
			t3=(EditText)findViewById(R.id.email1);
			t4=(EditText)findViewById(R.id.street1);
			t5=(EditText)findViewById(R.id.note1);
		}
	 public void submit(View v){
		 if(t1.getText().toString().equals("") && t2.getText().toString().equals("") && t3.getText().toString().equals("") && t4.getText().toString().equals("") && t5.getText().toString().equals(""))
		 {
			 Toast.makeText(this, "Please enter something ", 10000).show();
		 }
		 else
		 {
		 int x=1;
			int yy = (int) System.currentTimeMillis();
	     		/*DD d2=new DD();
				d2.setName(c_name);
				d2.setPhone(a1);
				d2.setEmail(a2);
				d2.setAdr(a3);
				d2.setNote(a4);*/
				Contact qd=new Contact(this);
				qd.openCon();
			//Cursor c = null;
					Cursor c = qd.getAllContacts();
				if( c.moveToFirst() )
				{
					String c_name=t1.getText().toString();
					String a1=t2.getText().toString();
					String a2=t3.getText().toString();
					String a3=t4.getText().toString();
					String a4=t5.getText().toString();
					do
			    	   {
			    		   x++;
			    	   } 
			    	   while(c.moveToNext());
					
					{
					if(qd.insert(yy,c_name,a1,a2,a3,a4)>0)
					Toast.makeText(this, "Saved ", 10000).show();
					}
					/*Intent in=new Intent(Addcontacts.this,MainActivity.class);
					startActivity(in);*/
					finish();
				}
				else
				{String c_name=t1.getText().toString();
				String a1=t2.getText().toString();
				String a2=t3.getText().toString();
				String a3=t4.getText().toString();
				String a4=t5.getText().toString();
				if(qd.insert(yy,c_name,a1,a2,a3,a4)>0)
					Toast.makeText(this, "Saved ", 10000).show();
				}
				qd.closeCon();
				
				/*Intent in=new Intent(Addcontacts.this,MainActivity.class);
				startActivity(in);*/	
			finish();
		 }
		}
	 
	 
	/* @Override
		protected void onPause() {
			super.onPause();
			Intent in=new Intent(Addcontacts.this,MainActivity.class);
			startActivity(in);
			finish();
		}
	 
	 
	 @Override
		protected void onStop() {
			super.onStop();		
			
	 }
	 
	 
	 @Override
		protected void onDestroy() {
			super.onDestroy();
		}*/
}