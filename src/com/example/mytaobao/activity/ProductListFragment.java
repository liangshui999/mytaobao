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

//ListActivity�����Դ�һ��listview��������ȥ�Լ���listview��xml�ļ�
public class ProductListFragment extends ListFragment {
	private int menuFlag;// ����һ����ǣ���ͬ��ֵ��Ӧ�Ų�ͬ��menuѡ��
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
	
	//������Ƭ�϶��ᾭ��onSaveInstanceState(Bundle outState)����Ϊ����tablistener�е�onTabSelected����������onAttach����
	//�� onTabUnselected�����������ȴ������detach�����������������Ҫ��onSaveInstanceState��������������״̬
	////�����滹�漰������bundle�����ݶ����ڵ�һ�д�����������н��ܣ����봫��productAdapter���󣬻���IsSelected���map����
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
		listView = getListView();// �õ�listactivity�Դ���listview
		MyLog.d("ProductListFragment", listView + "");
		context = getActivity();
		productAdapter = new ProductAdapter(context, products);
		// listView.setAdapter(productAdapter);
		setListAdapter(productAdapter);// ע�������������������������Ǹ����������Ǹ��Ǵ��
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
						if (menuFlag == 1) {// ˵���Ǳ༭״̬
							org = productAdapter.getIsSelected();// ��ȡ�ø�ѡ��ԭ����ѡ��״̬,Ҳ����ԭ�������д��ڵ�����
							HashMap<Integer, Boolean> add = new HashMap<Integer, Boolean>();
							for (int i = org.size(); i < products.size(); i++) {
								add.put(i, false);
							}
							org.putAll(add);
							productAdapter.setIsSelected(org);// �ȰѼ�¼��ѡ��״̬�ļ��ϱ༭��֮���ڴ���adapter
							// productAdapter.setProducts(products);
							// productAdapter.unallSeleted();
							productAdapter.notifyDataSetChanged();
						} else {
							productAdapter.notifyDataSetChanged();
						}
						// productAdapter.notifyDataSetChanged();
					} else {
						Toast.makeText(context, "�Ѿ������һ����", Toast.LENGTH_LONG)
								.show();
					}
					break;
				// case -1:
				// Toast.makeText(ProductListActivity.this, "���",
				// Toast.LENGTH_LONG).show();
				// break;

				default:
					if (menuFlag == 1) {
						ViewHolder viewHolder = (ViewHolder) view.getTag();
						CheckBox checkBox = viewHolder.getCheckBox();
						checkBox.toggle();
						org = productAdapter.getIsSelected();// ��ȡ�ø�ѡ��ԭ����ѡ��״̬,Ҳ����ԭ�������д��ڵ�����
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
		//menuFlag = 0;// �л��������Ƭ��ʱ����Ҫ��menuflag����Ϊ0
		menu.clear();

	}

	// ��̬�ı�˵�����getActivity().invalidateOptionsMenu()����������õ�ʱ��ϵͳ�ͻ����onPrepareOptionsMenu��������;
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
							Toast.makeText(context, "��չ", Toast.LENGTH_SHORT)
									.show();
							return true;// ����һ��Ҫreturn
										// true����Ȼ�Ļ���actionview������չ����
						}
					});
		} else {
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.edit_menu, menu);
		}
		this.menu = menu;

	}

	// actionbar����˵��ĵ���¼��Ĵ�����
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.edit:			
			productAdapter=new ProductAdapter(context,products, R.layout.product_item_edit);
			this.setListAdapter(productAdapter);//���ֱ�ӾͰ�onActivityCreated�����adapter����						
			//����actionbar�ϵ�menu
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
			return false;//����Ĭ��Ӧ��Ϊfalse�����򵼺�����û��Ч��
		}		
	}

	// listview������Ŀ�ĵ���¼��ķ���
	// @Override
	// // public void onListItemClick(ListView l, View v, int position, long id)
	// {
	// // // TODO Auto-generated method stub
	// // //���˱༭��ť֮���״̬
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
