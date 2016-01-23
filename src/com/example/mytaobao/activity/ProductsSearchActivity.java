package com.example.mytaobao.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.mytaobao.biz.Init;
import com.example.mytaobao.biz.ProductManager;
import com.example.mytaobao.model.Product;

import android.R.integer;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ProductsSearchActivity extends ListActivity {
	private ProductManager manager;
	private List<Product>products;//对queryProducts进行分页的结果
	List<Product>queryProducts;//模糊查询之后的结果
	private int pageIndex;
	private int pageSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		manager=Init.getProductManager();
		pageIndex=0;
		pageSize=3;
		products=new ArrayList<Product>();
		ListView listView=getListView();
		Intent intent=getIntent();
		//判断启动本活动的意图的方式，如果是隐式意图，说明确实是要search；因为一个活动可以被很多总
		//方式开启，
		if(Intent.ACTION_SEARCH.equals(intent.getAction())){
			String query=intent.getStringExtra(SearchManager.QUERY);
			queryProducts=searchByName(query);
		}
		//对products进行分页
		products=manager.getByPage(pageIndex, pageSize, queryProducts);
		//返回数据不能为空
		if(products!=null){
			final ProductAdapter productAdapter=new ProductAdapter(this, products);
			setListAdapter(productAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					//说明点击的是“更多”
					if(position==productAdapter.getCount()-1){
						pageIndex++;
						List<Product>tempProducts=manager.getByPage(pageIndex, pageSize, queryProducts);
						if(tempProducts!=null){
							products.addAll(tempProducts);
							productAdapter.notifyDataSetChanged();
						}else{
							Toast.makeText(ProductsSearchActivity.this, "到最后了，没有了", Toast.LENGTH_SHORT).show();
						}
						
					}
				}
			});
		}
		
	}
	
	//根据名称进行模糊查询，并将查询的结果返回
	private List<Product> searchByName(String name) {
		// TODO Auto-generated method stub
		return manager.getByName(name);
	}
	

}
