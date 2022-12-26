package main.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	private long id;

	@Column(name = "ord_num")
	private long ordNum;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "cust_num")
	private long custNum;

	@Column(name = "ord_amt")
	private BigDecimal ordAmt;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;

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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getCustNum() {
		return custNum;
	}

	public void setCustNum(long custNum) {
		this.custNum = custNum;
	}

	public BigDecimal getOrdAmt() {
		return ordAmt;
	}

	public void setOrdAmt(BigDecimal ordAmt) {
		this.ordAmt = ordAmt;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
