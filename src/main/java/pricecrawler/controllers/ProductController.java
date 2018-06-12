package pricecrawler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pricecrawler.models.bean.Product;
import pricecrawler.models.dao.impl.ProductDao;

@Controller
@RequestMapping(value="/product")
public class ProductController {

  @RequestMapping(value="/list")
  @ResponseBody
  public List<Product> list() {
    return ProductDAO.selectAll();
  }
  @RequestMapping(value="/create")
  @ResponseBody
  public String create(String title, String description) {
	  try {
		  Product product = new Product();
	      product.setTitle("test");
	      ProductDAO.insert(product);
	  }
	  catch (Exception ex) {
		  return "Error creating the product: " + ex.toString();
    }
    return "Test product succesfully created!";
  }


  @Autowired
  public ProductDao ProductDAO;

} 
