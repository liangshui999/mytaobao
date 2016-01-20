package com.example.mytaobao.activity;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.FragmentTransaction;

public class TabListener<T extends Fragment> implements ActionBar.TabListener {
	private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    
	public TabListener(Activity mActivity, String mTag, Class<T> mClass) {
		super();
		this.mActivity = mActivity;
		this.mTag = mTag;
		this.mClass = mClass;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				// Check if the fragment is already initialized
		        if (mFragment == null) {
		            // If not, instantiate and add it to the activity
		            mFragment = Fragment.instantiate(mActivity, mClass.getName());
		            ft.add(android.R.id.content, mFragment, mTag);
		        } else {
		            // If it exists, simply attach it in order to show it
		            ft.show(mFragment);//选择的时候，让之前隐藏的显示出来
		        }

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 if (mFragment != null) {
			            // Detach the fragment, because another one is being attached
			            ft.hide(mFragment);//不选择的时候，把碎片隐藏起来
			        }

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


}
