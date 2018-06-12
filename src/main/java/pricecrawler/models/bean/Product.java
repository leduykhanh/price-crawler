package pricecrawler.models.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	String absoluteUrl = null;
	@NotNull
	String title = null;
	String description = null;
	Double price = null;
	
	public Product() {}
	
	public Product(long id) {
		this.id = id;
	}
	
	public String getAbsoluteUrl() {
		return absoluteUrl;
	}

	public void setAbsoluteUrl(String absoluteUrl) {
		this.absoluteUrl = absoluteUrl;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	String url = null;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Class: Product ID {")
				.append(this.getId()).append("}\r\n").append("Title [")
				.append(this.getTitle()).append("] Price : [")
				.append(this.getPrice()).append("] Description : [")
				.append(this.getDescription()).append("]\r\n").append("URL [")
				.append(this.getAbsoluteUrl()).append("]");
		return sb.toString();
	}
}
