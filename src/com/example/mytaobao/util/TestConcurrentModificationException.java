package com.example.mytaobao.util;

import java.util.ArrayList;
import java.util.List;

import com.example.mytaobao.R;
import com.example.mytaobao.model.Product;

public class TestConcurrentModificationException {
	
	private List<Product> products=new ArrayList<Product>();//��ʼ���������ݷ�������������棬���Ǵ��������������ɾ�Ĳ�
	//��Ʒ��Ŵ�0��ʼ���ȽϺü���һ��
	public TestConcurrentModificationException() {
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
		
	}
	/*
	 * ��ȡ��ҳ���ݣ�pageIndex��ʾҳ������0��ʼ����
	 * pageSize��ʾÿҳ��������
	 * */
	public void main(String[]args){
		int pageIndex=0;
		int pageSize=5;
		List<Product>products1=getPage(pageIndex, pageSize);
		List<Product>products2=getPage(++pageIndex, pageSize);
		products1.addAll(products2);
		System.out.println(products1.size());
	}
	public List<Product> getPage(int pageIndex, int pageSize) {
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
}