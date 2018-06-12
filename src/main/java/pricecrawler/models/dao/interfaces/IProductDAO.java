package pricecrawler.models.dao.interfaces;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pricecrawler.models.bean.Product;


public interface IProductDAO {

	Log log = LogFactory.getLog(IProductDAO.class);
	

	void insert(Product product);

	List<Product> selectAll();

	Product findById(String id);
}