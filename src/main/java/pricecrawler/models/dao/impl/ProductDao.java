package pricecrawler.models.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import pricecrawler.models.bean.Product;
import pricecrawler.models.dao.interfaces.IProductDAO;


@Repository
@Transactional
public class ProductDao  {
	public void insert(Product product) {
		entityManager.persist(product);
	    return;
	}


	@SuppressWarnings("unchecked")
	public List<Product> selectAll() {
		return entityManager.createQuery("from Product").getResultList();
	}

	public Product findById(String id) {
		return (Product) entityManager.createQuery(
		        "from Product where id = :id")
		        .setParameter("id", id)
		        .getSingleResult();
	}
	
	public Product getByTitle(String title) {
	    return (Product) entityManager.createQuery(
	        "from Product where title = :title")
	        .setParameter("title", title)
	        .getSingleResult();
		  }
	public void update(Product product) {
		entityManager.merge(product);
		return;
		  }
	 
	public void updateOrInsert(Product product) {
		try {
			Product prdRecord = getByTitle(product.getTitle());
			prdRecord.setPrice(product.getPrice());
			update(prdRecord);
		}
		catch(Exception e) {
			insert(product);
		}
	}
	
	@PersistenceContext
	public EntityManager entityManager;

}
