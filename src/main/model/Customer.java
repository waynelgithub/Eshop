package main.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 1)
	private String name;

	private String county;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_details")
	private CustomerDetails customerDetails;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerComment> customerComments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public List<CustomerComment> getCustomerComments() {
		return customerComments;
	}

	public void setCustomerComments(List<CustomerComment> customerComments) {
		this.customerComments = customerComments;
	}

}
