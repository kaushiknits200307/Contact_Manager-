package com.example.contactmanager;
import Dao.Contact;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
public class EditContact extends Activity  {
	int i=0 , id ;
	EditText t1,t2,t3,t4,t5;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_cont);
        long id = getIntent().getExtras().getLong("ContactID");
        Log.e("****************", "6" + id);
        //t1.setSelection(position);
     //   Toast.makeText(getApplicationContext(),"hello" + id + "", 1000).show();
       /* Toast.makeText(getApplicationContext(), Integer.toString(id), 1000).show();
        Log.e("****************", "5" + Integer.toString(id));*/
        initView();
        Contact d=new Contact(this);
        d.openCon();
       Cursor c = d.getAllContacts();
       if( c.moveToFirst() && Long.parseLong(c.getString(0)) == (id))
       {    	 
    	   Log.e("****************", "7" + c.getString(0));
    	  t1.setText(c.getString(1));
          t2.setText(c.getString(2));
          t3.setText(c.getString(3));
          t4.setText(c.getString(4));
          t5.setText(c.getString(5));
          int position = c.getString(1).length() ;
          t1.setSelection(position);
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
    	  t1.setText(c.getString(1));
       t2.setText(c.getString(2));
       t3.setText(c.getString(3));
       t4.setText(c.getString(4));
       t5.setText(c.getString(5));
       int position = c.getString(1).length() ;
       t1.setSelection(position);
       d.closeCon();
      }
	}       
public void save2(View v)
{
//	int id = getIntent().getExtras().getInt("ContactID");
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
		finish();
		
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
				Toast.makeText(this, "deleted", 10000).show();
	      }
		qd.closeCon();
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
		t1=(EditText)findViewById(R.id.name2);
		t2=(EditText)findViewById(R.id.phone2);
		t3=(EditText)findViewById(R.id.email2);
		t4=(EditText)findViewById(R.id.street2);
		t5=(EditText)findViewById(R.id.note2);
		}
	
	
	
}