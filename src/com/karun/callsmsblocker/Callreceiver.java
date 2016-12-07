package com.karun.callsmsblocker;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Callreceiver extends BroadcastReceiver {
Databasecall databasecall;
Context context;
	
	public void onReceive(Context context, Intent intent) {
		String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		if(TelephonyManager.EXTRA_STATE_RINGING.equals(state)){
			String incomingnumber=intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			databasecall=new Databasecall(context);
		    String	numberid=databasecall.checkcallnumber(incomingnumber);
		    if(numberid.length()>0){
		    	ITelephony it;
		    	TelephonyManager tm=(TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
		    	try{
		    	AudioManager am=(AudioManager)context.getSystemService(context.AUDIO_SERVICE);
		        Class classs=Class.forName(tm.getClass().getName());
		        Method m=classs.getDeclaredMethod("getITelephony");
		        m.setAccessible(true);
		        it=(ITelephony)m.invoke(tm);
		        Bundle bundle=intent.getExtras();
		        int mode=am.getRingerMode();
		        switch(mode){
		        case AudioManager.RINGER_MODE_NORMAL:
		        	 am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				        it.endCall();
				        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				        break;
		        case AudioManager.RINGER_MODE_SILENT:
		        	it.endCall();
		        	break;
		        case AudioManager.RINGER_MODE_VIBRATE:
		        	am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			        it.endCall();
			        am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			        break;
		        }
		        
		    	}catch
		    	(Exception e)
		    	{
		    		Toast.makeText(context,""+e,Toast.LENGTH_SHORT).show();
		    	}
		    }
		}
	}
}
