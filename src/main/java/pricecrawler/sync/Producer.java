package pricecrawler.sync;

import java.io.IOException;
import java.util.HashSet;

import java.util.concurrent.BlockingQueue;

import pricecrawler.models.bean.PageLinkProductElement;
import pricecrawler.models.bean.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pricecrawler.models.dao.impl.ProductDao;

@Component
public class Producer implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
   	public ProductDao productDAO;
	
    private BlockingQueue<PageLinkProductElement> queue;
    private HashSet<String> links;
    
    public Producer() {
    	links = new HashSet<String>();
    }

    public Producer(BlockingQueue<PageLinkProductElement> q){
        this.queue=q;
        links = new HashSet<String>();
    }
    
    public void getPageLinks(String URL) {
        if (!links.contains(URL)) {
            try {

                if (links.add(URL)) {
                    System.out.println(URL);
                }

                Document document = Jsoup.connect(URL).timeout(60000).validateTLSCertificates(false).get();

                Elements els = document.getElementsByClass("product");


                for (Element product : els) {
                	try {
	                	String title = product.select(".pdt_title").text();
	                	String priceString = product.select(".pdt_C_price").text();
	                	String href = product.select("a").attr("abs:href");

	                	Product prd = new Product();
	                	
	                	prd.setTitle(title);
	                	prd.setPrice(Double.parseDouble(priceString.substring(1)));
	                	prd.setAbsoluteUrl(href);
	                	prd.setURL(href);
	                	productDAO.updateOrInsert(prd);
                	}
                	catch (Exception e) {
                		logger.error(e.getMessage());
                	}
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
    	getPageLinks("https://www.fairprice.com.sg/");
    }

}
