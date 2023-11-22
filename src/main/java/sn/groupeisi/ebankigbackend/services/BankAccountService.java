package sn.groupeisi.ebankigbackend.services;

import sn.groupeisi.ebankigbackend.dto.CustomerDTO;
import sn.groupeisi.ebankigbackend.entities.BankAccount;
import sn.groupeisi.ebankigbackend.exceptions.BalanceNotSufficientException;
import sn.groupeisi.ebankigbackend.exceptions.BankAccountNotFoundExecption;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    BankAccount saveCurrentBankAccount(double initialBalance,double overDraft,Long customerId) ;
    BankAccount saveSavingBankAccount(double initialBalance,double interestRate,Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(Long customerId);

    List<BankAccount> listBankAccounts();

    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundExecption;
    void debit(String accountId,double amount,String description) throws BankAccountNotFoundExecption, BalanceNotSufficientException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundExecption;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BalanceNotSufficientException, BankAccountNotFoundExecption;
}
