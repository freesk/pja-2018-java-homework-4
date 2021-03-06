package mypackage;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ProducerTest {
	
	private boolean foo = false;

	@Test
	public void productionTest() {
		Market market = new Market();
		Producer p = new Producer("Test", market);
		p.startProduction();
		
		foo = false;
		
		Timer timer = new Timer();
		
		// in seconds
		final int delay = 10;
		
		// wait n seconds, then release the while loop
		timer.schedule(new TimerTask() {
			public void run() {
				// stop the thread
				p.stopProduction();
				// good to go
				foo = true;
			}
		}, delay * 1000);
		
		// wait here
		while(!foo) {
			System.out.println("wait");
		}
		
		// the producer must create at least one new product within n seconds
		boolean bar = market.getNumberOfSlots() > 0;
		
		Assert.assertEquals(bar, true);
		
	}

}
