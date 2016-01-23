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
	private List<Product>products;//��queryProducts���з�ҳ�Ľ��
	List<Product>queryProducts;//ģ����ѯ֮��Ľ��
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
		//�ж������������ͼ�ķ�ʽ���������ʽ��ͼ��˵��ȷʵ��Ҫsearch����Ϊһ������Ա��ܶ���
		//��ʽ������
		if(Intent.ACTION_SEARCH.equals(intent.getAction())){
			String query=intent.getStringExtra(SearchManager.QUERY);
			queryProducts=searchByName(query);
		}
		//��products���з�ҳ
		products=manager.getByPage(pageIndex, pageSize, queryProducts);
		//�������ݲ���Ϊ��
		if(products!=null){
			final ProductAdapter productAdapter=new ProductAdapter(this, products);
			setListAdapter(productAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					//˵��������ǡ����ࡱ
					if(position==productAdapter.getCount()-1){
						pageIndex++;
						List<Product>tempProducts=manager.getByPage(pageIndex, pageSize, queryProducts);
						if(tempProducts!=null){
							products.addAll(tempProducts);
							productAdapter.notifyDataSetChanged();
						}else{
							Toast.makeText(ProductsSearchActivity.this, "������ˣ�û����", Toast.LENGTH_SHORT).show();
						}
						
					}
				}
			});
		}
		
	}
	
	//�������ƽ���ģ����ѯ��������ѯ�Ľ������
	private List<Product> searchByName(String name) {
		// TODO Auto-generated method stub
		return manager.getByName(name);
	}
	

}
