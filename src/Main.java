
public class Main {
	
	public static void main(String[] args) {
		
		Market m = new Market();
		
		int i;
		
		String[] producers = { "Mercedes-Benz", "Lexus", "Jaguar", "Toyota", "McLaren" };
		String[] consumers = { "CIA", "M1-6", "MI6", "MOSSAD", "MSS", "BND", "FSB", "DGSE", "ISI", "RAW", "ASIS" };
		
		for (i = 0; i < producers.length; i++) {
			Producer producer = new Producer(producers[i], m);
			producer.startProduction();
		}
		
		for (i = 0; i < consumers.length; i++) {
			Consumer consumer = new Consumer(consumers[i], m);	
			consumer.startBuying();
		}
		
		
	}

}
