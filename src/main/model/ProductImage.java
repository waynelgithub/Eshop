package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name = "product_image")
public class ProductImage {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_num")
	private long imageNum;
	
    
    @Column(name = "image_String", columnDefinition="MEDIUMTEXT")
    private String imageBase64String;
    
	@Column(name = "file_name")
	private String fileName;

	public long getImageNum() {
		return imageNum;
	}

	public void setImageNum(long imageNum) {
		this.imageNum = imageNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getImageBase64String() {
		return imageBase64String;
	}
	
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}	
	

}
