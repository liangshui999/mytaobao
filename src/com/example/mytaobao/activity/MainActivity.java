package com.example.mytaobao.activity;

import java.util.List;

import com.example.mytaobao.R;
import com.example.mytaobao.model.Product;
import com.example.mytaobao.util.MyLog;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
//这是unsqlite分支，这个注释不合并到主分支，1.20 17.47
public class MainActivity extends Activity {
	private int menuFlag;//这是一个标记，不同的值对应着不同的menu选项
	private ProductListFragment productListFragment;
	private List<Product>products;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setText("商品列表")
				.setTabListener(
						new TabListener<ProductListFragment>(this,
								"ProductListFragment", ProductListFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setText("购物车")
				.setTabListener(
						new TabListener<ShoppingCartFragment>(this,
								"ShoppingCartFragment", ShoppingCartFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setText("我的")
				.setTabListener(
						new TabListener<UserFragment>(this,
								"UserFragment", UserFragment.class));
		actionBar.addTab(tab);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.no_edit_menu, menu);
//		MenuItem searchItem = menu.findItem(R.id.search);
//		MenuItemCompat.setOnActionExpandListener(searchItem,
//				new OnActionExpandListener() {
//
//					@Override
//					public boolean onMenuItemActionCollapse(MenuItem arg0) {
//						// TODO Auto-generated method stub
//						return true;
//					}
//
//					@Override
//					public boolean onMenuItemActionExpand(MenuItem arg0) {
//						// TODO Auto-generated method stub
//						Toast.makeText(MainActivity.this, "伸展",
//								Toast.LENGTH_SHORT).show();
//						return true;//这里一定要return true，不然的话，actionview不会伸展开来
//					}
//				});
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case R.id.edit:
//			//先要得到productlistfragment,然后更换适配器
//			productListFragment=(ProductListFragment) getFragmentManager().findFragmentByTag("ProductListFragment");
//			products=productListFragment.getProductsFromProductListFragment();
//			ProductAdapter productAdapter=new ProductAdapter(this,products, R.layout.product_item_edit);
//			productListFragment.setListAdapter(productAdapter);//这个直接就把onActivityCreated里面的adapter换了
//			productListFragment.setProductAdapter(productAdapter);//这个相当于把下面点击事件里面的adapter换了
//			
//			//更改actionbar上的menu
//			menuFlag=1;
//			invalidateOptionsMenu(); 
//			
//			return true;
//		case R.id.sort:
//			Toast.makeText(this, "sort", Toast.LENGTH_SHORT).show();
//			return true;
//		case R.id.all_select:
//			Toast.makeText(this, "all_select", Toast.LENGTH_SHORT).show();
//			return true;
//		case R.id.all_unselect:
//			Toast.makeText(this, "all_unselect", Toast.LENGTH_SHORT).show();
//			return true;
//		case R.id.add:
//			Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
//			return true;
//		case R.id.delete:
//			ListView listView=productListFragment.getListViewFromProductListFragment();
//			long[]ids=listView.getCheckedItemIds();
//			MyLog.d("MainActivity", ids+"hahah"+ids.length);
//			for(long id:ids){
//				MyLog.d("MainActivity", ids+"hahah");
//			}
//			//Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
//			return true;
//		case R.id.search:
//			return false;
//		default:
		return false;//这里默认应该为false，否则导航好像没有效果
//		}		
	}
	//能够动态更改menu的方法

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
//		menu.clear();
//		if(menuFlag==0){
//			MenuInflater inflater = getMenuInflater();
//			inflater.inflate(R.menu.no_edit_menu, menu);
//		}else{
//			MenuInflater inflater = getMenuInflater();
//			inflater.inflate(R.menu.edit_menu, menu);
//		}
		
		return false;
	}

	//点击返回键的事件设置
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//menuflag==1说明是点击了编辑之后的情况
		productListFragment=(ProductListFragment) getFragmentManager().findFragmentByTag("ProductListFragment");
		menuFlag=productListFragment.getMenuFlag();
		if(menuFlag==1){
			initInterface(); 
		}else{
			super.onBackPressed();
		}
		
	}
	//回到编辑之前的状态
	private void initInterface() {
		products=productListFragment.getProductsFromProductListFragment();
		ProductAdapter productAdapter=new ProductAdapter(this,products, R.layout.product_item);		
		productListFragment.setListAdapter(productAdapter);//这个直接就把onActivityCreated里面的adapter换了
		productListFragment.setProductAdapter(productAdapter);//这个相当于把下面点击事件里面的adapter换了
		menuFlag=0;
		productListFragment.setMenuFlag(menuFlag);
		invalidateOptionsMenu();
	}
	
	
	
	
	
	
	

}
