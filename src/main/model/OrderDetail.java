package main.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sales_return_status")
	private SalesReturnStatus salesReturnStatus;

	public OrderDetail(long productNumber, int quantity, BigDecimal unitPrice, BigDecimal unitPriceAmount,
			Order order, SalesReturnStatus salesReturnStatus) {
		super();
		this.productNumber = productNumber;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.unitPriceAmount = unitPriceAmount;
		this.order = order;
		this.salesReturnStatus = salesReturnStatus;
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

	public SalesReturnStatus getSalesReturnStatus() {
		return salesReturnStatus;
	}

	public void setSalesReturnStatus(SalesReturnStatus salesReturnStatus) {
		this.salesReturnStatus = salesReturnStatus;
	}

	@Override
	public String toString() {
		return "\n"
				+ "OrderDetail [orderDetailId=" + orderDetailId 
				+ ", productNumber=" + productNumber
				+ ", quantity=" + quantity 
				+ ", unitPrice=" + unitPrice 
				+ ", unitPriceAmount=" + unitPriceAmount 
				+ ", orderNumber=" + order.getOrderNumber()
				+ ", salesReturnStatus=" + salesReturnStatus + "]";
	}



	
	
	
}