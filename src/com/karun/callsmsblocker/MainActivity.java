package com.karun.callsmsblocker;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class MainActivity extends FragmentActivity{
	ViewPager viewpager;
	TabPagerAdapter tabadapter;
	ActionBar actionbar;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabadapter=new TabPagerAdapter(getSupportFragmentManager());
		viewpager=(ViewPager)findViewById(R.id.pager);
		viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			
			public void onPageSelected(int arg0) {
				actionbar=getActionBar();
				actionbar.setSelectedNavigationItem(arg0);
				
			}
			});
		viewpager.setAdapter(tabadapter);
		 actionbar=getActionBar();
		 actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		 ActionBar.TabListener tablistener= new ActionBar.TabListener() {
			
			
			public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
				
				
			}
			
			
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				viewpager.setCurrentItem(tab.getPosition());
				
			}
			
			
			public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
				
				
			}
		};
		actionbar.addTab(actionbar.newTab().setText("Call blocker").setTabListener(tablistener));
		actionbar.addTab(actionbar.newTab().setText("SMS blocker").setTabListener(tablistener));
	}
}
	

