package entities;

public class Product {
	
	private String name;
	private Double price;
	private Integer quantity;
	
	public Product() {
	}

	public Product(String name, Double valor, Integer quantity) {
		this.name = name;
		this.price = valor;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValor() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public double totalValue() {
		return quantity * price;
	}
}
