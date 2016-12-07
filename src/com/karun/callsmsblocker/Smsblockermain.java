package com.karun.callsmsblocker;


import com.karun.callsmsblocker.Databasesms.Numberbase;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Smsblockermain extends Fragment{
	
	EditText tnumber;
	Button taddnumber;
	Button tviewnumber;
	Databasesms newdatabase;
	Numberbase newnumberbase;
	String checknumber;
	Context context;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
			
		View smsblocker= inflater.inflate(R.layout.smsblocker_frag, container, false);
		taddnumber=(Button)smsblocker.findViewById(R.id.taddnumber);
		tviewnumber=(Button)smsblocker.findViewById(R.id.tview);
        tnumber=(EditText)smsblocker.findViewById(R.id.tnumber);
        
    		newdatabase=new Databasesms(getActivity().getApplicationContext());
            taddnumber.setOnClickListener(new View.OnClickListener() {
 	
			public void onClick(View v) {
		        
					try{		
				String phonenumber=tnumber.getText().toString().trim();
			    if(phonenumber.length()==0){
						Toast.makeText(getActivity().getApplicationContext(),"Please enter the Number",Toast.LENGTH_SHORT).show();
						InputMethodManager ipmanager=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
						ipmanager.hideSoftInputFromWindow(tnumber.getWindowToken(),0);

					}
					else{
							checknumber=newdatabase.checkpresentnumber(phonenumber);
							if(checknumber.length()>0)
				    		{
				    		Toast.makeText(getActivity().getApplicationContext(),"The number is already exist in BlockList",Toast.LENGTH_SHORT).show();
				    		InputMethodManager ipmanager=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
							ipmanager.hideSoftInputFromWindow(tnumber.getWindowToken(),0);
				    		}				
				    		else{
				    			if(phonenumber.length()==10)
				    			phonenumber="+91"+phonenumber;
				    			long data=newdatabase.insertnumber(phonenumber);
				if(data>0){
					Toast.makeText(getActivity().getApplicationContext(),"Number is added to BlockList",Toast.LENGTH_SHORT).show();
					tnumber.setText("");
					InputMethodManager ipmanager=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					ipmanager.hideSoftInputFromWindow(tnumber.getWindowToken(),0);
				}
				else{
					Toast.makeText(getActivity().getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
				}
				   }
				    		}
					
					}
			    catch(Exception e){
			    	Toast.makeText(getActivity().getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
			         }
					}
		});
		tviewnumber.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
					try{
				Intent openintent=new Intent("com.karun.callsmsblocker.CURSORADAPTERVIEWSMS");
				startActivity(openintent);
					}
					catch(Exception e){
						Toast.makeText(getActivity().getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();	
					}
				
			}
		});
		return smsblocker;
		
	}
	
	}
	

	
