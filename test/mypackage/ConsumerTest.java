package mypackage;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ConsumerTest {
	
	private boolean foo = false;

	@Test
	public void consumingTest() {
		
		foo = false;
		
		Market market = new Market();
		
		market.sell(new Product("Test"));
		
		Consumer c = new Consumer("Test", market);
		c.startBuying();
		
		Timer timer = new Timer();
		
		// in seconds
		final int delay = 10;
		
		// wait n seconds, then release the while loop
		timer.schedule(new TimerTask() {
			public void run() {
				// stop the thread
				c.stopBuying();
				// good to go
				foo = true;
			}
		}, delay * 1000);
		
		// wait here
		while(!foo) {
			System.out.println("wait");
		}
		
		// the consumer must buy at least one product within n seconds
		boolean bar = market.getNumberOfSlots() == 0;
		
		Assert.assertEquals(bar, true);
	}

}
