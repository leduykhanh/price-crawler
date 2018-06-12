package pricecrawler.sync;

import java.util.concurrent.BlockingQueue;

import org.springframework.boot.CommandLineRunner;

import pricecrawler.models.bean.PageLinkProductElement;

public class Consumer implements CommandLineRunner{

private BlockingQueue<PageLinkProductElement> queue;
    
    public Consumer(BlockingQueue<PageLinkProductElement> q){
        this.queue=q;
        
    }

    @Override
	public void run(String... args) throws Exception {
//        try{
//        	PageLinkProductElement msg;
//            //consuming messages until exit message is received
//            while(true){
//            	msg = queue.take();	
//            Thread.sleep(10);
//            System.out.println("Consumed "+msg);
//            }
//        }catch(InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
