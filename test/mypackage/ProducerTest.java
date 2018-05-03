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
		
		// wait 3 seconds, then release the while loop
		timer.schedule(new TimerTask() {
			public void run() {
				// good to go
				foo = true;
			}
		}, 5000);
		
		// wait here
		while(!foo) {
//			System.out.println(market.getNumberOfSlots());
		}
		
		// the producer must create at least one new product within 5 seconds
		boolean bar = market.getNumberOfSlots() > 0;
		
		Assert.assertEquals(bar, true);
		
	}

}
