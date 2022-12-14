package main.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer {
	
	@NotBlank(message = "{cus.name}")
	@Size(min = 1)
	private String customerName;
	
	@NotBlank(message = "{cus.county}")
	@Size(min = 1)
	private String county;
	
	@Pattern(regexp = "^[0-9]{5}$")
	private String customerNumber;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
}
