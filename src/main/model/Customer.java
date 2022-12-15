package main.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer {
	
	public enum Continent {
		台北市, 新北市, 桃園市, 新竹縣, 新竹市, 苗栗縣, 苗栗市, 台中市, 彰化縣, 彰化市, 雲林縣, 雲林市, 嘉義縣, 嘉義市, 台南市, 高雄市, 屏東縣,
		屏東市, 台東縣, 台東市, 花蓮縣, 花蓮市, 宜蘭縣, 宜蘭市, 南投縣
	}
	
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
