package sn.groupeisi.ebankigbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.groupeisi.ebankigbackend.enums.OprationType;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date operationDate;
    private double amount;
    private OprationType type;
    private String description;
    @ManyToOne
    private BankAccount bankAccount;
}
