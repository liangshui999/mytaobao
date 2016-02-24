package com.example.mytaobao.daomplMoni;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.mytaobao.dao.IProductService;
import com.example.mytaobao.model.Product;
import com.example.mytaobao.*;

public class ProductService implements IProductService {
	private List<Product> products=new ArrayList<Product>();//��ʼ���������ݷ�������������棬���Ǵ��������������ɾ�Ĳ�
	private List<Product>shoppingProducts;//���ﳵ���������
	//��Ʒ��Ŵ�0��ʼ���ȽϺü���һ��
	public ProductService() {
		products.add(new Product(0,"���� GT-S58", 1, R.drawable.p1,
				1630.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(1,"���� GT-S5830", 1, R.drawable.p1,
				1630.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(2,"HTC A510e", 1, R.drawable.p2,
				1514.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(3,"���� I9100", 1, R.drawable.p3,
				3266.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(4,"���� U880��TD�棩", 1,
				R.drawable.p4, 989.00,
				"��������:TD-SCDMA(3G) ����ϵͳ:ANDROID  "));
		products.add(new Product(5,"Sony Ericsson���ᰮ���� ", 1,
				R.drawable.p5, 2584.00,
				"��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(6,"Ħ������ Defy", 1, R.drawable.p6,
				1851.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(7,"���� V880", 1, R.drawable.p7,
				957.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(8,"HTC S710e", 1, R.drawable.p8,
				2671.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(9,"Ħ������ ME525",1, R.drawable.p9,
				1853.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(10,"G12", 1, R.drawable.p10,
				2470.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(11,"Ħ������ ME525+", 1,
				R.drawable.p11, 1923.00,
				"��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(12,"Sony Ericsson", 1,
				R.drawable.p12, 2231.00,"��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		products.add(new Product(13,"Lenovo", 1, R.drawable.p13,997.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		//products.add(new Product(13,"Lenovo", 1, R.drawable.p13,997.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID "));
		//Log.d("MainActivity",""+products.size());
		shoppingProducts=new ArrayList<Product>();
	}
	/*
	 * ��ȡ��ҳ���ݣ�pageIndex��ʾҳ������0��ʼ����
	 * pageSize��ʾÿҳ��������
	 * */
	/*
	@Override
	public List<Product> getByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<Product> p=new ArrayList<Product>();
		
		int maxPage=0;//maxPage��ʾ���ҳ��
		int size=products.size();//size��ʾ���ϵĳ���
		if(size!=0){
			if(size%pageSize==0){
				maxPage=size/pageSize-1;
			}else{
				maxPage=size/pageSize;
			}
			//�Ը�����ҳ�������ж������ܳ������ҳ����Ҳ����С��0
			if(pageIndex<0){
				pageIndex=0;
			}
			if(pageIndex>maxPage){
				pageIndex=maxPage;
			}
			//�ж�ÿҳ����Ŀ����Ŀ����С��0
			if(pageSize<0){
				pageSize=0;
			}
			//���ж��Ƿ������һҳ�����һҳ��ǰ���ҳ����ȡ����һ��
			if(pageIndex==maxPage){
				p=products.subList(pageIndex*pageSize,size);//���һҳֻ��ȡ��size����Ȼ��Խ��,ע��ȡͷ��ȡβ������Ӧ����size��������size-1
			}else{
				p=products.subList(pageIndex*pageSize,(pageIndex+1)*pageSize );
			}
		}
		
		return p;
		
		
		
		
	}
	*/
	//����id���Ҽ����е�ĳ����Ʒ����
	@Override
	public Product getById(int productId) {
		// TODO Auto-generated method stub
		return products.get(productId);
	}
	//���ݸ�������Ʒ���֣�ģ����ѯ���ϸ����ֵ�������Ʒ
	@Override
	public List<Product> getByName(String name) {
		// TODO Auto-generated method stub
		List<Product> p=new ArrayList<Product>();
		for(Product product:products){
			if(product.getName().indexOf(name)!=-1){
				p.add(product);
			}
		}
		return p;
	}
	//��������ĩβ������Ʒ
	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		products.add(product);

	}
	//�ڼ��ϵ�ָ��λ�ô�������Ʒ
	public void insert(Product product, int positon) {
		// TODO Auto-generated method stub
		products.add(positon, product);
	}
	//����id�޸���Ʒ
	public void modify(int productId,int categoryId,String name,String note,int pictrue,Double price) {
		// TODO Auto-generated method stub
		Product product=getById(productId);
		product.setCategoryId(categoryId);
		product.setName(name);
		product.setNote(note);
		product.setPictrue(pictrue);
		product.setPrice(price);

	}
	//ɾ�������е���Ʒ
	@Override
	public void del(Product product) {
		// TODO Auto-generated method stub
		products.remove(product);

	}
	
	//ɾ��ָ��id������Ʒ
	@Override
	public void del(int productId) {
		// TODO Auto-generated method stub
		products.remove(productId);
	}
	//���һ����Ʒ���Ͻ�ȥ
	@Override
	public void addAll(List<Product> productss) {
		// TODO Auto-generated method stub
		products.addAll(productss);
	}
	//��ҳ��������
	public List<Product> getByPage(int pageIndex,int pageSize){
		if (pageIndex<0) pageIndex=0; 
		int totalCount = products.size();  //������
		int pageCount=1; //��ҳ��
		if (totalCount % pageSize==0){
			pageCount = totalCount/pageSize;
		}else{
			pageCount = (totalCount/pageSize)+1;
		}
		
		if (pageIndex>pageCount-1)//˵�������һҳ
		{
			//pageIndex = pageCount-1;
			return null;
		}
		
		Object[] source = this.products.toArray();
		
		 int endIndex=(pageIndex+1)*pageSize;
		if (endIndex>totalCount)
			endIndex=totalCount;
		
	
		List<Product> result = new ArrayList<Product>();
		for(int i=pageIndex*pageSize;i<endIndex;i++){
			result.add((Product)source[i]);
		}
		return result;
	}
	
	//��ҳ�������ݷ�������д���÷�����Ҫ�ṩproducts������
		public List<Product> getByPage(int pageIndex,int pageSize,List<Product>productss){
			if (pageIndex<0) pageIndex=0; 
			int totalCount = productss.size();  //������
			int pageCount=1; //��ҳ��
			if (totalCount % pageSize==0){
				pageCount = totalCount/pageSize;
			}else{
				pageCount = (totalCount/pageSize)+1;
			}
			
			if (pageIndex>pageCount-1)//˵�������һҳ
			{
				//pageIndex = pageCount-1;
				return null;
			}
			
			Object[] source = productss.toArray();
			
			 int endIndex=(pageIndex+1)*pageSize;
			if (endIndex>totalCount)
				endIndex=totalCount;
			
		
			List<Product> result = new ArrayList<Product>();
			for(int i=pageIndex*pageSize;i<endIndex;i++){
				result.add((Product)source[i]);
			}
			return result;
		}
	
	//������ݵ����ﳵ����
	public Boolean addToShoppingProducts(List<Product>shoppingproducts){
		return shoppingProducts.addAll(shoppingproducts);
	}
	//���shoppingproducts
	public List<Product> getShopProducts(){
		return shoppingProducts;
	}
	//��ȡ������Ʒ
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return products;
	}
	@Override
	public void modify(int productId, int categoryId, String name, String note,
			byte[] image, Double price) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modify(int productId, int categoryId, String name, String note,
			Double price) {
		// TODO Auto-generated method stub
		
	}

}
