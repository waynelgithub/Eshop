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

@Entity
@Table(name = "sales_order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private long orderDetailId;
	
	@Column(name = "product_number")
	private long productNumber;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	
	@Column(name = "unit_price_amount")
	private BigDecimal unitPriceAmount;	
	
	@ManyToOne
	@JoinColumn(name = "order_number")
	private Order order;

	public OrderDetail(long productNumber, int quantity, BigDecimal unitPrice, BigDecimal unitPriceAmount,
			Order order) {
		super();
		this.productNumber = productNumber;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.unitPriceAmount = unitPriceAmount;
		this.order = order;
	}

	public OrderDetail() {
		super();
	}

	public long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(long productNumber) {
		this.productNumber = productNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(BigDecimal unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", productNumber=" + productNumber + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + ", unitPriceAmount=" + unitPriceAmount + ", orderNumber=" + order.getOrderNumer()
				+ "]";
	}

	
	
	
}