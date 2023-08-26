package br.com.spring.velocity.example.services;

import br.com.spring.velocity.example.models.Customer;
import java.util.List;

public interface CustomerService {
	public List<Customer> getAllCustomers();

	public Customer getCustomerByFirstName(String firstName);

	public Customer insert(Customer customer);

	public Customer updateCustomerByEmail(Customer customer);

	public boolean deletetCustomerByEmail(String firstName);
}
