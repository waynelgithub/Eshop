package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;



//@Entity(name = "product_image")
public class ProductImageBackup {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_num")
	private long imageNum;
	
    @Column(name = "image_blob", columnDefinition="MEDIUMBLOB")
    private byte[] image;
    
    @Lob
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageBase64String() {
		return imageBase64String;
	}

	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}
	
	

//	@Column(name = "prod_num")
//	private long prodNum;
	
	

}
