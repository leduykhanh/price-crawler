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
    
    public void crawlProducts(String URL, String productClass, String titleClass, String priceClass, boolean priceInSpan) {
        if (!links.contains(URL)) {
            try {

                if (links.add(URL)) {
                    logger.info(URL);
                }

                Document document = Jsoup.connect(URL).timeout(60000).validateTLSCertificates(false).get();

                Elements els = document.getElementsByClass(productClass);


                for (Element product : els) {
                	try {
	                	String title = product.select(titleClass).text();
	                	String priceString = priceInSpan ? product.select(priceClass).select("span").text() : product.select(priceClass).text();
	                	String href = product.select("a").attr("abs:href");
	                	logger.info(priceString);

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
            	logger.error("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
    	crawlProducts("https://www.fairprice.com.sg/", "product", ".pdt_title", ".pdt_C_price", false);
    	crawlProducts("https://www.honestbee.sg/en/groceries/stores/fairprice", "XaRs403S_a6U7-8Wfu_c3",
    			"._2UCShViKs8ydkfj-XuvUhM", "._23g1UkP8VGFqvGuLjUsc-H", true);
    }

}
