package com.example.mytaobao.activity;

import java.util.List;

import com.example.mytaobao.R;
import com.example.mytaobao.biz.Init;
import com.example.mytaobao.biz.ProductManager;
import com.example.mytaobao.model.Product;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ListView;

public class ShoppingCartFragment extends ListFragment {
	private ListView listView;
	private ShoppingCartAdapter adapter;
	private List<Product>products;
	private ProductManager manager;
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		listView=getListView();
		manager=Init.getProductManager();
		products=manager.getShopProducts();
		adapter=new ShoppingCartAdapter(getActivity(), R.layout.product_item, products);
		//listView.setAdapter(adapter);
		setListAdapter(adapter);//注意这里得用这个而不能用上面那个，用上面那个是错的
	}
	
	

}
