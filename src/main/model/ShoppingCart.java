package main.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "{tour.date.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private BigDecimal amount;
	
	@OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL)
	private List<ShoppingCartDetails> shoppingCartDetails;

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

//	public ShoppingCartDetails getShoppingCartDetails() {
//		return shoppingCartDetails;
//	}
//
//	public void setShoppingCartDetails(ShoppingCartDetails shoppingCartDetails) {
//		this.shoppingCartDetails = shoppingCartDetails;
//	}
	
}
