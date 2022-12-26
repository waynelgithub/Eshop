package main.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date = new Date();
	
	private BigDecimal amount = new BigDecimal(0);
	
	@OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ShoppingCartDetails> shoppingCartDetails;
	
	private long customer_num;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<ShoppingCartDetails> getShoppingCartDetails() {
		return shoppingCartDetails;
	}

	public void setShoppingCartDetails(List<ShoppingCartDetails> shoppingCartDetails) {
		this.shoppingCartDetails = shoppingCartDetails;
	}

	public long getCustomer_num() {
		return customer_num;
	}

	public void setCustomer_num(long customer_num) {
		this.customer_num = customer_num;
	}
	
}
