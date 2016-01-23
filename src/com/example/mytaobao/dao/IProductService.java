package com.example.mytaobao.dao;

import java.util.List;

import com.example.mytaobao.model.Product;

public interface IProductService {
	public List<Product> getByPage(int pageIndex,int pageSize);//��ҳ��ѯ��Ʒ��pageIndex��ҳ����pageSize��ÿҳ����Ʒ��
	public List<Product> getByPage(int pageIndex,int pageSize,List<Product>productss);//�����ҳ���������أ����ṩ����Դ
	public Product getById(int productId);//������Ʒ��id������Ʒ,productId����Ʒ��id���
	public List<Product> getByName(String name);//������Ʒ������ģ��������Ʒ
	public void insert(Product product);//������Ʒ
	public void insert(Product product,int positon);//ָ��λ�ò�����Ʒ
	public void addAll(List<Product>products);//���һ���Ӽ��Ͻ�ȥ
	public void modify(int productId,int categoryId,String name,String note,int pictrue,Double price);//�޸Ķ�Ӧid������Ʒ
	public void del(int productId);//ɾ����Ӧid������Ʒ
	public void del(Product product);//ɾ����Ʒ
	public Boolean addToShoppingProducts(List<Product>shoppingproducts);//�����ﳵ�������Ʒ
	public List<Product> getShopProducts();//��ȡ���ﳵ������

}
