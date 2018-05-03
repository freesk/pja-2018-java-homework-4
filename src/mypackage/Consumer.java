package mypackage;

public class Consumer extends Thread implements Runnable {
	
	private String name = "";
	private Thread consumerThread;
	private Market market;
	private Screen screen;
	
	public Consumer(String name, Market market, Screen screen) {
		this.name = name;
		this.market = market;
		this.screen = screen;
	}
	
	public void run() {
		Thread cThread = Thread.currentThread();
		while(cThread == consumerThread) {
			try {
				// sleep a random number of seconds 
				sleep(Mixin.getRandomInt(3, 9) * 1000);
			} catch(InterruptedException e) {
				System.out.println(e.getMessage());
				return;
			}
			if (cThread == consumerThread) {
				// buy a product
				screen.log(name + " is about to buy someting!");
				Product p = market.buy();
				screen.log(name + " bought " + p.producer + " " + p.uniqueID + "");
			}
		}
	}
	
	public void startBuying() {
		super.start();
		if (consumerThread == null) {
			consumerThread = new Thread(this);
			consumerThread.start(); 
		}
	}
	
	public void stopBuying() {
		consumerThread = null;
		super.stop();
	}
	
}
