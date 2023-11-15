package sn.groupeisi.ebankigbackend.services;

import sn.groupeisi.ebankigbackend.entities.BankAccount;
import sn.groupeisi.ebankigbackend.entities.Customer;
import sn.groupeisi.ebankigbackend.exceptions.BalanceNotSufficientException;
import sn.groupeisi.ebankigbackend.exceptions.BankAccountNotFoundExecption;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveCurrentBankAccount(double initialBalance,double overDraft,Long customerId) ;
    BankAccount saveSavingBankAccount(double initialBalance,double interestRate,Long customerId);
    List<Customer> listCustomers();
    List<BankAccount> listBankAccounts();

    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundExecption;
    void debit(String accountId,double amount,String description) throws BankAccountNotFoundExecption, BalanceNotSufficientException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundExecption;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BalanceNotSufficientException, BankAccountNotFoundExecption;
}
