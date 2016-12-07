package com.karun.callsmsblocker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter{
	Context context;
	
	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}	
	public Fragment getItem(int arg0) {
		
		switch(arg0)
		{
		    case 0:
			return new Callblockermain();
		    case 1:
			return new Smsblockermain();
		}
		return null;
	}

	
	public int getCount() {
		
		return 2;
	}

}
