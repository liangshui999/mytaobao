package com.example.mytaobao.dao;

import java.util.List;

import com.example.mytaobao.model.Product;

public interface IProductService {
	public List<Product> getByPage(int pageIndex,int pageSize);//分页查询商品，pageIndex是页数，pageSize是每页的商品数
	public List<Product> getByPage(int pageIndex,int pageSize,List<Product>productss);//上面分页方法的重载，需提供数据源
	public Product getById(int productId);//根据商品的id查找商品,productId是商品的id编号
	public List<Product> getByName(String name);//根据商品的名字模糊查找商品
	public void insert(Product product);//插入商品
	public void insert(Product product,int positon);//指定位置插入商品
	public void addAll(List<Product>products);//添加一个子集合进去
	public void modify(int productId,int categoryId,String name,String note,int pictrue,Double price);//修改对应id处的商品
	public void del(int productId);//删除对应id处的商品
	public void del(Product product);//删除商品
	public Boolean addToShoppingProducts(List<Product>shoppingproducts);//网购物车里添加商品
	public List<Product> getShopProducts();//获取购物车的数据

}
