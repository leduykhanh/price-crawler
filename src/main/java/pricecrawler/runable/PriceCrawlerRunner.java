package pricecrawler.runable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pricecrawler.models.bean.PageLinkProductElement;
import pricecrawler.sync.Consumer;
import pricecrawler.sync.Producer;

@Component
public class PriceCrawlerRunner implements CommandLineRunner {

    public static void main(String[] args) {
 
        BlockingQueue<PageLinkProductElement> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

//        new Thread(producer).start();

        //new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	       BlockingQueue<PageLinkProductElement> queue = new ArrayBlockingQueue<>(10);
	        Producer producer = new Producer(queue);
	        Consumer consumer = new Consumer(queue);

//	        new Thread(producer).start();

	        //new Thread(consumer).start();
	        System.out.println("Producer and Consumer has been started");
	}

}
