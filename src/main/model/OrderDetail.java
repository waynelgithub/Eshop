package main.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 
 * @author hsu
 *
 */
@Entity
@Table(name = "sales_order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "ord_num")
	private long ordNum;
	
	@Column(name = "prod_num")
	private long prodNum;
	
	@Column(name = "ord_qty")
	private int ordQty;
	
	@Column(name = "ord_price")
	private BigDecimal ordPrice;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrdNum() {
		return ordNum;
	}

	public void setOrdNum(long ordNum) {
		this.ordNum = ordNum;
	}

	public long getProdNum() {
		return prodNum;
	}

	public void setProdNum(long prodNum) {
		this.prodNum = prodNum;
	}

	public int getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(int ordQty) {
		this.ordQty = ordQty;
	}

	public BigDecimal getOrdPrice() {
		return ordPrice;
	}

	public void setOrdPrice(BigDecimal ordPrice) {
		this.ordPrice = ordPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	




	





}
