package com.example.mytaobao.daomplZhenshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import com.example.mytaobao.dao.IProductService;
import com.example.mytaobao.db.HandleContentValues;
import com.example.mytaobao.db.HandleCursor;
import com.example.mytaobao.db.HandleSqlValues;
import com.example.mytaobao.db.MyDatabaseOperateHelper;
import com.example.mytaobao.model.Product;
import com.example.mytaobao.util.ImageUtil;
import com.example.mytaobao.util.MyLog;

public class ZProductService implements IProductService {
	private MyDatabaseOperateHelper helper;
	private String path;
	public ZProductService() {
		helper=MyDatabaseOperateHelper.getDatabaseOperateHelper();		
		try {
			path = Environment.getExternalStorageDirectory().getCanonicalPath();
			Product product1=new Product(1,"三星 GT-S5830", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p1.jpg")),
					1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
			
			Product product2=new Product(2,"HTC A510e", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p2.jpg")),
					1514.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
			Product product3=new Product(3,"三星 I9100", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p3.jpg")),
					3266.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
			Product product4=new Product(4,"中兴 U880（TD版）", 1,
					ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p4.jpg")), 989.00,
					"网络类型:TD-SCDMA(3G) 操作系统:ANDROID  ");
			Product product5=new Product(5,"Sony Ericsson索尼爱立信 ", 1,
					ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p5.jpg")), 2584.00,
					"网络类型:WCDMA(3G) 操作系统:ANDROID ");
			Product product6=new Product(6,"摩托罗拉 Defy", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p6.jpg")),
					1851.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
			Product product7=new Product(7,"中兴 V880", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p7.jpg")),
					957.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
			List<Product>products=new ArrayList<Product>();
			products.add(product1);
			products.add(product2);
			products.add(product3);
			products.add(product4);
			products.add(product5);
			products.add(product6);
			products.add(product7);
			addAll(products);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/*
	 * 分页查询数据,pageindex从0算起
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		//sum是商品的总条数
		int sum=(Integer) helper.query("select count(*) as count from product ", null, new HandleCursor() {			
			@Override
			public Object handleCursor(Cursor cursor) {
				// TODO Auto-generated method stub
				int sum1=-1;
				if(cursor.moveToNext()){
					sum1=cursor.getInt(cursor.getColumnIndex("count"));
				}
				return sum1;
			}
		});
		MyLog.d("ZProductService", "总条数="+sum);
		//pages是总页数
		int pages=1;
		if(pageSize>0){
			if(sum%pageSize==0){
				pages=sum/pageSize;
			}else{
				pages=sum/pageSize+1;
			}			
		}
		MyLog.d("ZProductService", "总页数="+pages);
		MyLog.d("ZProductService", "页面索引="+pageIndex);
		
		//pageindex是页面索引，从0开始算起,只要索引超出范围，直接return null
		if(pageIndex<0){
			MyLog.d("ZProductService", "页面索引="+pageIndex);
			return null;
		}else if(pageIndex>pages-1){
			MyLog.d("ZProductService", "页面索引="+pageIndex);
			return null;
		}else{
			//分页的具体实现		
			List<Product>products=(List<Product>) helper.query("select * from product limit ? offset ?"
					, new String[]{pageSize+"",pageIndex*pageSize+""}
					, new HandleCursor() {			
				@Override
				public Object handleCursor(Cursor cursor) {
					// TODO Auto-generated method stub
					List<Product>productss=new ArrayList<Product>();
					Product product=null;
					while(cursor.moveToNext()){
						int id=cursor.getInt(cursor.getColumnIndex("id"));
						String name=cursor.getString(cursor.getColumnIndex("name"));
						int categoryId=cursor.getInt(cursor.getColumnIndex("categoryId"));
						byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
						double price=cursor.getDouble(cursor.getColumnIndex("price"));
						String note=cursor.getString(cursor.getColumnIndex("note"));
						product=new Product(id, name, categoryId, image, price, note);
						productss.add(product);
					}
					MyLog.d("ZProductService", "productss的大小="+productss.size());
					return productss;				
				}
			});
			//MyLog.d("ZProductService", "products的大小="+products.size());			
			return products;
		}
		
	}

	@Override
	public List<Product> getByPage(int pageIndex, int pageSize,
			List<Product> productss) {
		// TODO Auto-generated method stub
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
	/*
	 *根据商品id查找商品 
	 * */
	@Override
	public Product getById(int productId) {
		// TODO Auto-generated method stub
		String sql="select * from product where id=?";
		String[] values=new String[]{productId+""};
		Product product=(Product) helper.query(sql, values, new HandleCursor() {
			
			@Override
			public Object handleCursor(Cursor cursor) {
				// TODO Auto-generated method stub
				Product product1=null;
				if(cursor.moveToNext()){
					int id=cursor.getInt(cursor.getColumnIndex("id"));
					String name=cursor.getString(cursor.getColumnIndex("name"));
					int categoryId=cursor.getInt(cursor.getColumnIndex("categoryId"));
					byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
					double price=cursor.getDouble(cursor.getColumnIndex("price"));
					String note=cursor.getString(cursor.getColumnIndex("note"));
					product1=new Product(id, name, categoryId, image, price, note);
				}
				return product1;
			}
		});
		return product;
	}
	/*
	 * 根据商品名称进行模糊查询
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getByName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from product where name like ?";
		String[] values=new String[]{"%"+name+"%"};//前面后面都加通配符
		List<Product>products=(List<Product>) helper.query(sql, values, new HandleCursor() {
			
			@Override
			public Object handleCursor(Cursor cursor) {
				// TODO Auto-generated method stub
				List<Product>productss=new ArrayList<Product>();
				Product product=null;
				while(cursor.moveToNext()){
					int id=cursor.getInt(cursor.getColumnIndex("id"));
					String name=cursor.getString(cursor.getColumnIndex("name"));
					int categoryId=cursor.getInt(cursor.getColumnIndex("categoryId"));
					byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
					double price=cursor.getDouble(cursor.getColumnIndex("price"));
					String note=cursor.getString(cursor.getColumnIndex("note"));
					product=new Product(id, name, categoryId, image, price, note);
					productss.add(product);
				}
				return productss;
			}
		});
		return products;
	}
	/*
	 *插入商品 
	 * */
	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		/*String sql="insert into product values(?,?,?,?,?,?)";
		String[] zhi=new String[]{product.getId()+"",product.getName(),product.getCategoryId()+"",
				product.getImage()+"",product.getPrice()+"",product.getNote()};
		helper.zsg(sql, zhi);*/
		
		ContentValues contentValues=new ContentValues();
		contentValues.put("id", product.getId());
		contentValues.put("name", product.getName());
		contentValues.put("categoryId", product.getCategoryId());
		contentValues.put("image", product.getImage());
		contentValues.put("price", product.getPrice());
		contentValues.put("note", product.getNote());
		helper.insert("product", contentValues);

	}

	@Override
	public void insert(Product product, int positon) {
		// TODO Auto-generated method stub		

	}
	/*
	 *批量插入商品 
	 * */
	@Override
	public void addAll(List<Product> products) {
		// TODO Auto-generated method stub
		helper.plInsert("product", products, new HandleContentValues() {
			
			@Override
			public ContentValues handleContentValues(Object object) {
				// TODO Auto-generated method stub
				Product product=(Product) object;
				ContentValues contentValues=new ContentValues();
				contentValues.put("id", product.getId());
				contentValues.put("name", product.getName());
				contentValues.put("categoryId", product.getCategoryId());
				contentValues.put("image", product.getImage());
				contentValues.put("price", product.getPrice());
				contentValues.put("note", product.getNote());
				return contentValues;
			}
		});
		/*String sql="insert into product values(?,?,?,?,?,?)";
		helper.plZsg(sql, products, new HandleSqlValues() {
			
			@Override
			public String[] handSqlValues(Object object) {
				// TODO Auto-generated method stub
				Product product=(Product) object;
				return new String[]{product.getId()+"",product.getName(),product.getCategoryId()+"",
						product.getImage()+"",product.getPrice()+"",product.getNote()};
			}
		});*/
	}
	
	@Override
	public void modify(int productId, int categoryId, String name, String note,
			int pictrue, Double price) {
		// TODO Auto-generated method stub		

	}
	/*
	 * 根据商品id删除商品
	 * */
	@Override
	public void del(int productId) {
		// TODO Auto-generated method stub
		String sql="delete from product where id=?";
		String[] values=new String[]{productId+""};
		helper.zsg(sql, values);

	}
	/*
	 *根据商品对象删除商品 
	 * */
	@Override
	public void del(Product product) {
		// TODO Auto-generated method stub
		this.del(product.getId());
	}

	@Override
	public Boolean addToShoppingProducts(List<Product> shoppingproducts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getShopProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 *获得所有商品 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		String sql="select * from product ";
		List<Product>products=(List<Product>) helper.query(sql, null, new HandleCursor() {
			
			@Override
			public Object handleCursor(Cursor cursor) {
				// TODO Auto-generated method stub
				List<Product>productss=new ArrayList<Product>();
				Product product1=null;
				while(cursor.moveToNext()){
					int id=cursor.getInt(cursor.getColumnIndex("id"));
					String name=cursor.getString(cursor.getColumnIndex("name"));
					int categoryId=cursor.getInt(cursor.getColumnIndex("categoryId"));
					byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
					double price=cursor.getDouble(cursor.getColumnIndex("price"));
					String note=cursor.getString(cursor.getColumnIndex("note"));
					product1=new Product(id, name, categoryId, image, price, note);
					productss.add(product1);
				}
				return productss;
			}
		});
		return products;
	}
	/*
	 * 根据商品id修改商品
	 * */
	@Override
	public void modify(int productId, int categoryId, String name, String note,
			 Double price) {
		// TODO Auto-generated method stub
		String sql="update product set name=?,categoryId=?,price=?,note=? where id=?";
		String[] values=new String[]{name,categoryId+"",price+"",note,productId+""};
		helper.zsg(sql, values);
	}

	@Override
	public void modify(int productId, int categoryId, String name, String note,
			byte[] image, Double price) {
		// TODO Auto-generated method stub
		
	}

}
