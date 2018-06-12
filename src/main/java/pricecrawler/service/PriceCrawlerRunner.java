package pricecrawler.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import pricecrawler.sync.Producer;

//@Service
public class PriceCrawlerRunner {
	@Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    @PersistenceContext
    private EntityManager entityManager;
    
    public void executeAsynchronously() {
    	System.out.println(taskExecutor);
        Producer producer = applicationContext.getBean(Producer.class);
        //taskExecutor.execute(producer);
    }
    
    public static void main(String[] args) {
    	new PriceCrawlerRunner().executeAsynchronously();
    }
    

}
