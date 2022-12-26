package main.model;

import java.sql.Blob;

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
    private Blob imageBlob;
    
	@Column(name = "file_name")
	private String fileName;

	public long getImageNum() {
		return imageNum;
	}

	public void setImageNum(long imageNum) {
		this.imageNum = imageNum;
	}

	public Blob getImageBlob() {
		return imageBlob;
	}

	public void setImageBlob(Blob imageBlob) {
		this.imageBlob = imageBlob;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

//	@Column(name = "prod_num")
//	private long prodNum;
	
	

}
