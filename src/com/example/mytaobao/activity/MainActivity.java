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
//����unsqlite��֧�����ע�Ͳ��ϲ�������֧��1.20 17.47
public class MainActivity extends Activity {
	private int menuFlag;//����һ����ǣ���ͬ��ֵ��Ӧ�Ų�ͬ��menuѡ��
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
				.setText("��Ʒ�б�")
				.setTabListener(
						new TabListener<ProductListFragment>(this,
								"ProductListFragment", ProductListFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setText("���ﳵ")
				.setTabListener(
						new TabListener<ShoppingCartFragment>(this,
								"ShoppingCartFragment", ShoppingCartFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setText("�ҵ�")
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
//						Toast.makeText(MainActivity.this, "��չ",
//								Toast.LENGTH_SHORT).show();
//						return true;//����һ��Ҫreturn true����Ȼ�Ļ���actionview������չ����
//					}
//				});
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case R.id.edit:
//			//��Ҫ�õ�productlistfragment,Ȼ�����������
//			productListFragment=(ProductListFragment) getFragmentManager().findFragmentByTag("ProductListFragment");
//			products=productListFragment.getProductsFromProductListFragment();
//			ProductAdapter productAdapter=new ProductAdapter(this,products, R.layout.product_item_edit);
//			productListFragment.setListAdapter(productAdapter);//���ֱ�ӾͰ�onActivityCreated�����adapter����
//			productListFragment.setProductAdapter(productAdapter);//����൱�ڰ��������¼������adapter����
//			
//			//����actionbar�ϵ�menu
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
		return false;//����Ĭ��Ӧ��Ϊfalse�����򵼺�����û��Ч��
//		}		
	}
	//�ܹ���̬����menu�ķ���

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

	//������ؼ����¼�����
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//menuflag==1˵���ǵ���˱༭֮������
		productListFragment=(ProductListFragment) getFragmentManager().findFragmentByTag("ProductListFragment");
		menuFlag=productListFragment.getMenuFlag();
		if(menuFlag==1){
			initInterface(); 
		}else{
			super.onBackPressed();
		}
		
	}
	//�ص��༭֮ǰ��״̬
	private void initInterface() {
		products=productListFragment.getProductsFromProductListFragment();
		ProductAdapter productAdapter=new ProductAdapter(this,products, R.layout.product_item);		
		productListFragment.setListAdapter(productAdapter);//���ֱ�ӾͰ�onActivityCreated�����adapter����
		productListFragment.setProductAdapter(productAdapter);//����൱�ڰ��������¼������adapter����
		menuFlag=0;
		productListFragment.setMenuFlag(menuFlag);
		invalidateOptionsMenu();
	}
	
	
	
	
	
	
	

}
