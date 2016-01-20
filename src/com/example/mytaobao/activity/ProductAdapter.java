package com.example.mytaobao.activity;

import java.util.HashMap;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.example.mytaobao.R;
import com.example.mytaobao.biz.Init;
import com.example.mytaobao.biz.ProductManager;
import com.example.mytaobao.model.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * ArrayAdapter把resource限定死了，必须给它 传一个进去，又不能传多个（构造函数决定的，他只有那么几个构造函数）
 * BaseAdapter虽然灵活，但是不能用泛型，但是他能同时传递几个resource资源进去
 * */
public class ProductAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	private ViewHolder viewHolder;
	private List<Product> products;
	private ProductManager productManager;
	private int productItemSource;
	private HashMap<Integer, Boolean>isSeleted;//用来存储每一个位置的复选框的选中状态的
	//初始化复选框选中情况,全部设置为未选中状态
	public void unallSeleted(){
		for(int i=0;i<products.size();i++){
			isSeleted.put(i, false);
		}
	}
	//将复选框全部设置为选中状态
	public void allSeleted(){
		for(int i=0;i<products.size();i++){
			isSeleted.put(i, true);
		}
	}
	//默认情况下使用这个构造方法
	public ProductAdapter(Context context, List<Product> products) {
		this.context = context;
		this.products = products;
		productItemSource=R.layout.product_item;
		productManager=Init.getProductManager();		
	}
	//点了编辑按钮之后，使用这个构造方法再新建一个适配器
	public ProductAdapter(Context context, List<Product> products,
			int productItemSource) {
		super();
		this.context = context;
		this.products = products;
		this.productItemSource = productItemSource;
		initIsSelected();
		unallSeleted();
	}
	public void initIsSelected() {
		isSeleted=new HashMap<Integer, Boolean>();
	}
	
	public HashMap<Integer, Boolean> getIsSelected(){
		return isSeleted;
	}
	public void setIsSelected(HashMap<Integer, Boolean>isSelected){
		this.isSeleted=isSelected;
	}
	public void setProducts(List<Product> products){
		this.products=products;
	}
	
	public ViewHolder getViewHolder(){
		return viewHolder;
	}
	

	//往适配器所绑定的集合里面添加子集合
	public Boolean addAll(List<Product>productss){
		return products.addAll(productss);		
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return products.size() + 1;
	}

	// postiton是从0开始算起的，position为0是“商品列表”子布局，position是getCount()-1的地方是“更多”子布局所在的位置
	// 中间的位置是商品列表子布局所在的位置
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position >= 0 && position < getCount() - 1) {
			return products.get(position);
		} else {
			return null;
		}
	}
	//这个方法必须重写，到时候为listview设置点击事件的时候会有用处
	//在“更多”那个项目的点击事件里面，不能用positon进行判断，否则会出现同时操作异常
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
//		if(position==0){
//			return -1;
//		}
		if(position<getCount()-1&&position>=0){
			return position;
		}else{
			return -2;
		}
	}

	// postiton是从0开始算起的，position为0是“商品列表”子布局，position是getCount()-1的地方是“更多”子布局所在的位置
	// 中间的位置是商品列表子布局所在的位置
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
//		ViewHolder viewHolder = null;
		// 如果position为0则加载“商品列表”标题的布局
//		if (position == 0) {
//			view = layoutInflater.inflate(R.layout.top_item_layout, null);
//			return view;
//		}
		//表明是最后一项，也就是“更多”
		if (position == getCount() - 1) {
			layoutInflater = LayoutInflater.from(context);
			view = layoutInflater.inflate(R.layout.bottom_item_layout, null);
			
			TextView more=(TextView) view.findViewById(R.id.more_product);			
			return view;
		}
		// 如果之前已经加载过的是空的，或者是商品列表标题,就要再重新加载，否则直接用以前的view，提高效率(务必注意view和view的内容是两个不同的概念)
		//注意这个if里面的判断方法得用id来判断，而不是用convertview==layoutinflater.layoutInflater.inflate(R.layout.bottom_item_layout, null)
		//后面这种判断方法是无效的
		if (convertView == null
//				|| convertView.findViewById(R.id.add_product)!=null
				|| convertView.findViewById(R.id.more_product)!=null) {
			layoutInflater = LayoutInflater.from(context);//注意要放在这儿，这个地方的view会先执行，切记切记
			view = layoutInflater.inflate(productItemSource, null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.product_image_item);
			TextView productName = (TextView) view
					.findViewById(R.id.product_name_item);
			TextView productPrice = (TextView) view
					.findViewById(R.id.product_price_item);
			CheckBox checkBox=null;			
			viewHolder = new ViewHolder();
			viewHolder.setImageView(imageView);
			viewHolder.setProductName(productName);
			viewHolder.setProductPrice(productPrice);
			if(productItemSource==R.layout.product_item_edit){
				checkBox=(CheckBox) view.findViewById(R.id.product_checkbox);
				viewHolder.setCheckBox(checkBox);
			}
			view.setTag(viewHolder);
		} else {
			view = convertView;// converview存的就是滚出屏幕的那个view，最下面进来的那个item可以直接使用convertview中存的内容，而不用重新再生成一个view
			viewHolder = (ViewHolder) view.getTag();			
			
		}
		if(viewHolder!=null){
			viewHolder.getImageView().setImageResource(
					products.get(position).getPictrue());
			viewHolder.getProductName().setText(
					products.get(position).getName());
			viewHolder.getProductPrice().setText(
					products.get(position).getPrice() + "");
			if(productItemSource==R.layout.product_item_edit){				
				viewHolder.getCheckBox().setChecked(isSeleted.get(position));
			}
		}
		
		return view;
	}

	// 用于保存view中各项的内部类
	

}
