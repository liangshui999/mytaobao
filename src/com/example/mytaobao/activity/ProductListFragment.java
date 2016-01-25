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
import android.app.Fragment;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

//ListActivity里面自带一个listview，都不用去自己见listview的xml文件
public class ProductListFragment extends Fragment {
	private int menuFlag;// 这是一个标记，不同的值对应着不同的menu选项：0是初始状态；1表示编辑状态；2表示网格视图状态
	private static final int ORGIN_STATE = 0;
	private static final int EDIT_STATE = 1;
	private static final int GRID_STATE = 2;
	private Menu menu;
	private List<Product> products;
	private ListView listView;
	private ProductAdapter productAdapter;
	private ProductManager productManager;
	private int pageIndex;
	private int pageSize;
	private Context context;
	private HashMap<Integer, Boolean> org;
	private GridView gridView;
	private LinearLayout linearLayout;
//	private SimpleAdapter simpleAdapter;

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
	public GridView getGridView(){
		return gridView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		MyLog.d("ProductListFragment", "onCreate");
	}

	// 这种碎片肯定会经历onSaveInstanceState(Bundle
	// outState)，因为他的tablistener中的onTabSelected（）调用了onAttach（）
	// 而 onTabUnselected（）这个方法却调用了detach（）方法。因此我需要用onSaveInstanceState（）来保存他的状态
	// //这里面还涉及到了用bundle来传递对象，在第一行代码的书里面有介绍，必须传递productAdapter对象，还有IsSelected这个map集合
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		org = productAdapter.getIsSelected();

	}
	
	//自定义fragment的布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.product_listfragment, null);
		listView=(ListView) view.findViewById(R.id.listview);
		gridView=(GridView) view.findViewById(R.id.gridview);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		pageIndex = 0;
		pageSize = 5;
		productManager = Init.getProductManager();
		products = productManager.getByPage(pageIndex, pageSize);
//		listView = getListView();// 得到listactivity自带的listview
		MyLog.d("ProductListFragment", listView + "");
		context = getActivity();
		productAdapter = new ProductAdapter(context, products);
		listView.setAdapter(productAdapter);
//		setListAdapter(productAdapter);// 注意这里得用这个而不能用上面那个，用上面那个是错的
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
					if (menuFlag == EDIT_STATE) {
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
		// getActivity().invalidateOptionsMenu();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// menuFlag = 0;// 切换到别的碎片的时候，需要把menuflag设置为0
		// menu.clear();

	}

	// 动态改变菜单，当getActivity().invalidateOptionsMenu()这个方法调用的时候，系统就会调用onPrepareOptionsMenu（）方法;
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();
		if (menuFlag == ORGIN_STATE) {
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.no_edit_menu, menu);
			MenuItem searchItem = menu.findItem(R.id.search);
			SearchManager searchManager = (SearchManager) getActivity()
					.getSystemService(Context.SEARCH_SERVICE);
			SearchView searchView = (SearchView) searchItem.getActionView();
			ComponentName cn = new ComponentName("com.example.mytaobao",
					"com.example.mytaobao.activity.ProductsSearchActivity");// 直接指定搜索组件的明证
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
		} else if (menuFlag == EDIT_STATE) {
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.edit_menu, menu);
		} else {
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.grid_menu_swap, menu);
			
		}
		this.menu = menu;

	}

	// actionbar上面菜单的点击事件的处理方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.edit:
			productAdapter = new ProductAdapter(context, products,
					R.layout.product_item_edit);
			listView.setAdapter(productAdapter);// 这个直接就把onActivityCreated里面的adapter换了
			// 更改actionbar上的menu
			menuFlag = EDIT_STATE;
			getActivity().invalidateOptionsMenu();
			return true;
		case R.id.sort:
			Toast.makeText(context, "sort", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.all_select:
			productAdapter.allSeleted();
			productAdapter.notifyDataSetChanged();
			// Toast.makeText(context, "all_select", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.all_unselect:
			productAdapter.unallSeleted();
			productAdapter.notifyDataSetChanged();
			// Toast.makeText(context, "all_unselect",
			// Toast.LENGTH_SHORT).show();
			return true;
		case R.id.add:
			Toast.makeText(context, "add", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.delete:
			org = productAdapter.getIsSelected();
			List<Product> temp = new ArrayList<Product>();
			for (Map.Entry<Integer, Boolean> e : org.entrySet()) {
				if (e.getValue()) {
					// products.remove(e.getKey());
					temp.add(products.get(e.getKey()));
					MyLog.d("ProductListFragment", "" + e.getKey());
					MyLog.d("ProductListFragment", "" + e.getValue());
				}
			}
			products.removeAll(temp);
			MyLog.d("ProductListFragment", products.size() + "");
			productAdapter = new ProductAdapter(context, products);
			listView.setAdapter(productAdapter);
			menuFlag = ORGIN_STATE;
			getActivity().invalidateOptionsMenu();
			return true;
		case R.id.search:
			return false;
		case R.id.grid:
			Toast.makeText(context, "网格视图", Toast.LENGTH_SHORT).show();
			listView.setVisibility(View.GONE);					
			gridView.setVisibility(View.VISIBLE);
			SimpleAdapter simpleAdapter=getSimpleAdapter(R.layout.grid_item);
			MyLog.d("ProductListFragment", simpleAdapter.toString());
			gridView.setAdapter(simpleAdapter);			
			menuFlag = GRID_STATE;
			getActivity().invalidateOptionsMenu();// 动态切换menu			
			//为gridview设置contextual menu
			setGridviewContextualMenu();
			return true;
		case R.id.list:
			gridView.setVisibility(View.GONE);
			listView.setVisibility(View.VISIBLE);
			Toast.makeText(context, "列表视图", Toast.LENGTH_SHORT).show();
			menuFlag = ORGIN_STATE;
			getActivity().invalidateOptionsMenu();// 动态切换menu
			return true;
		default:
			return false;// 这里默认应该为false，否则导航好像没有效果
		}
	}
	
	//为gridview设置contextual menu
	public void setGridviewContextualMenu() {
		gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		gridView.setMultiChoiceModeListener(new MultiChoiceModeListener(){
			//长按的时候会生成的视图
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub				
				MenuInflater inflater = mode.getMenuInflater();			
		        inflater.inflate(R.menu.grid_menu, menu);
		        SimpleAdapter simpleAdapter=getSimpleAdapter(R.layout.grid_item_long_click);
		        gridView.setAdapter(simpleAdapter);
		        return true;

			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}
			//设置每个菜单项点击之后的事件
			@Override
			public boolean onActionItemClicked(ActionMode mode,
					MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.list:						
					break;

				default:
					break;
				}
				return false;
			}
			//用户做出选择之后，contexmenu就会消失，这个时候，需要把视图切换回去
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				initGridview();
			}
			//让gridview回到初始视图，不论用户做完了什么选择，都应该回到初始视图
			public void initGridview() {
				SimpleAdapter simpleAdapter=getSimpleAdapter(R.layout.grid_item);
		        gridView.setAdapter(simpleAdapter);
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	// 获取简单适配器
	public SimpleAdapter getSimpleAdapter(int resource) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		List<Product> gridProducts = productManager.getAllProducts();// 这个就不分页了，获取所有商品
		for (int i = 0; i < gridProducts.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", gridProducts.get(i).getPictrue());
			map.put("name", gridProducts.get(i).getName());
			list.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(context, list,
				resource, new String[] { "image", "name" },
				new int[] { R.id.image, R.id.text });		
		return simpleAdapter;
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
