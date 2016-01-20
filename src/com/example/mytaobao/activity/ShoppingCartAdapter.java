package com.example.mytaobao.activity;

import java.util.List;

import com.example.mytaobao.R;
import com.example.mytaobao.model.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingCartAdapter extends ArrayAdapter<Product> {
	private int viewResource;
	private Context context;
	private List<Product>products;
	public ShoppingCartAdapter(Context context, int resource,
			 List<Product> objects) {
		super(context, resource, objects);
		viewResource=resource;
		this.context=context;
		this.products=objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		ViewHolder viewHolder=null;
		if(convertView==null){
			LayoutInflater inflater=LayoutInflater.from(context);
			view=inflater.inflate(viewResource, null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.product_image_item);
			TextView productName = (TextView) view
					.findViewById(R.id.product_name_item);
			TextView productPrice = (TextView) view
					.findViewById(R.id.product_price_item);
			viewHolder=new ViewHolder();
			viewHolder.setImageView(imageView);
			viewHolder.setProductName(productName);
			viewHolder.setProductPrice(productPrice);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		if(viewHolder!=null){
			viewHolder.getImageView().setImageResource(
					products.get(position).getPictrue());
			viewHolder.getProductName().setText(
					products.get(position).getName());
			viewHolder.getProductPrice().setText(
					products.get(position).getPrice() + "");
		}
		return view;
	}
	

}
