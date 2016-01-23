package com.example.mytaobao.biz;

import java.util.List;

import com.example.mytaobao.dao.IProductService;
import com.example.mytaobao.daompl.ProductService;
import com.example.mytaobao.model.Product;

public class ProductManager {
	private IProductService ip=null;
	
	public ProductManager() {
		ip=new ProductService();
	}
	public List<Product> getByPage(int pageIndex, int pageSize){
		return ip.getByPage(pageIndex, pageSize);
	}
	public List<Product> getByPage(int pageIndex,int pageSize,List<Product>productss){
		return ip.getByPage(pageIndex, pageSize, productss);
	}
	public Product getById(int productId){
		return ip.getById(productId);
	}
	public List<Product> getByName(String name){
		return ip.getByName(name);
	}
	public void insert(Product product){
		ip.insert(product);
	}
	public void insert(Product product, int positon){
		ip.insert(product, positon);
	}
	public void modify(int productId,int categoryId,String name,String note,int pictrue,Double price){
		ip.modify(productId, categoryId, name, note, pictrue, price);
	}
	public void del(Product product){
		ip.del(product);
	}
	public void del(int productId){
		ip.del(productId);
	}
	public void addAll(List<Product>products){
		ip.addAll(products);
	}
	public Boolean addToShoppingProducts(List<Product>shoppingproducts){
		return ip.addToShoppingProducts(shoppingproducts);
	}
	public List<Product> getShopProducts(){
		return ip.getShopProducts();
	}

}
