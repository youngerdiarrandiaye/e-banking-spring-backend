package sn.groupeisi.ebankigbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.groupeisi.ebankigbackend.entities.AccountOperation;
import sn.groupeisi.ebankigbackend.entities.BankAccount;
public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
