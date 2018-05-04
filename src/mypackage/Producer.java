package mypackage;

public class Producer extends Thread implements Runnable {
	   
	private String name = "";
	private Thread productionThread;
	private Market market;
	private Screen screen = new Screen();
	
	public Producer(String name, Market market) {
		this.name = name;
		this.market = market;
	}
	
	public Producer(String name, Market market, Screen screen) {
		this(name, market);
		this.screen = screen;
	}
	
	public void run() {
		Thread cThread = Thread.currentThread();
		while(cThread == productionThread) {
			try {
				// sleep a random number of seconds
				sleep(Mixin.getRandomInt(1, 5) * 1000);
			} catch(InterruptedException e) {
				System.out.println(e.getMessage());
				return;
			}
			if (cThread == productionThread) {
				// create a new product
				Product p = new Product(this.name);
				screen.log(name + " produced a new car " + p.uniqueID + "");
				// put up for sale
				market.sell(p);
				screen.log(name + " put " + p.uniqueID + " up for sale!");
			}
		}
	}
	
	public void startProduction() {
		super.start();
		if (productionThread == null) {
			productionThread = new Thread(this);
			productionThread.start(); 
		}
	}
	
	@SuppressWarnings("deprecation")
	public void stopProduction() {
		productionThread = null;
		super.stop();
	}
	
}
