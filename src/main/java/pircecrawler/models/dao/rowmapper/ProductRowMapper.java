package pircecrawler.models.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper {

	// title, description, price and URL
	String title = null;
	String description = null;
	Double price = null;

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
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductRowMapper product = new ProductRowMapper();
		product.setTitle(rs.getString("TITLE"));
		product.setDescription(rs.getString("DESCRIPTION"));
		product.setPrice(rs.getDouble("PRICE"));
		product.setURL(rs.getString("URL"));
		return product;
	}
}
