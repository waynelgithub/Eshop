package main.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "{tour.date.notnull}")
	@Future(message = "{tour.date.future}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private BigDecimal amount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shopping_cart_details_id")
	private ShoppingCartDetails shoppingCartDetails;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public ShoppingCartDetails getShoppingCartDetails() {
		return shoppingCartDetails;
	}

	public void setShoppingCartDetails(ShoppingCartDetails shoppingCartDetails) {
		this.shoppingCartDetails = shoppingCartDetails;
	}
	
	
}
