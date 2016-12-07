package com.karun.callsmsblocker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Cursoradapterviewcall extends Activity {
	ListView mylistcall;
	Databasecall mydatabasecall;
	Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calllist);
        mylistcall=(ListView)findViewById(R.id.listViewcall);
        registerForContextMenu(mylistcall);
		mydatabasecall=new Databasecall(this);
		String[] table = {Databasecall.CALLNUMBER};
		int[] list = {R.id.tvnumbercall};
		cursor=mydatabasecall.getallcallnumbers();
		SimpleCursorAdapter callcursoradapter = new SimpleCursorAdapter(this,R.layout.calllisttv,cursor,table,list);
		mylistcall.setAdapter(callcursoradapter);
	}
	   public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuinfo){
		super.onCreateContextMenu(menu, v, menuinfo);
		menu.setHeaderTitle("Select The Action");
		menu.add(0,v.getId(),0,"Edit");
		menu.add(0,v.getId(),0,"Delete");
		menu.add(0,v.getId(),0,"Cancel");
	}
	public boolean onContextItemSelected(MenuItem item){
		if(item.getTitle()=="Edit"){
			final String Selected=cursor.getString(cursor.getColumnIndex("callnumber"));
			AlertDialog.Builder edit=new AlertDialog.Builder(this);
			final EditText edittext=new EditText(this);
			edit.setTitle("Edit");
			edit.setView(edittext);
			edittext.setText(Selected);
			edit.setCancelable(false)
			.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					try{
					String editnumber=edittext.getText().toString().trim();
					mydatabasecall.updatecallnumber(Selected,editnumber);
					cursor.requery();
					}
					catch(Exception e){
						Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
					}
					}
			})
			.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					dialog.cancel();
				}
			});
			edit.show();
		}
		else if(item.getTitle()=="Delete"){
			try{
				AlertDialog.Builder alert=new AlertDialog.Builder(this);
				alert.setTitle("Delete!").setMessage("Are you sure want to delete?").setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String selected=cursor.getString(cursor.getColumnIndex("callnumber"));
						mydatabasecall.deletecallnumber(selected);
						cursor.requery();
						Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
						}
				})
				.setNegativeButton("No",new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						dialog.cancel();
					}
				});
				alert.create().show();
			}
			catch(Exception e){
				Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
			}
		}
		else{
			return false;
		}
		return true;
}
}


