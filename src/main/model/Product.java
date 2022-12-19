package main.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
 


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "prod_num")
	private BigInteger prodNum;
	
	@Column(name = "prod_type")
	private String prodType;
	
	@Column(name = "prod_line")
	private String prodline;
	
	@Column(name = "prod_name")
	private String prodName;
	
	@Column(name = "prod_price")
	private BigDecimal prodPrice;
	

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}




	public BigInteger getProdNum() {
		return prodNum;
	}


	public void setProdNum(BigInteger prodNum) {
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





	

}
