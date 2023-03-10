package main.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sales_return_status")
	private SalesReturnStatus salesReturnStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date")
	private Date createdDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "modifed_date")
	private Date modifedDate;

	public OrderDetail(long productNumber, 
			int quantity, 
			BigDecimal unitPrice, 
			BigDecimal unitPriceAmount,
			Order order, 
			SalesReturnStatus salesReturnStatus,
			Date createdDate, 
			Date modifedDate) {
		super();
		this.productNumber = productNumber;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.unitPriceAmount = unitPriceAmount;
		this.order = order;
		this.salesReturnStatus = salesReturnStatus;
		this.createdDate = createdDate;
		this.modifedDate = modifedDate;
	}
	
	//給前端 thymeleaf ，取得退貨進度代碼對應該顯示的訊息
	public String getSalesReturnStatusMessage() {
		return salesReturnStatus.getMessageCode();
	}
	
	/**
	 * 取得是否可退貨的狀態
	 * @return true 代表可以退貨
	 */
	public boolean isReturnable() {
		
		// can't return non-returnable products
		// can't return a product that is returning
		if (!this.salesReturnStatus.equals(SalesReturnStatus.NOT_RETURN))
			return false;
		
		//return window: within 7 days from the day upon which they receive the products
		if (!isReturnWindowWithIn7days())
			return false;
		
		return true;
	}
	
	/**
	 * 訂單明細日期是否還在7天退貨期限內
	 * @return true 
	 */
	public boolean isReturnWindowWithIn7days(){
		//取 createdDate +7 為過期日
		LocalDate returnDueDate = createdDate
								.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
								.plusDays(7)
								;
		
		//取當前日期
		LocalDate today = LocalDate.now();
		
		//過期日超過當前日為 false
		return returnDueDate.isAfter(today);
		
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifedDate() {
		return modifedDate;
	}

	public void setModifedDate(Date modifedDate) {
		this.modifedDate = modifedDate;
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
				+ ", salesReturnStatus=" + salesReturnStatus 
				+ ", createdDate" + createdDate
				+ ", modifedDate" + modifedDate
				+ "]";
	}



	
	
	
}