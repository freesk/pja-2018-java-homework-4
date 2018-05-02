import java.util.ArrayList;
import java.util.List;

public class Market {
	private List<Product> list = new ArrayList<Product>();
	private int NUMBER_OF_SLOTS = 5;
	
	public synchronized void sell(Product p) {
		try {
			while (list.size() > NUMBER_OF_SLOTS - 1) wait();
		} catch (InterruptedException e) { 
			System.out.println(e.getMessage());
		}
		// add a new product
		list.add(p);
		notifyAll();
	}
	
	public String getStatus() {
		return "Saturation " + list.size() + "/" + NUMBER_OF_SLOTS;
	}
	
	public synchronized Product buy() {
		try {
			while (list.size() < 1) wait();
		} catch (InterruptedException e) { 
			System.out.println(e.getMessage());
		}
		// remove first
		Product p = list.get(0);
		list.remove(0);
		notifyAll();
		return p;
	}
	
}
