package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter client data:");
		System.out.print("Name: ");
		String nameC = sc.next();
		
		System.out.print("Email: ");
		String emailC = sc.next();
		
		System.out.print("Birth date(DD/MM/YYYY): ");
		String dateBirthdayClient = sc.next();
		Date bdC = sdf2.parse(dateBirthdayClient);	
		Client c = new Client(nameC, emailC, bdC);

		
		System.out.println("Enter order data:");
		System.out.print("Status: ");
		OrderStatus os = OrderStatus.valueOf(sc.next());
		Date dateOrder = new Date();
		Order order = new Order(dateOrder, os, c);
		//to show the order date use the method .format of sdf(SimpleDateFormat)
		
		System.out.print("How many items to this order? ");
		Integer quantityItems = sc.nextInt();
		
		for(int i=0; i<quantityItems; i++){
			System.out.println("Enter #" + (1+i) + " item data:");
			
			System.out.print("Product name: ");
			String pName = sc.next();
			
			System.out.print("Product price: ");
			double pPrice = sc.nextDouble();
			
			System.out.println("Quantity: ");
			Integer pQuantity = sc.nextInt();
			
			Product p = new Product(pName, pPrice);
			OrderItem oi = new OrderItem(pQuantity, p);
			order.addOrderItem(oi);
			//order is created
		}
		//printing the order
		double sumPrice = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("ORDER SUMMARY:\n");
		sb.append("Order moment: " + sdf.format(order.getMoment()) + "\n");
		sb.append("Order status: " + order.getStatus() + "\n");
		sb.append("Client: " + order.getClient().getName() + " ");
		sb.append("(" + sdf2.format(order.getClient().getBirthDate()) + ") - ");
		sb.append(order.getClient().getEmail() + "\n");
		sb.append("Order items:\n");
		for (OrderItem oi : order.getOrderItems()) {
			sb.append(oi.getProduct().getName() + ", $");
			sb.append(oi.getProduct().getPrice() + ", Quantity: ");
			sb.append(oi.getQuantity() + ", Subtotal: $");
			sb.append(oi.subTotal() + "\n");
			sumPrice += oi.subTotal();
		}
		sb.append("Total price: $" + sumPrice);
		System.out.println(sb.toString());
		
		sc.close();		
	}
}
