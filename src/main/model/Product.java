package main.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product {
 


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "prod_num")
	private BigInteger prodNum;
	
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


	public BigDecimal getProd_price() {
		return prod_price;
	}


	public void setProd_price(BigDecimal prod_price) {
		this.prod_price = prod_price;
	}


	@Column(name = "prod_type")
	private String prodType;
	
	@Column(name = "prod_line")
	private String prodline;
	
	@Column(name = "prod_name")
	private String prodName;
	
	
	@Column(name = "prod_price")
	private BigDecimal prod_price;
	

}
