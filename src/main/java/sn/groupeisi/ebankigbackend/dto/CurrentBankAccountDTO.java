package sn.groupeisi.ebankigbackend.dto;

import lombok.Data;
import sn.groupeisi.ebankigbackend.enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    protected double overDraft;

}
