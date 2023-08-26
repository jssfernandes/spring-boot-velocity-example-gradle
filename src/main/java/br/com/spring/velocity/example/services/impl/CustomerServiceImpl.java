package br.com.spring.velocity.example.services.impl;

import br.com.spring.velocity.example.entities.CustomerEntity;
import br.com.spring.velocity.example.models.Customer;
import br.com.spring.velocity.example.repositories.CustomerRepository;
import br.com.spring.velocity.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> customerEntityList = this.customerRepository.findAll();

        return !CollectionUtils.isEmpty(customerEntityList) ? customerEntityList.stream().map(CustomerEntity::toModel).collect(Collectors.toList()) : null;
    }

    @Override
    public Customer getCustomerByFirstName(String firstName) {
        if (firstName != null) {
            CustomerEntity customerEntity = this.customerRepository.findByFirstName(firstName);
            return customerEntity.toModel();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Customer insert(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        setCustomer(customerEntity, customer);

        return this.customerRepository.saveAndFlush(customerEntity).toModel();
    }

    @Override
    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Customer updateCustomerByEmail(Customer customer) {

        CustomerEntity customerEntity = getCustomerEntityFirstName(customer.getFirstName());
//        customer.setId(customerEntity.getId());
        setCustomer(customerEntity, customer);

        return this.customerRepository.save(customerEntity).toModel();
    }

    @Override
    public boolean deletetCustomerByEmail(String email) {
        if (email != null) {
            try {
                CustomerEntity customerEntity = this.customerRepository.findByEmail(email);
                customerRepository.delete(customerEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private void setCustomer(CustomerEntity customerEntity, Customer customer) {
        if (customer != null) {
            customerEntity.setFirstName(customer.getFirstName());
            customerEntity.setLastName(customer.getLastName());
            customerEntity.setEmail(customer.getEmail());
            customerEntity.setGender(customer.getGender());
        }
    }

    public CustomerEntity getCustomerEntityFirstName(String firstName) {
        return !firstName.isEmpty() ? this.customerRepository.findByFirstName(firstName) : null;
    }
    public CustomerEntity getCustomerEntityByEmail(String email) {
        return !email.isEmpty() ? this.customerRepository.findByEmail(email) : null;
    }
}
