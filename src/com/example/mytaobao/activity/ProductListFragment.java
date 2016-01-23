package com.example.mytaobao.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mytaobao.R;
import com.example.mytaobao.biz.Init;
import com.example.mytaobao.biz.ProductManager;
import com.example.mytaobao.model.Product;
import com.example.mytaobao.util.MyApplication;
import com.example.mytaobao.util.MyLog;

import android.R.integer;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

//ListActivity里面自带一个listview，都不用去自己见listview的xml文件
public class ProductListFragment extends ListFragment {
	private int menuFlag;// 这是一个标记，不同的值对应着不同的menu选项
	private Menu menu;
	private List<Product> products;
	private ListView listView;
	private ProductAdapter productAdapter;
	private ProductManager productManager;
	private int pageIndex;
	private int pageSize;
	private Context context;
	private HashMap<Integer, Boolean> org;

	public int getMenuFlag() {
		return menuFlag;
	}

	public void setMenuFlag(int menuFlag) {
		this.menuFlag = menuFlag;
	}

	public void setProductAdapter(ProductAdapter productAdapter) {
		this.productAdapter = productAdapter;
	}

	public List<Product> getProductsFromProductListFragment() {
		return products;
	}

	public ListView getListViewFromProductListFragment() {
		return listView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		MyLog.d("ProductListFragment", "onCreate");
	}
	
	//这种碎片肯定会经历onSaveInstanceState(Bundle outState)，因为他的tablistener中的onTabSelected（）调用了onAttach（）
	//而 onTabUnselected（）这个方法却调用了detach（）方法。因此我需要用onSaveInstanceState（）来保存他的状态
	////这里面还涉及到了用bundle来传递对象，在第一行代码的书里面有介绍，必须传递productAdapter对象，还有IsSelected这个map集合
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		org=productAdapter.getIsSelected();
		
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		pageIndex = 0;
		pageSize = 5;
		productManager = Init.getProductManager();
		products = productManager.getByPage(pageIndex, pageSize);
		listView = getListView();// 得到listactivity自带的listview
		MyLog.d("ProductListFragment", listView + "");
		context = getActivity();
		productAdapter = new ProductAdapter(context, products);
		// listView.setAdapter(productAdapter);
		setListAdapter(productAdapter);// 注意这里得用这个而不能用上面那个，用上面那个是错的
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch ((int) id) {
				case -2:
					pageIndex++;
					List<Product> moreProduct = productManager.getByPage(
							pageIndex, pageSize);
					if (moreProduct != null) {
						products.addAll(moreProduct);
						if (menuFlag == 1) {// 说明是编辑状态
							org = productAdapter.getIsSelected();// 先取得复选框原来的选择状态,也就是原来集合中存在的数据
							HashMap<Integer, Boolean> add = new HashMap<Integer, Boolean>();
							for (int i = org.size(); i < products.size(); i++) {
								add.put(i, false);
							}
							org.putAll(add);
							productAdapter.setIsSelected(org);// 先把记录复选框状态的集合编辑好之后，在传给adapter
							// productAdapter.setProducts(products);
							// productAdapter.unallSeleted();
							productAdapter.notifyDataSetChanged();
						} else {
							productAdapter.notifyDataSetChanged();
						}
						// productAdapter.notifyDataSetChanged();
					} else {
						Toast.makeText(context, "已经是最后一项了", Toast.LENGTH_LONG)
								.show();
					}
					break;
				// case -1:
				// Toast.makeText(ProductListActivity.this, "添加",
				// Toast.LENGTH_LONG).show();
				// break;

				default:
					if (menuFlag == 1) {
						ViewHolder viewHolder = (ViewHolder) view.getTag();
						CheckBox checkBox = viewHolder.getCheckBox();
						checkBox.toggle();
						org = productAdapter.getIsSelected();// 先取得复选框原来的选择状态,也就是原来集合中存在的数据
						org.put(position, checkBox.isChecked());
						productAdapter.setIsSelected(org);
						productAdapter.notifyDataSetChanged();
					}
					break;
				}
			}
		});
	}

	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getActivity().invalidateOptionsMenu();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//menuFlag = 0;// 切换到别的碎片的时候，需要把menuflag设置为0
		menu.clear();

	}

	// 动态改变菜单，当getActivity().invalidateOptionsMenu()这个方法调用的时候，系统就会调用onPrepareOptionsMenu（）方法;
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();
		if (menuFlag == 0) {
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.no_edit_menu, menu);
			MenuItem searchItem = menu.findItem(R.id.search);
			SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
			SearchView searchView = (SearchView)searchItem.getActionView();
			ComponentName cn = new ComponentName(getActivity(), ProductsSearchActivity.class);
			searchView.setSearchableInfo(searchManager.getSearchableInfo(cn));
			searchView.setIconifiedByDefault(false);
			MenuItemCompat.setOnActionExpandListener(searchItem,
					new OnActionExpandListener() {

						@Override
						public boolean onMenuItemActionCollapse(MenuItem arg0) {
							// TODO Auto-generated method stub
							return true;
						}

						@Override
						public boolean onMenuItemActionExpand(MenuItem arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(context, "伸展", Toast.LENGTH_SHORT)
									.show();
							return true;// 这里一定要return
										// true，不然的话，actionview不会伸展开来
						}
					});
		} else {
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.edit_menu, menu);
		}
		this.menu = menu;

	}

	// actionbar上面菜单的点击事件的处理方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.edit:			
			productAdapter=new ProductAdapter(context,products, R.layout.product_item_edit);
			this.setListAdapter(productAdapter);//这个直接就把onActivityCreated里面的adapter换了						
			//更改actionbar上的menu
			menuFlag=1;
			getActivity().invalidateOptionsMenu();			
			return true;
		case R.id.sort:
			Toast.makeText(context, "sort", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.all_select:
			productAdapter.allSeleted();
			productAdapter.notifyDataSetChanged();
			//Toast.makeText(context, "all_select", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.all_unselect:
			productAdapter.unallSeleted();
			productAdapter.notifyDataSetChanged();
			//Toast.makeText(context, "all_unselect", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.add:
			Toast.makeText(context, "add", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.delete:			
			org=productAdapter.getIsSelected();
			List<Product>temp=new ArrayList<Product>();
			for(Map.Entry<Integer,Boolean>e:org.entrySet()){
				if(e.getValue()){
					//products.remove(e.getKey());
					temp.add(products.get(e.getKey()));
					MyLog.d("ProductListFragment", ""+e.getKey());
					MyLog.d("ProductListFragment", ""+e.getValue());
				}				
			}
			products.removeAll(temp);
			MyLog.d("ProductListFragment", products.size()+"");
			productAdapter=new ProductAdapter(context, products);
			setListAdapter(productAdapter);
			menuFlag=0;
			getActivity().invalidateOptionsMenu();
			return true;
		case R.id.search:
			return false;
		default:
			return false;//这里默认应该为false，否则导航好像没有效果
		}		
	}

	// listview里面项目的点击事件的方法
	// @Override
	// // public void onListItemClick(ListView l, View v, int position, long id)
	// {
	// // // TODO Auto-generated method stub
	// // //点了编辑按钮之后的状态
	// //// if(menuFlag==1){
	// //// ViewHolder viewHolder=productAdapter.getViewHolder();
	// //// CheckBox checkBox=viewHolder.getCheckBox();
	// //// checkBox.toggle();
	// //// org.put(position, checkBox.isChecked());
	// //// productAdapter.setIsSelected(org);
	// //// productAdapter.notifyDataSetChanged();
	// //// }
	// // }
	

}
