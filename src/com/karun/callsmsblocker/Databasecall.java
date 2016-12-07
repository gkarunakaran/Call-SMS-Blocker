package com.karun.callsmsblocker;

	import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

	public class Databasecall{
		private static final String DATABASE_NAME="mydatacall";
		private static final String TABLE_NAME="mytablecall";
		static final String CALLNUMBER="callnumber";
		static final String ID="_id";
		private static final int DATABASE_VERSION=1;
		Numberbasecall mydata;
		SQLiteDatabase db;
		long data;
		Context context;
		public Databasecall(Context context){
			mydata=new Numberbasecall(context);
			db=mydata.getWritableDatabase();
			}
		
	public class Numberbasecall extends SQLiteOpenHelper {
	private Context context;
	public Numberbasecall(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		this.context=context;
	}
		
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CALLNUMBER+" VARCHAR);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS"+" "+TABLE_NAME);
			onCreate(db);
		}

	}public long insertcallnumber(String callnumber) {
		db=mydata.getWritableDatabase();
		ContentValues contentvalues=new ContentValues();
		contentvalues.put(CALLNUMBER,callnumber);
		data=db.insert(TABLE_NAME,null,contentvalues);
		return data;
	}
	public String checkcallpresentnumber(String callnumber) {
		db=mydata.getWritableDatabase();
		String[] columns={Databasecall.ID};
		String[] selectionArgs={callnumber};
		Cursor cursor=db.query(Databasecall.TABLE_NAME, columns,Databasecall.CALLNUMBER +"=?",selectionArgs,null, null, null,null);
		StringBuffer buffer=new StringBuffer();
		while(cursor.moveToNext())
		{
			int index0=cursor.getColumnIndex(Databasecall.ID);
			int checknumber=cursor.getInt(index0);
			buffer.append(checknumber);
		}
		return buffer.toString();
	}
	public Cursor getallcallnumbers(){
		String[] columns={Databasecall.ID,Databasecall.CALLNUMBER};
		db=mydata.getWritableDatabase();
		Cursor mycursor=db.query(TABLE_NAME,columns, null, null, null, null, null);
		return mycursor;
	}
	public void updatecallnumber(String selected, String editnumber) {
		db=mydata.getWritableDatabase();
		ContentValues contentvalues=new ContentValues();
		contentvalues.put(Databasecall.CALLNUMBER,editnumber);
		String whereArgs[]={selected};
		db.update(Databasecall.TABLE_NAME,contentvalues,Databasecall.CALLNUMBER +"=?",whereArgs);
		
	}
	public void deletecallnumber(String selected) {
		try{
			db=mydata.getWritableDatabase();
			String newselected[]={selected};
			db.delete(TABLE_NAME,CALLNUMBER +"=?",newselected);
			}
			catch(Exception e){
				Toast.makeText(context,""+e,Toast.LENGTH_SHORT).show();
			}
		
	}
	public String checkcallnumber(String incomingnumber) {
		db=mydata.getWritableDatabase();
		String[] columns={Databasecall.ID};
		String selectionArgs[]={incomingnumber};
		Cursor cursor=db.query(Databasecall.TABLE_NAME, columns,Databasecall.CALLNUMBER +"=?",selectionArgs,null, null, null,null);
		StringBuffer buffer=new StringBuffer();
		while(cursor.moveToNext())
		{
			int index0=cursor.getColumnIndex(Databasesms.ID);
			int mynumber=cursor.getInt(index0);
			buffer.append(mynumber);
		}
		return buffer.toString();
	}
	}


