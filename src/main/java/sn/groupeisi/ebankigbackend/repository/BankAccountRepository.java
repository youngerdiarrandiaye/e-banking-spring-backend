package sn.groupeisi.ebankigbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.groupeisi.ebankigbackend.entities.BankAccount;
import sn.groupeisi.ebankigbackend.entities.Customer;
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
