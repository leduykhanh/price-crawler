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
		        "from User where id = :id")
		        .setParameter("id", id)
		        .getSingleResult();
	}
	
	@PersistenceContext
	public EntityManager entityManager;

}
