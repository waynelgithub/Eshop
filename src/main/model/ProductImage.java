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
	
    @Column(name = "image_blob", columnDefinition="MEDIUMBLOB")
    private byte[] image;
    
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

//	@Column(name = "prod_num")
//	private long prodNum;
	
	

}
