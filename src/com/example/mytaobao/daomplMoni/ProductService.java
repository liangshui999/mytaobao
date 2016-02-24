package com.example.mytaobao.daomplMoni;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.mytaobao.dao.IProductService;
import com.example.mytaobao.model.Product;
import com.example.mytaobao.*;

public class ProductService implements IProductService {
	private List<Product> products=new ArrayList<Product>();//初始给定的数据放在这个集合里面，就是从这个集合里面增删改查
	private List<Product>shoppingProducts;//购物车里面的数据
	//商品编号从0开始，比较好计算一点
	public ProductService() {
		products.add(new Product(0,"三星 GT-S58", 1, R.drawable.p1,
				1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(1,"三星 GT-S5830", 1, R.drawable.p1,
				1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(2,"HTC A510e", 1, R.drawable.p2,
				1514.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(3,"三星 I9100", 1, R.drawable.p3,
				3266.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(4,"中兴 U880（TD版）", 1,
				R.drawable.p4, 989.00,
				"网络类型:TD-SCDMA(3G) 操作系统:ANDROID  "));
		products.add(new Product(5,"Sony Ericsson索尼爱立信 ", 1,
				R.drawable.p5, 2584.00,
				"网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(6,"摩托罗拉 Defy", 1, R.drawable.p6,
				1851.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(7,"中兴 V880", 1, R.drawable.p7,
				957.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(8,"HTC S710e", 1, R.drawable.p8,
				2671.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(9,"摩托罗拉 ME525",1, R.drawable.p9,
				1853.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(10,"G12", 1, R.drawable.p10,
				2470.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(11,"摩托罗拉 ME525+", 1,
				R.drawable.p11, 1923.00,
				"网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(12,"Sony Ericsson", 1,
				R.drawable.p12, 2231.00,"网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(13,"Lenovo", 1, R.drawable.p13,997.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		//products.add(new Product(13,"Lenovo", 1, R.drawable.p13,997.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		//Log.d("MainActivity",""+products.size());
		shoppingProducts=new ArrayList<Product>();
	}
	/*
	 * 获取分页数据，pageIndex表示页数，从0开始计数
	 * pageSize表示每页的数据量
	 * */
	/*
	@Override
	public List<Product> getByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<Product> p=new ArrayList<Product>();
		
		int maxPage=0;//maxPage表示最大页数
		int size=products.size();//size表示集合的长度
		if(size!=0){
			if(size%pageSize==0){
				maxPage=size/pageSize-1;
			}else{
				maxPage=size/pageSize;
			}
			//对给定的页数进行判定，不能超过最大页数，也不能小于0
			if(pageIndex<0){
				pageIndex=0;
			}
			if(pageIndex>maxPage){
				pageIndex=maxPage;
			}
			//判断每页的数目，数目不能小于0
			if(pageSize<0){
				pageSize=0;
			}
			//再判断是否是最后一页，最后一页和前面的页数的取法不一样
			if(pageIndex==maxPage){
				p=products.subList(pageIndex*pageSize,size);//最后一页只能取到size，不然会越界,注意取头不取尾，所以应该是size，而不是size-1
			}else{
				p=products.subList(pageIndex*pageSize,(pageIndex+1)*pageSize );
			}
		}
		
		return p;
		
		
		
		
	}
	*/
	//根据id查找集合中的某个商品对象
	@Override
	public Product getById(int productId) {
		// TODO Auto-generated method stub
		return products.get(productId);
	}
	//根据给定的商品名字，模糊查询符合该名字的所有商品
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
	//往集合中末尾插入商品
	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		products.add(product);

	}
	//在集合的指定位置处插入商品
	public void insert(Product product, int positon) {
		// TODO Auto-generated method stub
		products.add(positon, product);
	}
	//根据id修改商品
	public void modify(int productId,int categoryId,String name,String note,int pictrue,Double price) {
		// TODO Auto-generated method stub
		Product product=getById(productId);
		product.setCategoryId(categoryId);
		product.setName(name);
		product.setNote(note);
		product.setPictrue(pictrue);
		product.setPrice(price);

	}
	//删除集合中的商品
	@Override
	public void del(Product product) {
		// TODO Auto-generated method stub
		products.remove(product);

	}
	
	//删除指定id处的商品
	@Override
	public void del(int productId) {
		// TODO Auto-generated method stub
		products.remove(productId);
	}
	//添加一个商品集合进去
	@Override
	public void addAll(List<Product> productss) {
		// TODO Auto-generated method stub
		products.addAll(productss);
	}
	//分页查找数据
	public List<Product> getByPage(int pageIndex,int pageSize){
		if (pageIndex<0) pageIndex=0; 
		int totalCount = products.size();  //总条数
		int pageCount=1; //总页数
		if (totalCount % pageSize==0){
			pageCount = totalCount/pageSize;
		}else{
			pageCount = (totalCount/pageSize)+1;
		}
		
		if (pageIndex>pageCount-1)//说明是最后一页
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
	
	//分页查找数据方法的重写，该方法需要提供products的数据
		public List<Product> getByPage(int pageIndex,int pageSize,List<Product>productss){
			if (pageIndex<0) pageIndex=0; 
			int totalCount = productss.size();  //总条数
			int pageCount=1; //总页数
			if (totalCount % pageSize==0){
				pageCount = totalCount/pageSize;
			}else{
				pageCount = (totalCount/pageSize)+1;
			}
			
			if (pageIndex>pageCount-1)//说明是最后一页
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
	
	//添加数据到购物车里面
	public Boolean addToShoppingProducts(List<Product>shoppingproducts){
		return shoppingProducts.addAll(shoppingproducts);
	}
	//获得shoppingproducts
	public List<Product> getShopProducts(){
		return shoppingProducts;
	}
	//获取所有商品
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
