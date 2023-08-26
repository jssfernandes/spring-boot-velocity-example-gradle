package br.com.spring.velocity.example.repositories;

import br.com.spring.velocity.example.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long > {
    public CustomerEntity findByFirstName(String firstName);

    public CustomerEntity findByEmail(String email);
}
