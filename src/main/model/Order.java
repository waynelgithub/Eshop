package main.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sales_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_number")
	private long orderNumber;

	@Column(name = "customer_number")
	private String customerNumer;

	@Column(name = "order_amount")
	private BigDecimal orderAmount;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderDetail> orderDetails;

	
	public enum PaymentStatus{
        UNPAID,
        PAID
	}
	
    public enum OrderStatus{
        OPEN,
        FILLED, 
        CANCELED
    }
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "order_created_date")
	private Date orderCreatedDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "order_modifed_date")
	private Date orderModifedDate;



	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCustomerNumer() {
		return customerNumer;
	}

	public void setCustomerNumer(String customerNumer) {
		this.customerNumer = customerNumer;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Date getOrderCreatedDate() {
		return orderCreatedDate;
	}

	public void setOrderCreatedDate(Date orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}

	public Date getOrderModifedDate() {
		return orderModifedDate;
	}

	public void setOrderModifedDate(Date orderModifedDate) {
		this.orderModifedDate = orderModifedDate;
	}

	@Override
	public String toString() {
		return "\n"
				+ "Order [orderNumber=" + orderNumber 
				+ ", customerNumer=" + customerNumer 
				+ ", orderAmount=" + orderAmount
				+ ", orderCreatedDate=" + orderCreatedDate 
				+ ", orderModifedDate=" + orderModifedDate
				+"\n"
				+ ", orderDetails=" + orderDetails + "]"
				+ "\n";
	}





	
}
