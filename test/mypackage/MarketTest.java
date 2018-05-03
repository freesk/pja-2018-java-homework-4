package mypackage;

//java lib
import java.lang.RuntimeException;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

// my classes 
import mypackage.Market;

@SuppressWarnings({"unused", "deprecation"})
public class MarketTest { 

//	@Before
//	public void setUp() throws Exception {
//		System.out.println("@Before");
//	}
	
//	@After
//	public void tearDown() throws Exception {
//		System.out.println("@After");
//	}
	
	private boolean foo = false; 
	
	@Test
	public void buyWithZeroSaturation() {
		Market market = new Market();
		
		// reset to false
		foo = false;
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			public void run() {
				// release from wait()
				market.sell(new Product("Test"));
				// good to go
				foo = true;
			}
		}, 3000);
		
		try {
			// it's supposed to hang 3 seconds till the timer updates the market 
			market.buy();	
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} finally {
			// whatever happens when we buy, the timer gets canceled
			timer.cancel();
		}
		
		Assert.assertEquals(foo, true);
	}
	
	@Test
	public void sellWithMaxSaturation() {
		Market market = new Market();
		
		// reset to false
		foo = false;
		
		Timer timer = new Timer();
		
		// take all 5 available slots
		market.sell(new Product("Test"));
		market.sell(new Product("Test"));
		market.sell(new Product("Test"));
		market.sell(new Product("Test"));
		market.sell(new Product("Test"));
		
		timer.schedule(new TimerTask() {
			public void run() {
				// release from wait()
				market.buy();
				// good to go
				foo = true;
			}
		}, 3000);
		
		try {
			// it's supposed to hang 3 seconds till the timer updates the market
			market.sell(new Product("Test"));	
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} finally {
			// whatever happens when we sell, the timer gets canceled
			timer.cancel();
		}
		
		Assert.assertEquals(foo, true);
	}
}
