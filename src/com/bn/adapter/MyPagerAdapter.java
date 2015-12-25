package com.bn.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MyPagerAdapter extends PagerAdapter {
List<View> list = new ArrayList<View>();

public MyPagerAdapter(ArrayList<View> list)
{
	this.list = list;
}
@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
     ViewPager pViewPager = ((ViewPager)container);
     pViewPager.removeView(list.get(position));
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub

	      ViewPager pViewPager = ((ViewPager)container);
	      pViewPager.addView(list.get(position));
	      return list.get(position);
	
	
	}
	@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
	@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
			// TODO Auto-generated method stub
		}
	@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}
	@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return POSITION_NONE;
		}
      @Override
    	public void startUpdate(View container) {
    		// TODO Auto-generated method stub
    	}

}
