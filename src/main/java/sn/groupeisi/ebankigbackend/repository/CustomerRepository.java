package sn.groupeisi.ebankigbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.groupeisi.ebankigbackend.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
