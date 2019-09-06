package Dao;


import DTO.DD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Contact {
private static final String  ROW="id";
private static final String  NAME="name";
private static final String  PHONE="phone";
private static final String  EMAIL="email";
private static final String  ADDRESS="adr";
private static final String  NOTE="note";
private static final String  CITY="city";

private static final int  VERSION=1;
private static final String  DATABASE="contact.db";//ducat
private static final String  TABLE="address";//javadata

private static final String  CREATE="create table address (id integer , name varchar(20)," +
		"phone varchar(20),email varchar(20),adr varchar(150),note varchar(200),city varchar(200));";

Helper h;
SQLiteDatabase sd;
private String whereClause;
public Contact(Context context){
	h=new Helper(context);
}


public void openCon(){
	sd=h.getWritableDatabase();
}
public void closeCon(){
	sd.close();
}
public long insert(long id, String name,String phone,String email,String adr,String note){
	ContentValues c=new ContentValues();
	c.put(ROW,id);
	c.put(NAME,name);
	c.put(PHONE,phone);
	c.put(EMAIL,email);
	c.put(ADDRESS,adr);
	c.put(NOTE,note);
	return sd.insert(TABLE, null, c);
}
/*String name,String phone,String email,String adr,String note NAME="name";
private static final String  PHONE="phone";
private static final String  EMAIL="email";
private static final String  ADDRESS="adr";
private static final String  NOTE="note";
*/
/*public boolean update(long id,String name,String phone,String email,String adr,String note ){
	ContentValues c=new ContentValues();
	c.put(ROW,id);
	c.put(NAME,name);
	c.put(PHONE,phone);
	c.put(EMAIL,email);
	c.put(ADDRESS,adr);
	c.put(NOTE,note);
//	return sd.update(TABLE, c, ROW + "=" + id, null);
	return sd.update(TABLE, c, "id=?", null) > 0;*/

public long deleted(){
	return sd.delete(TABLE, "id not in (select max(id) from address group by name,phone,email,adr,note)", null);
	
}
/*ROW="id";
private static final String  NAME="name";
private static final String  PHONE="phone";
private static final String  EMAIL="email";
private static final String  ADDRESS="adr";
private static final String  NOTE="note";*/

	public int updatecc(long k, String name, String phone, String email, String adr, String note) 
    {
		 Log.e("****************", "hello" + k);
    ContentValues c=new ContentValues();
	c.put(NAME,name);
	c.put(PHONE,phone);
	c.put(EMAIL,email);
	c.put(ADDRESS,adr);
	c.put(NOTE,note);
	return sd.update(TABLE, c, ROW + "=" + k , null) ;
    }
public boolean deleteCon(long k){
	return sd.delete(TABLE, ROW + "=" + k, null) > 0;
	//return sd.delete(TABLE, "name = ? ",new String[]{name})>0 ;
	//sd.delete(TABLE, NAME + "=" + n,PHONE + "=" + p,EMAIL + "=" + e, ADDRESS + "=" + a, NOTE + "=" + not, null)>0 
	
}/*
public long delete(String email){
	return sd.delete(TABLE, "email=? ", new String[]{email});
	
}*/
public Cursor getAllContacts() 
{
	return sd.query(TABLE,new String[]{ROW,NAME,PHONE,EMAIL,ADDRESS,NOTE}, null, null, null, null, "name asc", null);	
}
private class Helper extends SQLiteOpenHelper
{
	public Helper(Context context) {
		super(context, DATABASE, null, VERSION);
	}	@Override
	public void onCreate(SQLiteDatabase db) {
	db.execSQL(CREATE);
	 }
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//do some stubs for upgrading..
	}
  }
}