package com.example.mytaobao.daomplZhenshi;

import java.util.List;

import com.example.mytaobao.dao.IProductService;
import com.example.mytaobao.model.Product;

public class ZProductService implements IProductService {

	@Override
	public List<Product> getByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getByPage(int pageIndex, int pageSize,
			List<Product> productss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Product product, int positon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAll(List<Product> products) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(int productId, int categoryId, String name, String note,
			int pictrue, Double price) {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(int productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(Product product) {
		// TODO Auto-generated method stub

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

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
