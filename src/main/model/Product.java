package main.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@DecimalMin(value = "10000", message = "{product.number.min}")
	@Column(name = "prod_num")
	private long prodNum;
	
	@Size(min = 2, message = "{product.type.size}")
	@Column(name = "prod_type")
	private String prodType;
	
	@Size(min = 2, message = "{product.prodline.size}")
	@Column(name = "prod_line")
	private String prodline;
	
	@NotBlank(message = "{product.name.notblank}")
	@Size(min = 2, message = "{product.name.size}")
	@Column(name = "prod_name")
	private String prodName;
	
	@DecimalMin(value = "0.0", message = "{product.price.min}")
	@Column(name = "prod_price")
	private BigDecimal prodPrice;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_image_num")
	private ProductImage productImage;
	

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getProdNum() {
		return prodNum;
	}


	public void setProdNum(long prodNum) {
		this.prodNum = prodNum;
	}


	public String getProdType() {
		return prodType;
	}


	public void setProdType(String prodType) {
		this.prodType = prodType;
	}


	public String getProdline() {
		return prodline;
	}


	public void setProdline(String prodline) {
		this.prodline = prodline;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public BigDecimal getProdPrice() {
		return prodPrice;
	}


	public void setProdPrice(BigDecimal prodPrice) {
		this.prodPrice = prodPrice;
	}


	public ProductImage getProductImage() {
		return productImage;
	}


	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}

}
