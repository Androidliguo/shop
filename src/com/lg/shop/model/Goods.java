package com.lg.shop.model;

public class Goods {
	private int id;
	@ValidateForm(type=ValidateType.NotNull,errorMsg="商品名称不能为空")
	private String name;
	@ValidateForm(type=ValidateType.Number,errorMsg="商品价格必为数字")
	private double price;
	private String intro;
	private String img;
	@ValidateForm(type=ValidateType.Number,errorMsg="商品库存必须为数字")
	private int stock;
	private Category category;
	private int status;
	 
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * 增加商品的状态。0表示的可以购买的上架的上平。1表示的是下架的商品。
	 * @return
	 */
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
