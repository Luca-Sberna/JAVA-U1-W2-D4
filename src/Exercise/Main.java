package Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		List<Product> productList = createProductList();
		List<Order> orderList = createOrderList();

		// Esercizio 1: Prodotti nella categoria Books con prezzo > 100
		List<Product> filteredProducts = productList.stream().filter(product -> "Books".equals(product.getCategory()))
				.filter(product -> product.getPrice() > 100).collect(Collectors.toList());

		System.out.println("Prodotti nella categoria Books con prezzo > 100:");
		for (Product product : filteredProducts) {
			System.out.println(product.getName());
		}
		System.out.println();

		// Esercizio 2: Ordini con prodotti nella categoria Baby
		List<Order> filteredOrders = orderList.stream()
				.filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby")))
				.collect(Collectors.toList());

		System.out.println("Ordini con prodotti nella categoria Baby:");
		for (Order order : filteredOrders) {
			System.out.println("ID ordine: " + order.getId());
		}
		System.out.println();

		// Esercizio 3: Prodotti nella categoria Boys con sconto del 1%
		List<Product> discountedProducts = productList.stream().filter(product -> product.getCategory().equals("Boys"))
				.peek(product -> product.setPrice(product.getPrice() * 0.99)).collect(Collectors.toList());

		System.out.println("Prodotti nella categoria Boys con sconto del 1%:");
		for (Product product : discountedProducts) {
			System.out.println(product.getName() + " - Prezzo scontato: " + product.getPrice());
		}
		System.out.println();

		// Esercizio 4: Prodotti ordinati da clienti di livello 2 tra l'01-Feb-2021 e
		// l'01-Apr-2021
		LocalDate startDate = LocalDate.of(2021, 2, 1);
		LocalDate endDate = LocalDate.of(2021, 4, 1);

		List<Product> orderedProducts = orderList.stream()
				.filter(order -> ((Customer) order.getCustomer()).getTier() == 2)
				.filter(order -> order.getOrderDate().isAfter(startDate) && order.getOrderDate().isBefore(endDate))
				.flatMap(order -> order.getProducts().stream()).collect(Collectors.toList());

		System.out.println("Prodotti ordinati da clienti di livello 2 tra l'01-Feb-2021 e l'01-Apr-2021:");
		for (Product product : orderedProducts) {
			System.out.println(product.getName());
		}
		System.out.println();
	}

	static List<Product> createProductList() {
		List<Product> productList = new ArrayList<>();

		productList.add(new Product(1L, "Il Signore Di Java - Stream Sniper", "Books", 150.0));
		productList.add(new Product(1L, "Il Signore Di Java - Stream Sniper", "Boys", 150.0));
		productList.add(new Product(2L, "Bleach", "Baby", 90.0));
		productList.add(new Product(3L, "Il Signore di Java - E Le Due Eccezioni", "Boys", 50.0));

		return productList;
	}

	private static List<Order> createOrderList() {
		List<Order> orderList = new ArrayList<>();

		List<Product> order1Products = new ArrayList<>();
		order1Products.add(new Product(1L, "Il Signore Di Java - Stream Sniper", "Books", 150.0));
		order1Products.add(new Product(3L, "Il Signore di Java - E Le Due Eccezioni", "Boys", 50.0));
		Order order1 = new Order(1L, "Pending", LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 25), order1Products,
				new Customer(1L, "Luca", 2));
		orderList.add(order1);

		List<Product> order2Products = new ArrayList<>();
		order2Products.add(new Product(2L, "Bleach", "Baby", 90.0));
		Order order2 = new Order(2L, "Completed", LocalDate.of(2021, 2, 10), LocalDate.of(2021, 2, 20), order2Products,
				new Customer(2L, "Mauro", 1));
		orderList.add(order2);

		List<Product> order3Products = new ArrayList<>();
		order3Products.add(new Product(3L, "Il Signore di Java - E Le Due Eccezioni", "Boys", 50.0));
		Order order3 = new Order(3L, "Completed", LocalDate.of(2021, 2, 10), LocalDate.of(2021, 2, 20), order3Products,
				new Customer(3L, "Diego", 1));
		orderList.add(order3);

		return orderList;
	}

}
