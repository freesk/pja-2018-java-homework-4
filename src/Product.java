import java.util.UUID;

public class Product {
	
	public String uniqueID = "";
	public String producer = "";

	public Product(String producer) {
		this.producer = producer;
		this.uniqueID = UUID.randomUUID().toString();
	}

}
