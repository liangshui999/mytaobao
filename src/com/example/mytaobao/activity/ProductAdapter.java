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
 * ArrayAdapter��resource�޶����ˣ�������� ��һ����ȥ���ֲ��ܴ���������캯�������ģ���ֻ����ô�������캯����
 * BaseAdapter��Ȼ�����ǲ����÷��ͣ���������ͬʱ���ݼ���resource��Դ��ȥ
 * */
public class ProductAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	private ViewHolder viewHolder;
	private List<Product> products;
	private ProductManager productManager;
	private int productItemSource;
	private HashMap<Integer, Boolean>isSeleted;//�����洢ÿһ��λ�õĸ�ѡ���ѡ��״̬��
	//��ʼ����ѡ��ѡ�����,ȫ������Ϊδѡ��״̬
	public void unallSeleted(){
		for(int i=0;i<products.size();i++){
			isSeleted.put(i, false);
		}
	}
	//����ѡ��ȫ������Ϊѡ��״̬
	public void allSeleted(){
		for(int i=0;i<products.size();i++){
			isSeleted.put(i, true);
		}
	}
	//Ĭ�������ʹ��������췽��
	public ProductAdapter(Context context, List<Product> products) {
		this.context = context;
		this.products = products;
		productItemSource=R.layout.product_item;
		productManager=Init.getProductManager();		
	}
	//���˱༭��ť֮��ʹ��������췽�����½�һ��������
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
	

	//�����������󶨵ļ�����������Ӽ���
	public Boolean addAll(List<Product>productss){
		return products.addAll(productss);		
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return products.size() + 1;
	}

	// postiton�Ǵ�0��ʼ����ģ�positionΪ0�ǡ���Ʒ�б��Ӳ��֣�position��getCount()-1�ĵط��ǡ����ࡱ�Ӳ������ڵ�λ��
	// �м��λ������Ʒ�б��Ӳ������ڵ�λ��
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position >= 0 && position < getCount() - 1) {
			return products.get(position);
		} else {
			return null;
		}
	}
	//�������������д����ʱ��Ϊlistview���õ���¼���ʱ������ô�
	//�ڡ����ࡱ�Ǹ���Ŀ�ĵ���¼����棬������positon�����жϣ���������ͬʱ�����쳣
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

	// postiton�Ǵ�0��ʼ����ģ�positionΪ0�ǡ���Ʒ�б��Ӳ��֣�position��getCount()-1�ĵط��ǡ����ࡱ�Ӳ������ڵ�λ��
	// �м��λ������Ʒ�б��Ӳ������ڵ�λ��
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
//		ViewHolder viewHolder = null;
		// ���positionΪ0����ء���Ʒ�б�����Ĳ���
//		if (position == 0) {
//			view = layoutInflater.inflate(R.layout.top_item_layout, null);
//			return view;
//		}
		//���������һ�Ҳ���ǡ����ࡱ
		if (position == getCount() - 1) {
			layoutInflater = LayoutInflater.from(context);
			view = layoutInflater.inflate(R.layout.bottom_item_layout, null);
			
			TextView more=(TextView) view.findViewById(R.id.more_product);			
			return view;
		}
		// ���֮ǰ�Ѿ����ع����ǿյģ���������Ʒ�б����,��Ҫ�����¼��أ�����ֱ������ǰ��view�����Ч��(���ע��view��view��������������ͬ�ĸ���)
		//ע�����if������жϷ�������id���жϣ���������convertview==layoutinflater.layoutInflater.inflate(R.layout.bottom_item_layout, null)
		//���������жϷ�������Ч��
		if (convertView == null
//				|| convertView.findViewById(R.id.add_product)!=null
				|| convertView.findViewById(R.id.more_product)!=null) {
			layoutInflater = LayoutInflater.from(context);//ע��Ҫ�������������ط���view����ִ�У��м��м�
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
			view = convertView;// converview��ľ��ǹ�����Ļ���Ǹ�view��������������Ǹ�item����ֱ��ʹ��convertview�д�����ݣ�����������������һ��view
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

	// ���ڱ���view�и�����ڲ���
	

}
