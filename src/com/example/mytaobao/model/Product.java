package com.example.mytaobao.model;

public class Product {
	private int id;
	private String name;
	private int categoryId;//商品所属的类别id
	private int pictrue;//模拟用的，准备废弃掉
	private byte[] image;
	private Double price;
	private String note;//商品的备注信息
	/*
	 *准备废弃掉的构造方法 
	 * */
	public Product(int id, String name, int categoryId, int pictrue, Double price,
			String note) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.pictrue = pictrue;
		this.price = price;
		this.note = note;
	}
	
	public Product(int id, String name, int categoryId, byte[] image,
			Double price, String note) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.image = image;
		this.price = price;
		this.note = note;
	}

	public Product(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getPictrue() {
		return pictrue;
	}
	public void setPictrue(int pictrue) {
		this.pictrue = pictrue;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", categoryId="
				+ categoryId + ", pictrue=" + pictrue + ", price=" + price
				+ ", note=" + note + "]";
	}
	

}
