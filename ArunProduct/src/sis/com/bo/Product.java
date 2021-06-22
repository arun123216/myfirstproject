package sis.com.bo;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String details;
	private float price;
	java.sql.Date dateOfManufacture;
	java.sql.Timestamp recordCreated;

	public Product() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	

	public java.sql.Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(java.sql.Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public java.sql.Timestamp getRecordCreated() {
		return recordCreated;
	}

	public void setRecordCreated(java.sql.Timestamp recordCreated) {
		this.recordCreated = recordCreated;
	}

}

